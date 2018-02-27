package se.jee.dao;

import se.jee.logger.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

@ApplicationScoped
public class EntityManagerProducer {

    @Inject
    Logger logger;

    @PersistenceUnit
    EntityManagerFactory entityManagerFactory;

    @Produces
    @RequestScoped //Since EntityManager is not thread-safe
    public EntityManager createEntityManager() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        logger.info("EntityManager created: " + entityManager);
        return entityManager;
    }

    void closeMyObject(@Disposes EntityManager entityManager) {
        if(entityManager.isOpen()) {
            entityManager.close();
        }
        logger.info("EntityManager closed: " + entityManager);
    }

}
