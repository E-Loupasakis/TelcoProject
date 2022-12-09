package gr.codehub.telco.telcoproject.resource;
import gr.codehub.telco.telcoproject.model.User;
import gr.codehub.telco.telcoproject.service.CustomerService;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("/customers")
public class UserResource {

    @Inject
    private CustomerService customerService;

    @Path("/")
    @POST
    @PermitAll
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public gr.codehub.telco.telcoproject.model.User insert(gr.codehub.telco.telcoproject.model.User customer) {
        return customerService.create(customer);
    }


    @Path("/{customerId}")
    @DELETE
    public void delete(@PathParam("customerId") long customerId){
        customerService.delete(customerId);
    }

    @Path("/{customerId}")
    @PUT
    @PermitAll
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public User update(@PathParam("customerId") long customerId, User customer){
        customer.setId(customerId);
        return customerService.update(customer);
    }
    @Path("/{customerId}")
    @GET
    @RolesAllowed("ADMIN")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public User read(@PathParam("customerId") long customerId){
        return customerService.read(customerId);
    }
    @Path("/")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> read(){
        return customerService.read();
    }

    @Path("/find/vat/{vatNumber}")
    @GET
    @RolesAllowed("ADMIN")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public User readByVat(@PathParam("vatNumber") int vatNumber){
        return customerService.readByVat(vatNumber);
    }
    @Path("/find/email/{email}")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> getCustomerByEmail(@PathParam("email") String email){
        return (List<User>) customerService.read(email);
    }





}