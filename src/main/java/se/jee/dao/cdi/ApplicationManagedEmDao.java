package se.jee.dao.cdi;

import se.jee.logger.TransactionLogger;
import se.jee.entity.TimeEntity;
import se.jee.logger.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@ApplicationScoped
@Interceptors(TransactionLogger.class)
public class ApplicationManagedEmDao {

    @Inject
    EntityManager em;

    @Inject
    Logger logger;

    @Transactional
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