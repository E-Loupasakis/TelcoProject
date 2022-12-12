package gr.codehub.telco.telcoproject.resource;

import gr.codehub.telco.telcoproject.dto.CustomerDto;
import gr.codehub.telco.telcoproject.model.Ticket;
import gr.codehub.telco.telcoproject.model.User;
import gr.codehub.telco.telcoproject.service.CustomerService;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
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
    public User insert(@Valid User customer) {
        return customerService.create(customer);
    }

    @Path("/admin/{code}")
    @POST
    @PermitAll
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public User createThatAdmin(@Valid User customer, @PathParam("code") String code)
    {   if(code.equals("908asu998ikk00oikl")) return customerService.create(customer);
        return null;
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
    public User update(@PathParam("customerId") long customerId, @Valid User customer){
        customer.setId(customerId);
        return customerService.update(customer);
    }
    @Path("/{customerId}")
    @GET
    @PermitAll
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
        return customerService.read(email);
    }


    @Path("/find/tickets/{customerId}")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<Ticket> getTicketsByCustomerId(@PathParam("customerId") long customerId){
        return customerService.findTicketsByCustomerId(customerId);
    }


}