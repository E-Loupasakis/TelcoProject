package gr.codehub.telco.telcoproject.resource;

import gr.codehub.telco.telcoproject.dto.CustomerDto;
import gr.codehub.telco.telcoproject.service.CustomerService;
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
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public CustomerDto insert(CustomerDto customer) {
        return customerService.create(customer);
    }


    @Path("/{customerId}")
    @DELETE
    public void delete(@PathParam("customerId") long customerId){
        customerService.delete(customerId);
    }

    @Path("/{customerId}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public CustomerDto update(@PathParam("customerId") long customerId,  CustomerDto customer){
        customer.setId(customerId);
        return customerService.update(customer);
    }
    @Path("/{customerId}")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public CustomerDto read(@PathParam("customerId") long customerId){
        return customerService.read(customerId);
    }
    @Path("/")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<CustomerDto> read(){
        return customerService.read();
    }

    @Path("/find/vat/{vatNumber}")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public CustomerDto readByVat(@PathParam("vatNumber") int vatNumber){
        return customerService.readByVat(vatNumber);
    }
    @Path("/find/email/{email}")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<CustomerDto> getCustomerByEmail(@PathParam("email") String email){
        return (List<CustomerDto>) customerService.read(email);
    }





}