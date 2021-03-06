package se.jee.rest;

import se.jee.dao.cdi.ContainerManagedEmDao;
import se.jee.entity.TimeEntity;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.LocalDateTime;

import static javax.ws.rs.core.Response.Status.NOT_FOUND;

@Path("time/v2")
@Produces(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class TimeServiceV2 {

    @Inject
    ContainerManagedEmDao containerManagedEmDao;

    @GET
    public Response cdiNow() {
        TimeEntity timeEntity = containerManagedEmDao.save(new TimeEntity(LocalDateTime.now().toString()));
        return Response.ok(timeEntity).build();
    }

    @GET
    @Path("{id}")
    public Response cdiGetById(@PathParam("id") long id) {
        TimeEntity timeEntity = containerManagedEmDao.findById(id).orElseThrow(() -> new WebApplicationException("Not found", NOT_FOUND));
        return Response.ok(timeEntity).build();
    }


}
