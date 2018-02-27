package se.jee.dao;

import se.jee.logger.TransactionLogger;
import se.jee.entity.TimeEntity;
import se.jee.logger.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
@Interceptors(TransactionLogger.class)
public class TimeEjbDao {

    @PersistenceContext
    EntityManager em;

    @Inject
    Logger logger;

    public TimeEntity save(TimeEntity timeEntity) {
        logger.logTxStatus();
        em.persist(timeEntity);
        return timeEntity;
    }

    public TimeEntity findById(long id) {
        logger.logTxStatus();
        return em.find(TimeEntity.class, id);
    }


}
