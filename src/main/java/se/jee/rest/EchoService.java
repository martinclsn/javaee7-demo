package se.jee.rest;

import javax.json.Json;
import javax.json.JsonObject;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@Path("echo")
public class EchoService {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response echo(@Valid EchoRequest echoRequest) {
        JsonObject jsonObject = Json.createObjectBuilder().add("message", echoRequest.message).build();
        return Response.ok(jsonObject).build();
    }

    @XmlRootElement
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class EchoRequest {
        @NotNull
        @Size(min = 5, max= 100)
        @Pattern(regexp = "^[a-zA-Z0-9]*$")
        String message;
    }
}
