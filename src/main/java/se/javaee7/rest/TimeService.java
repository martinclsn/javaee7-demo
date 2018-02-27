package se.javaee7.rest;

import org.apache.log4j.Logger;
import se.javaee7.ejb.TimeEjbDao;
import se.javaee7.entity.TimeEntity;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.LocalDateTime;

@Path("time")
@Produces(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class TimeService {

    @Inject
    Logger logger;

    @Inject
    TimeEjbDao timeEjbDao;

    @Path("now")
    @GET
    public Response now() {
        TimeEntity timeEntity = new TimeEntity(LocalDateTime.now().toString());
        timeEntity = timeEjbDao.save(timeEntity);
        logger.info("Returning " + timeEntity.getTime());
        return Response.ok(timeEntity).build();
    }

}
