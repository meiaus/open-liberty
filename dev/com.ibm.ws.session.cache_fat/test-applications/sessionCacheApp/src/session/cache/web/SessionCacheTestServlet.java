/*******************************************************************************
 * Copyright (c) 2017,2018 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package session.cache.web;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import javax.naming.InitialContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionListener;
import javax.sql.DataSource;

import componenttest.app.FATServlet;

@SuppressWarnings("serial")
@WebServlet("/SessionCacheTestServlet")
public class SessionCacheTestServlet extends FATServlet {
    // Maximum number of nanoseconds for test to wait
    static final long TIMEOUT_NS = TimeUnit.MINUTES.toNanos(2);

    /**
     * Evict the active session from memory, if any.
     */
    public void evictSession(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        // We've configured the server to only hold a single session in memory.
        // By creating a new one, we flush the other one from memory.
        request.getSession();
    }

    /**
     * Obtains the session id for the current session and writes it to the servlet response
     */
    public void getSessionId(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String sessionId = request.getSession().getId();
        System.out.println("session id is " + sessionId);
        response.getWriter().write("session id: [" + sessionId + "]");
    }

    /**
     * Invalidate the active session, if any.
     */
    public void invalidateSession(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            System.out.println("Invalidating session: " + session.getId());
            session.invalidate();
        }
    }

    /**
     * Verify that the session contains the specified attribute names.
     */
    public void testAttributeNames(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String[] expectedAttributes = request.getParameter("sessionAttributes").split(",");
        boolean allowOtherAttributes = Boolean.parseBoolean(request.getParameter("allowOtherAttributes"));

        HttpSession session = request.getSession(false);
        Enumeration<String> attributeNames = session.getAttributeNames();

        Collection<String> expected = Arrays.asList(expectedAttributes);
        Collection<String> observed = Collections.list(attributeNames);
        if (allowOtherAttributes)
            assertTrue("Expected " + expected + ". Observed " + observed, observed.containsAll(expected));
        else
            assertEquals(new HashSet<String>(expected), new HashSet<String>(observed));
    }

    /**
     * Test that the reported creation time is reasonably close to the time that we create the session
     * and that the session consistently returns the same value as the creation time.
     */
    public void testCreationTime(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        long now = System.currentTimeMillis();
        HttpSession session = request.getSession(true);
        long creationTime = session.getCreationTime();
        long lastAccessedTime = session.getLastAccessedTime();
        assertEquals(creationTime, lastAccessedTime);

        // reported creation time should be reasonably close to when we requested the session be created
        long diff = creationTime - now;
        assertTrue("unexpectedly large difference from current time: " + diff, Math.abs(diff) < TimeUnit.NANOSECONDS.toMillis(TIMEOUT_NS));

        session.setAttribute("testCreationTime-key1", 3.14159f);

        // creation time should never change
        assertEquals(creationTime, session.getCreationTime());
    }

    /**
     * Test that HttpSessionListeners are notified when sessions are created and/or destroyed.
     */
    @SuppressWarnings("unchecked")
    public void testHttpSessionListener(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String[] expectCreated = request.getParameterValues("sessionCreated");
        String[] expectDestroyed = request.getParameterValues("sessionDestroyed");
        String[] expectNotDestroyed = request.getParameterValues("sessionNotDestroyed");

        String listenerClassName = "session.cache.web." + request.getParameter("listener") + ".SessionListener"; // listener1 or listener2
        Class<HttpSessionListener> sessionListenerClass = (Class<HttpSessionListener>) Class.forName(listenerClassName);

        LinkedBlockingQueue<String> created = (LinkedBlockingQueue<String>) sessionListenerClass.getField("created").get(null);
        LinkedBlockingQueue<String> destroyed = (LinkedBlockingQueue<String>) sessionListenerClass.getField("destroyed").get(null);

        if (expectCreated != null)
            for (String sessionId : expectCreated)
                assertTrue(sessionId, created.contains(sessionId));

        if (expectDestroyed != null)
            for (String sessionId : expectDestroyed)
                assertTrue(sessionId, destroyed.contains(sessionId));

        if (expectNotDestroyed != null)
            for (String sessionId : expectNotDestroyed)
                assertFalse(sessionId, destroyed.contains(sessionId));
    }

    /**
     * Test that the last accessed time changes when accessed at different times.
     */
    public void testLastAccessedTime(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        HttpSession session = request.getSession(true);
        long lastAccessedTime = session.getLastAccessedTime();

        TimeUnit.MILLISECONDS.sleep(100); // ensure that the time changes before next access

        assertEquals(lastAccessedTime, session.getLastAccessedTime());

        session.setAttribute("testLastAccessedTime-key1", 2.71828);

        // last accessed time should change
        assertNotSame(lastAccessedTime, session.getLastAccessedTime());
    }

    /**
     * Begin the testing of session attribute serialization. Create the
     * session and set attributes.
     */
    public void testSerialization(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        Map<String, Object> sessionMap = new HashMap<String, Object>();
        session.setAttribute("map", sessionMap);
        System.out.println("Session is: " + session.getId());
        System.out.println("Session map is: " + sessionMap);

        // Test serialization of various types directly (setAttribute) and indirectly (HashMap).

        // String property
        String str = "STRING_PROP";
        session.setAttribute("str", str);
        sessionMap.put("str", str);

        // AppObject property
        AppObject object = new AppObject();
        session.setAttribute("appObject", object);
        sessionMap.put("appObject", object);
    }

    /**
     * Complete the testing of session attribute serialization. The call to
     * getSession will require deserializing the object.
     */
    public void testSerialization_complete(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        HttpSession session = request.getSession(false);
        @SuppressWarnings("unchecked")
        Map<String, Object> sessionMap = (Map<String, Object>) session.getAttribute("map");
        System.out.println("Session is: " + session.getId());
        System.out.println("Session map is: " + sessionMap);

        // String
        String str = (String) session.getAttribute("str");
        assertEquals("direct String value not deserialized properly", "STRING_PROP", str);
        str = (String) sessionMap.get("str");
        assertEquals("indirect String value not deserialized properly", "STRING_PROP", str);

        // AppObject
        AppObject object = (AppObject) session.getAttribute("appObject");
        assertNotNull("The appObject was not found in the HTTP session", object);
        assertTrue("direct AppObject not deserialized properly", object.deserialized);
        object = (AppObject) sessionMap.get("appObject");
        assertNotNull("The indirect appObject was not found in the HTTP session", object);
        assertTrue("indirect AppObject not deserialized properly", object.deserialized);

        ArrayList<String> attributeNames = Collections.list(session.getAttributeNames());
        String attributeNamesString = attributeNames.toString();
        assertTrue(attributeNamesString, attributeNames.containsAll(Arrays.asList("map", "str", "appObject")));
        assertEquals(attributeNamesString, 3, attributeNames.size());
    }

    public void testSerializeDataSource(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        HttpSession session = request.getSession();
        Map<String, Object> sessionMap = new HashMap<String, Object>();
        session.setAttribute("map", sessionMap);
        System.out.println("Session is: " + session.getId());
        System.out.println("Session map is: " + sessionMap);

        // DataSource
        DataSource ds = InitialContext.doLookup("java:comp/env/jdbc/derbyRef");
        session.setAttribute("dataSource", ds);
        sessionMap.put("dataSource", ds);
        ds.getConnection().close();
    }

    public void testSerializeDataSource_complete(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        HttpSession session = request.getSession(false);
        @SuppressWarnings("unchecked")
        Map<String, Object> sessionMap = (Map<String, Object>) session.getAttribute("map");
        System.out.println("Session is: " + session.getId());
        System.out.println("Session map is: " + sessionMap);

        // DataSource
        DataSource sessionDS = (DataSource) session.getAttribute("dataSource");
        assertNotNull("The dataSource was not found in the HTTP session", sessionDS);
        sessionDS.getConnection().close();
        sessionDS = (DataSource) sessionMap.get("dataSource");
        assertNotNull("The indirect dataSource was not found in the HTTP session", sessionDS);
        sessionDS.getConnection().close();
    }

    /**
     * Expects that the session is either empty, or if it exists it should not have any of the attributes set
     */
    public void testSessionEmpty(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        HttpSession session = request.getSession(false);
        if (session == null) {
            System.out.println("Session was null");
            return;
        }

        assertNull(session.getAttribute("str"));
        assertNull(session.getAttribute("appObject"));
        assertFalse(session.getAttributeNames().hasMoreElements());
    }

    public void sessionPut(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        boolean createSession = Boolean.parseBoolean(request.getParameter("createSession"));
        HttpSession session = request.getSession(createSession);
        if (createSession)
            System.out.println("Created a new session with sessionID=" + session.getId());
        else
            System.out.println("Re-using existing session with sessionID=" + session == null ? null : session.getId());
        String key = request.getParameter("key");
        String value = request.getParameter("value");
        String type = request.getParameter("type");
        Object val = toType(type, value);
        session.setAttribute(key, val);
        String sessionID = session.getId();
        System.out.println("Put entry: " + key + '=' + value + " into sessionID=" + sessionID);
        response.getWriter().write("session id: [" + sessionID + "]");
    }

    public void sessionGet(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        String key = request.getParameter("key");
        String rawExpectedValue = request.getParameter("expectedValue");
        String type = request.getParameter("type");
        boolean compareAsString = Boolean.parseBoolean(request.getParameter("compareAsString")); // useful if the class does not implement .equals
        Object expectedValue = toType(type, rawExpectedValue);

        HttpSession session = request.getSession(false);
        if (expectedValue == null && session == null) {
            System.out.println("Session was null and was expecting null value.");
            return;
        } else if (session == null) {
            fail("Was expecting to get " + key + '=' + expectedValue + ", but instead got a null session.");
        }
        Object actualValue = session.getAttribute(key);
        System.out.println("Got entry: " + key + '=' + actualValue + " from sessionID=" + session.getId());

        if (compareAsString)
            assertEquals(expectedValue.toString(), actualValue.toString());
        else
            assertEquals(expectedValue, actualValue);
    }

    /**
     * Get a session attribute which is a StringBuffer and append characters,
     * but don't set the attribute with the updated value.
     */
    public void testStringBufferAppendWithoutSetAttribute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String key = request.getParameter("key");
        HttpSession session = request.getSession(true);
        StringBuffer value = (StringBuffer) session.getAttribute(key);
        value.append("Appended");
    }

    /**
     * Convert a String value to the specified type.
     * This is valid for the primitive wrapper classes (such as java.lang.Integer)
     * and any other type that has a single argument String constructor.
     */
    private static Object toType(String type, String s) throws Exception {
        if (s == null || "null".equals(s))
            return null;

        if (Character.class.getName().equals(type))
            return s.charAt(0);

        return Class.forName(type).getConstructor(String.class).newInstance(s);
    }
}
