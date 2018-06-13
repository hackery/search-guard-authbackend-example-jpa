package com.example.es.sg.auth.db;

import com.example.es.sg.auth.db.entities.BackendUser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Database {
    private static final String PERSISTENCE_UNIT_NAME = "com.example.db.users";
    private final Logger log = LogManager.getLogger(this.getClass());
    private static EntityManagerFactory entityManagerFactory;
    protected final EntityManager entityManager;

    public Database() {
        entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        entityManager = entityManagerFactory.createEntityManager();
    }

    public BackendUser getUser(String userId) {
        //User user = entityManager.find(User.class, userId);
        BackendUser user = entityManager
                .createQuery( "from USERS where userId = :userId", BackendUser.class )
                .setParameter("userId", userId)
                .getSingleResult();

        if (user != null)
            log.info("getUser: found {}", user.toString());
        else
            log.info("getUser: not found: {}", userId);

        return user;
    }
}
