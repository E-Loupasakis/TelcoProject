package gr.codehub.telco.telcoproject;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

@Path("/create")
public class HelloResource {
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public String hello() {
        return "Hello, World!";
    }
}