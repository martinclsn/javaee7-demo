package se.jee.dao.cdi;

import se.jee.entity.TimeEntity;
import se.jee.logger.DaoLoggerInterceptor;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.Optional;

@ApplicationScoped
@Interceptors(DaoLoggerInterceptor.class)
public class ApplicationManagedEmDao {

    @Inject
    EntityManager em;

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
