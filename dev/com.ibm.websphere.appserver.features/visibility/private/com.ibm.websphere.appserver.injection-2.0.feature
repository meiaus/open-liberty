-include= ~${workspace}/cnf/resources/bnd/feature.props
symbolicName=com.ibm.websphere.appserver.injection-2.0
IBM-Process-Types: client, \
 server
-features=com.ibm.websphere.appserver.containerServices-1.0, \
 com.ibm.websphere.appserver.anno-1.0
-bundles=com.ibm.ws.injection.jakarta
kind=noship
edition=full
WLP-Activation-Type: parallel
