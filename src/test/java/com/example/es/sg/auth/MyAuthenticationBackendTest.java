package com.example.es.sg.auth;

import com.example.es.sg.auth.db.Database;
import com.example.es.sg.auth.db.DatabaseTest;
import com.example.es.sg.auth.db.entities.BackendUser;
import com.floragunn.searchguard.auth.AuthenticationBackend;
import com.floragunn.searchguard.user.AuthCredentials;
import com.floragunn.searchguard.user.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import static org.junit.Assert.*;

public class MyAuthenticationBackendTest {
    Database database = DatabaseTest.initDatabase();
    AuthenticationBackend backend;

    private final String TEST_USER = "jmb";
    private final String TEST_PASS = "password";
    private final String TEST_ROLE = "admin";

    @Before
    public void setUp() throws Exception {
        backend = new MyAuthenticationBackend(database);
    }

    @Test
    public void authenticate() throws Exception {
        AuthCredentials creds = new AuthCredentials(TEST_USER, TEST_PASS.getBytes());
        User user = backend.authenticate(creds);

        Assert.assertNotNull("Returned user is not null", user);
        Assert.assertEquals("Returned user has same name as requested", user.getName(), TEST_USER);
        Assert.assertTrue("Returned user has expected role", user.isUserInRole(TEST_ROLE));

    }

}