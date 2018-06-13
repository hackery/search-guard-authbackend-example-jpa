package com.example.es.sg.auth;

import com.example.es.sg.auth.db.Database;
import com.example.es.sg.auth.db.entities.BackendUser;
import com.floragunn.searchguard.auth.AuthenticationBackend;
import com.floragunn.searchguard.user.AuthCredentials;
import com.floragunn.searchguard.user.User;
import com.google.common.collect.Lists;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.elasticsearch.ElasticsearchSecurityException;
import org.elasticsearch.common.settings.Settings;

import java.nio.file.Path;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.List;

public class MyAuthenticationBackend implements AuthenticationBackend {
    protected final Logger log = LogManager.getLogger(this.getClass());

    Database database;

    // for test cases
    MyAuthenticationBackend(Database database) {
        this.database = database;
    }

    // Search Guard integration entry point
    public MyAuthenticationBackend(final Settings settings, final Path configPath) {
        super();
        log.info("Starting up authentication backend: {}, {}", settings, configPath);
        
        this.database = AccessController.doPrivileged(new PrivilegedAction<Database>() {
            
            @Override
            public Database run() {       
                return new Database();
            }
        });
    }

    protected Database getDatabase() {
        return database;
    }

    public String getType() {
        return "my-auth";
    }

    public boolean exists(User user) {
        log.info("exists({})", user.getName());

        if (database.getUser(user.getName()) != null) {
            log.info("Found user {} in DB", user.getName());
            return true;
        }
        return false;
    }

    public User authenticate(AuthCredentials authCredentials) throws ElasticsearchSecurityException {
        log.info("authenticate({})", authCredentials.getUsername());

        BackendUser userFromDb = database.getUser(authCredentials.getUsername());

        if (userFromDb != null
                && userFromDb.getPlaintextPassword().equals(new String(authCredentials.getPassword()))) {

            List<String> rolesReturned = Lists.newArrayList(userFromDb.getRole());
            return new User(authCredentials.getUsername(),rolesReturned, null);
        }

        return null;
    }
}
