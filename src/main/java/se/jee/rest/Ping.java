package se.jee.rest;

import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("ping")
public class Ping {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response json() {
        JsonObject jsonObject = Json.createObjectBuilder().add("response", "pong").build();
        return Response.ok(jsonObject).build();
    }
}
