package se.jee.rest;

import se.jee.dao.cdi.ApplicationManagedEmDao;
import se.jee.entity.TimeEntity;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.LocalDateTime;

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
        TimeEntity timeEntity = applicationManagedEmDao.findById(id);
        return Response.ok(timeEntity).build();
    }


}
