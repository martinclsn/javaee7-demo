package se.jee.rest;

import se.jee.dao.TimeEjbDao;
import se.jee.entity.TimeEntity;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.function.Supplier;

import static javax.ws.rs.core.Response.Status.NOT_FOUND;

@Path("time/v1")
@Produces(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class TimeServiceV1 {

    @Inject
    TimeEjbDao timeEjbDao;

    @GET
    public Response ejbNow() {
        String time = LocalDateTime.now().toString();
        TimeEntity timeEntity = timeEjbDao.save(new TimeEntity(time));
        return Response.ok(timeEntity).build();
    }

    @Path("{id}")
    @GET
    public Response ejbGetById(@PathParam("id") long id) {
        TimeEntity timeEntity = timeEjbDao.findById(id).orElseThrow(() -> new WebApplicationException("Not found", NOT_FOUND));
        return Response.ok(timeEntity).build();
    }

}
