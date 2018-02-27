package se.jee.rest;

import se.jee.dao.TimeEjbDao;
import se.jee.entity.TimeEntity;
import se.jee.logger.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.LocalDateTime;

@Path("time/v1")
@Produces(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class TimeServiceV1 {

    @Inject
    Logger logger;

    @Inject
    TimeEjbDao timeEjbDao;

    @GET
    public Response ejbNow() {
        String time = LocalDateTime.now().toString();
        TimeEntity timeEntity = timeEjbDao.save(new TimeEntity(time));
        logger.logTxStatus();
        return Response.ok(timeEntity).build();
    }

    @Path("{id}")
    @GET
    public Response ejbGetById(@PathParam("id") long id) {
        TimeEntity timeEntity = timeEjbDao.findById(id);
        logger.logTxStatus();
        return Response.ok(timeEntity).build();
    }

}
