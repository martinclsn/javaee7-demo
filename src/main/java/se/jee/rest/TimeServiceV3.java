package se.jee.rest;

import se.jee.dao.cdi.ApplicationManagedEmDao;
import se.jee.entity.TimeEntity;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.LocalDateTime;

import static javax.ws.rs.core.Response.Status.NOT_FOUND;

@Path("time/v3")
@Produces(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class TimeServiceV3 {

    @Inject
    ApplicationManagedEmDao applicationManagedEmDao;

    @GET
    public Response cdiNow() {
        TimeEntity timeEntity = applicationManagedEmDao.save(new TimeEntity(LocalDateTime.now().toString()));
        return Response.ok(timeEntity).build();
    }

    @Path("{id}")
    @GET
    public Response cdiGetById(@PathParam("id") long id) {
        TimeEntity timeEntity = applicationManagedEmDao.findById(id).orElseThrow(() -> new WebApplicationException("Not found", NOT_FOUND));
        return Response.ok(timeEntity).build();
    }


}
