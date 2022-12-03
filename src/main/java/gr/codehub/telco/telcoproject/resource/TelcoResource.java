package gr.codehub.telco.telcoproject.resource;

import gr.codehub.telco.telcoproject.dto.CustomerDto;
import gr.codehub.telco.telcoproject.dto.TicketDto;
import gr.codehub.telco.telcoproject.service.CustomerService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

@Path("/customers")
public class TelcoResource {

    @Inject
    private CustomerService customerService;

    @Path("/create")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public CustomerDto insert(CustomerDto customer) {
        return customerService.create(customer);
    }


    @Path("/delete/{customerId}")
    @DELETE
    public void delete(@PathParam("customerId") long customerId){
        customerService.delete(customerId);
    }

//    @Path("/ticket")
//    @POST
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces(MediaType.APPLICATION_JSON)
//    public TicketDto insert(TicketDto ticket) {
//        return ticketService.create(ticket);
//    }

}