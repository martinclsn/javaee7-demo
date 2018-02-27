package se.javaee7.ejb;

import se.javaee7.entity.TimeEntity;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class TimeEjbDao {

    @PersistenceContext
    EntityManager em;

    public TimeEntity save(TimeEntity timeEntity) {
        em.persist(timeEntity);
        return timeEntity;
    }


}
