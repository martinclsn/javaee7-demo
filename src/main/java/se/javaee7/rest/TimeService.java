package se.javaee7.rest;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.LocalDateTime;
import java.util.logging.Logger;

@Path("time")
@Produces(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class TimeService {

    @Inject
    Logger logger;

    @Path("now")
    @GET
    public Response now() {
        Now now = new Now();
        logger.info("Returning " + now.time);
        return Response.ok(now).build();
    }

    public static class Now {
        public String time = LocalDateTime.now().toString();
    }
}
