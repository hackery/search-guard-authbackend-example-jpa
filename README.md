Minimal example to show issue described in https://groups.google.com/forum/#!topic/search-guard/jMTV9Rq5d6I

To make it work:

    mvn clean package
    cp target/custom-authentication-backend-1.0-SNAPSHOT-jar-with-dependencies.jar plugins/search-guard-6/

To make it work the plugin-security.policy needs to be contain the following entries:
  
    permission java.lang.RuntimePermission "createClassLoader";
    permission java.util.PropertyPermission "jboss.i18n.generate-proxies","write";