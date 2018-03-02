package se.jee.dao.cdi;

import org.apache.log4j.Logger;
import se.jee.entity.TimeEntity;
import se.jee.logger.DaoLoggerInterceptor;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Optional;

@RequestScoped //Since EntityManager is not thread-safe
@Interceptors(DaoLoggerInterceptor.class)
public class ContainerManagedEmDao {

    @PersistenceContext
    EntityManager em;

    @Inject
    Logger logger;

    @Inject
    Event<TimeEntity> event;

    @Transactional
    public TimeEntity save(TimeEntity timeEntity) {
        em.persist(timeEntity);
        event.fire(timeEntity);
        return timeEntity;
    }

    public Optional<TimeEntity> findById(long id) {
        Optional<TimeEntity> entityOptional = Optional.ofNullable(em.find(TimeEntity.class, id));
        entityOptional.ifPresent(entity -> event.fire(entity));
        return entityOptional;
    }


}
