package se.jee.dao;

import se.jee.entity.TimeEntity;
import se.jee.logger.DaoLoggerInterceptor;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@Stateless
@Interceptors(DaoLoggerInterceptor.class)
public class TimeEjbDao {

    @PersistenceContext
    EntityManager em;

    @Inject
    Event<TimeEntity> event;

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
