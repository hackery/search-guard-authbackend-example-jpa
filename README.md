Minimal example to show issue described in

https://groups.google.com/forum/#!topic/search-guard/jMTV9Rq5d6I
"AccessControlException for getClassLoader in custom AuthenticationBackend
"

AccessControlException: access denied ("java.lang.RuntimePermission" "getClassLoader")

Output trace:

```
[2018-06-13T18:47:26,737][INFO ][c.e.e.s.a.MyAuthenticationBackend] Starting up authentication backend: {"client.type":"node","cluster.name":"jb-laptop-cluster-6.2.2","cluster.routing.allocation.disk.threshold_enabled":"false","discovery.zen.minimum_master_nodes":"1","network.host":["_local_","_site_"],"network.publish_host":"_local_","node.max_local_storage_nodes":"3","node.name":"jb-laptop-1","path.data":["C:\\dev\\elastic\\data"],"path.home":"C:\\dev\\elastic\\6.2.2\\elasticsearch-6.2.2","path.logs":"C:\\dev\\elastic\\logs","reindex.remote.whitelist":"hermes-elastic.qa.pyr:9200","searchguard.allow_default_init_sgindex":"true","searchguard.allow_unsafe_democertificates":"true","searchguard.audit.type":"internal_elasticsearch","searchguard.authcz.admin_dn":["CN=kirk,OU=client,O=client,L=test, C=de"],"searchguard.check_snapshot_restore_write_privileges":"true","searchguard.enable_snapshot_restore_privilege":"true","searchguard.enterprise_modules_enabled":"true","searchguard.restapi.roles_enabled":["sg_all_access"],"searchguard.ssl.http.enabled":"true","searchguard.ssl.http.pemcert_filepath":"esnode.pem","searchguard.ssl.http.pemkey_filepath":"esnode-key.pem","searchguard.ssl.http.pemtrustedcas_filepath":"root-ca.pem","searchguard.ssl.transport.enforce_hostname_verification":"false","searchguard.ssl.transport.pemcert_filepath":"esnode.pem","searchguard.ssl.transport.pemkey_filepath":"esnode-key.pem","searchguard.ssl.transport.pemtrustedcas_filepath":"root-ca.pem","security.manager.filter_bad_defaults":"true","xpack.graph.enabled":"true","xpack.monitoring.enabled":"true","xpack.security.enabled":"false","xpack.watcher.enabled":"true"}, C:\dev\elastic\6.2.2\elasticsearch-6.2.2\config [2018-06-13T18:47:26,746][WARN ][c.f.s.s.ReflectionHelper ] Unable to enable 'com.example.es.sg.auth.MyAuthenticationBackend' due to java.lang.reflect.InvocationTargetException
[2018-06-13T18:47:26,749][ERROR][c.f.s.a.BackendRegistry  ] Unable to initialize auth domain my-auth due to ElasticsearchException[java.lang.reflect.InvocationTargetException]; nested: InvocationTargetException; nested: AccessControlException[access denied ("java.lang.RuntimePermission" "getClassLoader")];
org.elasticsearch.ElasticsearchException: java.lang.reflect.InvocationTargetException
        at com.floragunn.searchguard.support.ReflectionHelper.instantiateAAA(ReflectionHelper.java:184) ~[search-guard-6-6.2.2-22.1.jar:6.2.2-22.1]
        at com.floragunn.searchguard.auth.BackendRegistry.newInstance(BackendRegistry.java:668) ~[search-guard-6-6.2.2-22.1.jar:6.2.2-22.1]
        at com.floragunn.searchguard.auth.BackendRegistry.onChange(BackendRegistry.java:230) [search-guard-6-6.2.2-22.1.jar:6.2.2-22.1]
        at com.floragunn.searchguard.configuration.IndexBaseConfigurationRepository.notifyAboutChanges(IndexBaseConfigurationRepository.java:361) [search-guard-6-6.2.2-22.1.jar:6.2.2-22.1]
        at com.floragunn.searchguard.configuration.IndexBaseConfigurationRepository.reloadConfiguration(IndexBaseConfigurationRepository.java:311) [search-guard-6-6.2.2-22.1.jar:6.2.2-22.1]
        at com.floragunn.searchguard.configuration.IndexBaseConfigurationRepository$1$1.run(IndexBaseConfigurationRepository.java:171) [search-guard-6-6.2.2-22.1.jar:6.2.2-22.1]
        at java.lang.Thread.run(Thread.java:748) [?:1.8.0_161]
Caused by: java.lang.reflect.InvocationTargetException
        at sun.reflect.NativeConstructorAccessorImpl.newInstance0(Native Method) ~[?:?]
        at sun.reflect.NativeConstructorAccessorImpl.newInstance(NativeConstructorAccessorImpl.java:62) ~[?:?]
        at sun.reflect.DelegatingConstructorAccessorImpl.newInstance(DelegatingConstructorAccessorImpl.java:45) ~[?:?]
        at java.lang.reflect.Constructor.newInstance(Constructor.java:423) ~[?:1.8.0_161]
        at com.floragunn.searchguard.support.ReflectionHelper.instantiateAAA(ReflectionHelper.java:176) ~[?:?]
        ... 6 more
Caused by: java.security.AccessControlException: access denied ("java.lang.RuntimePermission" "getClassLoader")
        at java.security.AccessControlContext.checkPermission(AccessControlContext.java:472) ~[?:1.8.0_161]
        at java.security.AccessController.checkPermission(AccessController.java:884) ~[?:1.8.0_161]
        at java.lang.SecurityManager.checkPermission(SecurityManager.java:549) ~[?:1.8.0_161]
        at java.lang.ClassLoader.checkClassLoaderPermission(ClassLoader.java:1528) ~[?:1.8.0_161]
        at java.lang.Thread.getContextClassLoader(Thread.java:1443) ~[?:1.8.0_161]
        at javax.persistence.spi.PersistenceProviderResolverHolder$PersistenceProviderResolverPerClassLoader.getContextualClassLoader(PersistenceProviderResolverHolder.java:101) ~[?:?]
        at javax.persistence.spi.PersistenceProviderResolverHolder$PersistenceProviderResolverPerClassLoader.getPersistenceProviders(PersistenceProviderResolverHolder.java:76) ~[?:?]
        at javax.persistence.Persistence.getProviders(Persistence.java:69) ~[?:?]
        at javax.persistence.Persistence.createEntityManagerFactory(Persistence.java:53) ~[?:?]
        at javax.persistence.Persistence.createEntityManagerFactory(Persistence.java:39) ~[?:?]
        at com.example.es.sg.auth.db.Database.<init>(Database.java:19) ~[?:?]
        at com.example.es.sg.auth.MyAuthenticationBackend.<init>(MyAuthenticationBackend.java:31) ~[?:?]
        at sun.reflect.NativeConstructorAccessorImpl.newInstance0(Native Method) ~[?:?]
        at sun.reflect.NativeConstructorAccessorImpl.newInstance(NativeConstructorAccessorImpl.java:62) ~[?:?]
        at sun.reflect.DelegatingConstructorAccessorImpl.newInstance(DelegatingConstructorAccessorImpl.java:45) ~[?:?]
        at java.lang.reflect.Constructor.newInstance(Constructor.java:423) ~[?:1.8.0_161]
        at com.floragunn.searchguard.support.ReflectionHelper.instantiateAAA(ReflectionHelper.java:176) ~[?:?]
        ... 6 more
```