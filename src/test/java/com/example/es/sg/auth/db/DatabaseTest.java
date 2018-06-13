package com.example.es.sg.auth.db;

import com.example.es.sg.auth.db.entities.BackendUser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;


public class DatabaseTest {
    private final Logger log = LogManager.getLogger(this.getClass());
    private Database database = initDatabase();

    private final static String TEST_USER = "jmb";
    private final static String TEST_PASS = "password";
    private final static String TEST_ROLE = "admin";

    public static Database initDatabase() {
        Database database = new Database();
        EntityManager em = database.entityManager;
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(new BackendUser(TEST_USER,
                TEST_PASS, TEST_ROLE));
        em.persist(new BackendUser(TEST_USER+"1",
                TEST_PASS, TEST_ROLE));
        em.persist(new BackendUser(TEST_USER+"2",
                TEST_PASS, TEST_ROLE));
        tx.commit();

        return database;
    }

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void getUser() throws Exception {
        BackendUser user = database.getUser(TEST_USER);
        Assert.assertNotNull("User returned is not null", user);
        Assert.assertEquals("User returned is same id as requested", TEST_USER, user.getUserId());
        Assert.assertTrue("User has admin role", user.getRole().contains(TEST_ROLE));
    }
}