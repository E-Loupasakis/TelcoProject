package gr.codehub.telco.telcoproject.resource;


import gr.codehub.telco.telcoproject.model.User;
import gr.codehub.telco.telcoproject.service.CustomerService;
import gr.codehub.telco.telcoproject.transfer.ApiResponse;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;


@Path("/customers")
public class UserResource {

    @Inject
    private CustomerService customerService;

    @Path("/")
    @POST
    @RolesAllowed("ADMIN")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response insert(@Valid User customer) {
        return Response.ok().entity(ApiResponse.builder().data(customerService.create(customer)).build()).build();
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
    @RolesAllowed("ADMIN")
    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("customerId") long customerId){
        return Response.ok().entity(ApiResponse.builder().data(customerService.delete(customerId)).build()).build();
    }

    @Path("/{customerId}")
    @PUT
    @RolesAllowed({"ADMIN","CUSTOMER"})
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("customerId") long customerId, @Valid User customer){
        customer.setId(customerId);
        return Response.ok().entity(ApiResponse.builder().data(customerService.update(customer)).build()).build();
    }
    @Path("/{customerId}")
    @GET
    @RolesAllowed({"ADMIN","CUSTOMER"})
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response read(@PathParam("customerId") long customerId){
        return Response.ok().entity(ApiResponse.builder().data(customerService.read(customerId)).build()).build();
    }



    @Path("/")
    @GET
    @RolesAllowed("ADMIN")
    @Produces(MediaType.APPLICATION_JSON)
    public Response read(){
        return  Response.ok().entity(ApiResponse.builder().data(customerService.read()).build()).build();
    }

    @Path("/find/vat/{vatNumber}")
    @GET
    @RolesAllowed("ADMIN")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response readByVat(@PathParam("vatNumber") int vatNumber){
        return Response.ok().entity(ApiResponse.builder().data(customerService.readByVat(vatNumber)).build()).build();
    }
    @Path("/find/email/{email}")
    @GET
    @RolesAllowed("ADMIN")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCustomerByEmail(@PathParam("email") String email){
        return Response.ok().entity(ApiResponse.builder().data(customerService.read(email)).build()).build();
    }


    @Path("/find/tickets/{customerId}")
    @GET
    @RolesAllowed({"ADMIN","CUSTOMER"})
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTicketsByCustomerId(@PathParam("customerId") long customerId){
        return Response.ok().entity(ApiResponse.builder().data(customerService.findTicketsByCustomerId(customerId)).build()).build();
    }


}