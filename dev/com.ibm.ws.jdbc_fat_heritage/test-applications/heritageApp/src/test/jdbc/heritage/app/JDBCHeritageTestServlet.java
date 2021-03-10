/*******************************************************************************
 * Copyright (c) 2021 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package test.jdbc.heritage.app;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.lang.reflect.Field;
import java.security.AccessController;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.sql.DataSource;
import javax.transaction.UserTransaction;

import org.junit.Test;

import com.ibm.websphere.ce.cm.DuplicateKeyException;
import com.ibm.websphere.ce.cm.StaleConnectionException;

import componenttest.app.FATServlet;
import test.jdbc.heritage.driver.HeritageDBConnection;
import test.jdbc.heritage.driver.helper.HeritageDBDuplicateKeyException;
import test.jdbc.heritage.driver.helper.HeritageDBFeatureUnavailableException;
import test.jdbc.heritage.driver.helper.HeritageDBStaleConnectionException;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = "/JDBCHeritageTestServlet")
public class JDBCHeritageTestServlet extends FATServlet {
    @Resource
    private DataSource defaultDataSource;

    @Resource
    private UserTransaction tx;

    @Override
    public void destroy() {
        super.destroy();
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    /**
     * Utility method that accesses the internal field WSJdbcPreparedStatement.pstmtImpl.
     * This is useful for determining if we got the same statement from the statement cache.
     *
     * @param pstmt prepared statement wrapper (WSJdbcPreparedStatement).
     * @return prepared statement implementation.
     */
    private static PreparedStatement pstmtImpl(PreparedStatement pstmt) throws PrivilegedActionException {
        return AccessController.doPrivileged((PrivilegedExceptionAction<PreparedStatement>) () -> {
            for (Class<?> c = pstmt.getClass(); c != null; c = c.getSuperclass())
                try {
                    Field pstmtImpl = c.getDeclaredField("pstmtImpl");
                    pstmtImpl.setAccessible(true);
                    return (PreparedStatement) pstmtImpl.get(pstmt);
                } catch (NoSuchFieldException x) {
                    // ignore, try the super class
                }
            throw new NoSuchFieldException("Unable to find pstmtImpl on prepared statement wrapper. If the field has been renamed, you will need to update this test.");
        });
    }

    /**
     * Verifies that doConnectionCleanup is invoked on a data store helper that uses this notification to
     * restore the default list of client info properties.
     */
    @Test
    public void testConnectionCleanup() throws Exception {
        try (Connection con = defaultDataSource.getConnection()) {
            HeritageDBConnection hcon = con.unwrap(HeritageDBConnection.class);
            Set<String> defaultClientInfoKeys = hcon.getClientInfoKeys();

            // Set non-default keys
            hcon.setClientInfoKeys("testConCleanupKey1", "testConCleanupKey2");

            // Prove the fake JDBC driver API returns what we set because the test will rely on this later
            Set<String> validKeys = hcon.getClientInfoKeys();
            assertTrue(validKeys.toString(), validKeys.contains("testConCleanupKey1")
                                             && validKeys.contains("testConCleanupKey2")
                                             && validKeys.size() == 2);

            tx.begin();
            try {
                con.createStatement()
                                .executeQuery("VALUES ('testConnectionCleanup is an excellent test')")
                                .getStatement()
                                .close();

                // doConnectionCleanup resets the default client info keys value when the connection handle is
                // reassociated across the transaction boundary

                validKeys = hcon.getClientInfoKeys();
                assertEquals(defaultClientInfoKeys, validKeys);

                // Set more non-default keys
                hcon.setClientInfoKeys("testConCleanupKey3", "testConCleanupKey4", "testConCleanupKey5");

            } finally {
                tx.commit();
            }

            // doConnectionCleanup again resets the default client info keys value when the connection handle is
            // reassociated across the transaction boundary

            validKeys = hcon.getClientInfoKeys();
            assertEquals(defaultClientInfoKeys, validKeys);
        }
    }

    /**
     * Verifies that doConnectionSetup was performed on a new connection.
     * Verifies that doConnectionSetupPerGetConnection is invoked for each first connection handle.
     * Verifies that doConnectionCleanupPerCloseConnection is invoked upon closing or dissociating the last connection handle.
     */
    @Test
    public void testConnectionSetup() throws Exception {
        try (Connection con = defaultDataSource.getConnection("testConnectionSetupUser", "PASSWORD")) {

            // Read the value that was set by doConnectionSetup
            Statement stmt = con.createStatement();
            ResultSet result = stmt.executeQuery("VALUES SYSCS_UTIL.SYSCS_GET_RUNTIMESTATISTICS()");
            assertTrue(result.next());
            assertNotNull(result.getString(1));
            stmt.close();

            // Query the doConnectionSetupPerGetConnection count, expecting 1
            result = con.prepareCall("CALL TEST.GET_SETUP_COUNT()").executeQuery();
            assertTrue(result.next());
            assertEquals(1, result.getInt(1));
            result.getStatement().setPoolable(false); // statement caching interferes with test replacing sql
            result.getStatement().close();

            // Query the doConnectionCleanupPerCloseConnection count, expecting 0
            result = con.prepareCall("CALL TEST.GET_CLEANUP_COUNT()").executeQuery();
            assertTrue(result.next());
            assertEquals(0, result.getInt(1));
            result.getStatement().setPoolable(false); // statement caching interferes with test replacing sql
            result.getStatement().close();
        }

        Connection con;
        ResultSet result;

        tx.begin();
        try {
            con = defaultDataSource.getConnection("testConnectionSetupUser", "PASSWORD");

            // Query the doConnectionSetupPerGetConnection count, expecting 2
            result = con.prepareCall("CALL TEST.GET_SETUP_COUNT()").executeQuery();
            assertTrue(result.next());
            assertEquals(2, result.getInt(1));
            result.getStatement().setPoolable(false); // statement caching interferes with test replacing sql
            result.getStatement().close();

            // Query the doConnectionCleanupPerCloseConnection count, expecting 1
            result = con.prepareCall("CALL TEST.GET_CLEANUP_COUNT()").executeQuery();
            assertTrue(result.next());
            assertEquals(1, result.getInt(1));
            result.getStatement().setPoolable(false); // statement caching interferes with test replacing sql
            result.getStatement().close();

            Connection con2 = defaultDataSource.getConnection("testConnectionSetupUser", "PASSWORD");

            // Query the doConnectionSetupPerGetConnection count, still expecting 2
            result = con.prepareCall("CALL TEST.GET_SETUP_COUNT()").executeQuery();
            assertTrue(result.next());
            assertEquals(2, result.getInt(1));
            result.getStatement().setPoolable(false); // statement caching interferes with test replacing sql
            result.getStatement().close();

            // Query the doConnectionCleanupPerCloseConnection count, still expecting 1
            result = con.prepareCall("CALL TEST.GET_CLEANUP_COUNT()").executeQuery();
            assertTrue(result.next());
            assertEquals(1, result.getInt(1));
            result.getStatement().setPoolable(false); // statement caching interferes with test replacing sql
            result.getStatement().close();
        } finally {
            tx.commit();
        }

        // Query the doConnectionSetupPerGetConnection count
        result = con.prepareCall("CALL TEST.GET_SETUP_COUNT()").executeQuery();
        assertTrue(result.next());
        // assertEquals(3, result.getInt(1)); // legacy behavior does not invoke doConnectionSetupPerGetConnection on reassociate
        result.getStatement().setPoolable(false); // statement caching interferes with test replacing sql
        result.getStatement().close();

        // Query the doConnectionCleanupPerCloseConnection count, expecting 2
        result = con.prepareCall("CALL TEST.GET_CLEANUP_COUNT()").executeQuery();
        assertTrue(result.next());
        assertEquals(2, result.getInt(1));
        result.getStatement().setPoolable(false); // statement caching interferes with test replacing sql
        result.getStatement().close();

        con.close();
    }

    /**
     * Verifies that doConnectionCleanupPerTransaction is invoked exactly once for each global transaction
     * or Local Transaction Containment (which is not actually a transaction).
     */
    @Test
    public void testConnectionSetupPerTransaction() throws Exception {
        // Use in Local Transaction Containment:
        try (Connection con = defaultDataSource.getConnection("testConnectionSetupPerTxUser", "PASSWORD")) {

            // Query the doConnectionSetupPerTransaction count, expecting 1
            ResultSet result = con.prepareCall("CALL TEST.GET_TRANSACTION_COUNT()").executeQuery();
            assertTrue(result.next());
            assertEquals(1, result.getInt(1));
            result.getStatement().setPoolable(false); // statement caching interferes with test replacing sql
            result.getStatement().close();
        }

        Connection con;
        ResultSet result;

        // Use in global transaction:
        tx.begin();
        try {
            con = defaultDataSource.getConnection("testConnectionSetupPerTxUser", "PASSWORD");

            // Query the doConnectionSetupPerTransaction count, expecting 2
            result = con.prepareCall("CALL TEST.GET_TRANSACTION_COUNT()").executeQuery();
            assertTrue(result.next());
            assertEquals(2, result.getInt(1));
            result.getStatement().setPoolable(false); // statement caching interferes with test replacing sql
            result.getStatement().close();

            Connection con2 = defaultDataSource.getConnection("testConnectionSetupPerTxUser", "PASSWORD");

            // Query the doConnectionSetupPerTransaction count, still expecting 2
            result = con.prepareCall("CALL TEST.GET_TRANSACTION_COUNT()").executeQuery();
            assertTrue(result.next());
            assertEquals(2, result.getInt(1));
            result.getStatement().setPoolable(false); // statement caching interferes with test replacing sql
            result.getStatement().close();

            con.close();
            con2.close();

            con = defaultDataSource.getConnection("testConnectionSetupPerTxUser", "PASSWORD");

            // Query the doConnectionSetupPerTransaction count, still expecting 2 because it's the same transaction
            result = con.prepareCall("CALL TEST.GET_TRANSACTION_COUNT()").executeQuery();
            assertTrue(result.next());
            assertEquals(2, result.getInt(1));
            result.getStatement().setPoolable(false); // statement caching interferes with test replacing sql
            result.getStatement().close();
        } finally {
            tx.commit();
        }

        // Use again in global transaction:
        tx.begin();
        try {
            con = defaultDataSource.getConnection("testConnectionSetupPerTxUser", "PASSWORD");

            // Query the doConnectionSetupPerTransaction count, expecting 3
            result = con.prepareCall("CALL TEST.GET_TRANSACTION_COUNT()").executeQuery();
            assertTrue(result.next());
            assertEquals(3, result.getInt(1));
            result.getStatement().setPoolable(false); // statement caching interferes with test replacing sql
            result.getStatement().close();

            con.close();
        } finally {
            tx.commit();
        }
    }

    /**
     * Confirm that a dataSource that is configured with heritageSettings can be injected
     * and has the transaction isolation level that is assigned as default by the DataStoreHelper.
     */
    @Test
    public void testDefaultIsolationLevel() throws Exception {
        assertNotNull(defaultDataSource);
        try (Connection con = defaultDataSource.getConnection()) {
            assertEquals(Connection.TRANSACTION_SERIALIZABLE, con.getTransactionIsolation());
        }
    }

    /**
     * Confirm that a data store helper's doStatementCleanup is capable of resetting the configured
     * default queryTimeout from the dataSource configuration.
     */
    @Test
    public void testDefaultQueryTimeout() throws Exception {
        try (Connection con = defaultDataSource.getConnection()) {
            PreparedStatement pstmt = con.prepareStatement("VALUES ('testDefaultQueryTimeout', SQRT(196))");
            assertEquals(81, pstmt.getQueryTimeout()); // configured on dataSource as 1m21s
            pstmt.setQueryTimeout(289);
            pstmt.executeQuery().close();
            // return statement to cache
            pstmt.close();

            // reuse from cache
            pstmt = con.prepareStatement("VALUES ('testDefaultQueryTimeout', SQRT(196))");
            assertEquals(81, pstmt.getQueryTimeout()); // doStatementCleanup resets the configured default
            pstmt.executeQuery().close();
            pstmt.close();
        }

    }

    /**
     * Verify that the data store helper is invoked to map and replace exceptions.
     */
    @Test
    public void testMapExceptionAndReplaceIt() throws Exception {
        try (Connection con = defaultDataSource.getConnection()) {
            try {
                con.prepareCall("CALL TEST.FORCE_EXCEPTION(0A000,0,java.sql.SQLFeatureNotSupportedException)").executeQuery();
                fail("Test case did not force an error with SQL state 0A000");
            } catch (HeritageDBFeatureUnavailableException x) {
                // pass
            }

            try {
                con.prepareCall("CALL TEST.FORCE_EXCEPTION(08001,22344,java.sql.SQLException)").executeQuery();
                fail("Test case did not force an error with SQL state 08001 and error code 22344");
            } catch (HeritageDBStaleConnectionException x) {
                // pass
            }

            // user-defined exception indicates stale:
            assertTrue(con.isClosed());
        }

        try (Connection con = defaultDataSource.getConnection()) {
            try {
                con.prepareCall("CALL TEST.FORCE_EXCEPTION(08006,4002,java.sql.SQLException)").executeQuery();
                fail("Test case did not force an error with SQL state 08006 and error code 4002");
            } catch (StaleConnectionException x) {
                // pass if not a subclass
                assertEquals(StaleConnectionException.class.getName(), x.getClass().getName());
            }

            // user-defined exception indicates stale:
            assertTrue(con.isClosed());
        }
    }

    /**
     * Cause an error that we have arbitrarily mapped to StaleStatementException in the data store helper.
     * Verify that the statement is not cached and that the error is surface to the application as a
     * StaleConnectionException. This matches the legacy behavior.
     */
    @Test
    public void testStaleStatement() throws Exception {
        PreparedStatement cachedStatement;

        try (Connection con = defaultDataSource.getConnection("testStaleStatement", "StaleStmtPwd")) {
            // Force an error that raises a SQLState (22013) that we mapped to StaleStatementException,
            PreparedStatement pstmt = con.prepareStatement("VALUES SQRT (-1)");
            cachedStatement = pstmtImpl(pstmt);
            try {
                ResultSet result = pstmt.executeQuery();
                result.next();
                System.out.println("The database says that the square root of -1 is " + result.getObject(1));
            } catch (StaleConnectionException x) {
                // The StaleStatementException must be surfaced to the application as StaleConnectionException
                assertEquals(StaleConnectionException.class.getName(), x.getClass().getName());
                assertEquals("22013", x.getSQLState());
            }
        }

        // The statement must not be cached due to the StaleStatementException
        try (Connection con = defaultDataSource.getConnection("testStaleStatement", "StaleStmtPwd")) {
            // Force an error that raises a SQLState (22013) that we mapped to StaleStatementException,
            PreparedStatement pstmt = con.prepareStatement("VALUES SQRT (-1)");
            assertNotSame(cachedStatement, pstmtImpl(pstmt));
        }
    }

    /**
     * Confirm that doesStatementCacheIsoLevel causes prepared statements to be cached based on
     * the isolation level that was present on the connection at the time the statement was
     * created.
     */
    @Test
    public void testStatementCachingBasedOnIsolationLevel() throws Exception {
        try (Connection con = defaultDataSource.getConnection()) {
            String sql = "VALUES('testStatementCachingBasedOnIsolationLevel')";
            PreparedStatement pstmt;

            con.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            pstmt = con.prepareStatement(sql);
            PreparedStatement pstmtRC = pstmtImpl(pstmt);
            pstmt.close();

            con.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);
            pstmt = con.prepareStatement(sql);
            PreparedStatement pstmtRR = pstmtImpl(pstmt);
            pstmt.close();

            con.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            pstmt = con.prepareStatement(sql);
            PreparedStatement pstmtRC2 = pstmtImpl(pstmt);
            pstmt.close();

            assertNotSame(pstmtRC, pstmtRR);
            assertSame(pstmtRC, pstmtRC2);
        }
    }

    /**
     * Confirm that doStatementCleanup is invoked when reusing statements from the statement cache.
     */
    @Test
    public void testStatementCleanup() throws Exception {
        try (Connection con = defaultDataSource.getConnection()) {
            PreparedStatement pstmt = con.prepareStatement("VALUES ('testPreparedStatementCleanup', CURRENT_TIME)");
            assertEquals(225, pstmt.getMaxFieldSize()); // default value used by fake JDBC driver
            pstmt.setMaxFieldSize(441);
            pstmt.executeQuery().close();
            // return statement to cache
            pstmt.close();

            // reuse from cache
            pstmt = con.prepareStatement("VALUES ('testPreparedStatementCleanup', CURRENT_TIME)");
            assertEquals(225, pstmt.getMaxFieldSize()); // doStatementCleanup resets the default
            pstmt.executeQuery().close();
            pstmt.close();

            // repeat with CallableStatement
            CallableStatement cstmt = con.prepareCall("VALUES (SUBSTR('testCallableStatementCleanup', 5, 12))");
            assertEquals(225, cstmt.getMaxFieldSize()); // default value used by fake JDBC driver
            cstmt.setMaxFieldSize(576);
            cstmt.executeQuery().close();
            // return statement to cache
            cstmt.close();

            // reuse from cache
            cstmt = con.prepareCall("VALUES (SUBSTR('testCallableStatementCleanup', 5, 12))");
            assertEquals(225, cstmt.getMaxFieldSize()); // doStatementCleanup resets the default
            cstmt.executeQuery().close();
            cstmt.close();
        }
    }

    /**
     * Confirm that setUserDefinedMap is supplied with the identifyException configuration.
     */
    @Test
    public void testUserDefinedExceptionMap() throws SQLException {
        try (Connection con = defaultDataSource.getConnection()) {
            try {
                con.prepareCall("CALL TEST.FORCE_EXCEPTION(HY000,40960,test.jdbc.heritage.driver.HeritageDBDoesNotImplementItException)").executeQuery();
                fail("Test case did not force an error with error code 40960");
            } catch (HeritageDBFeatureUnavailableException x) {
                // pass
            }

            try {
                con.prepareCall("CALL TEST.FORCE_EXCEPTION(08004,0,java.sql.SQLException)").executeQuery();
                fail("Test case did not force an error with SQL state 08004");
            } catch (StaleConnectionException x) {
                // identifyException removes this mapping, so it should not be considered stale
                throw x;
            } catch (SQLException x) {
                // pass
            }

            try {
                con.prepareCall("CALL TEST.FORCE_EXCEPTION(H8006,0,test.jdbc.heritage.driver.HeritageDBConnectionBadException)").executeQuery();
                fail("Test case did not force an error with SQL state H8006");
            } catch (StaleConnectionException x) {
                // pass if not a subclass
                assertEquals(StaleConnectionException.class.getName(), x.getClass().getName());
            }

            // automatically closed due to stale connection
            assertTrue(con.isClosed());
        }

        try (Connection con = defaultDataSource.getConnection()) {
            try {
                con.prepareCall("CALL TEST.FORCE_EXCEPTION(H8000,0,java.sql.SQLRecoverableException)").executeQuery();
                fail("Test case did not force an error with SQL state H8000");
            } catch (StaleConnectionException x) {
                // pass if not a subclass
                assertEquals(StaleConnectionException.class.getName(), x.getClass().getName());
            }

            // automatically closed due to stale connection
            assertTrue(con.isClosed());
        }
    }

    /**
     * Verify that the user-defined error map (configured via identifyException) takes precedence when both
     * the data store helper and the identifyException configuration have a conflicting mapping.
     */
    @Test
    public void testUserDefinedExceptionMapOverridesDefaults() throws SQLException {
        try (Connection con = defaultDataSource.getConnection()) {
            // Default mapping for SQL state 23000:
            try {
                con.prepareCall("CALL TEST.FORCE_EXCEPTION(23000,0,java.sql.SQLIntegrityConstraintViolationException)").executeQuery();
                fail("Test case did not force an error with SQL state 23000 and error code 143360");
            } catch (HeritageDBDuplicateKeyException x) {
                throw x;
            } catch (DuplicateKeyException x) {
                // pass
            }

            // Override by identifyException when error code is 143360
            try {
                con.prepareCall("CALL TEST.FORCE_EXCEPTION(23000,143360,java.sql.SQLIntegrityConstraintViolationException)").executeQuery();
                fail("Test case did not force an error with SQL state 23000 and error code 143360");
            } catch (HeritageDBDuplicateKeyException x) {
                // pass
            }
        }
    }
}