package gr.codehub.telco.telcoproject.resource;

import gr.codehub.telco.telcoproject.model.Ticket;
import gr.codehub.telco.telcoproject.service.TicketService;
import gr.codehub.telco.telcoproject.service.impl.TicketServiceImpl;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.time.LocalDate;
import java.util.List;

@Path("/tickets")
public class TicketResource {

    @Inject
    private TicketServiceImpl ticketService;

    @POST
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Ticket insert(Ticket ticket) {
        return ticketService.createTicket(ticket);
    }

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Ticket> findAll () {
        return ticketService.findAll();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Ticket findByTicketId(@PathParam("id") Long id) {
        return ticketService.findByTicketId(id);
    }

    @PUT
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Ticket update(Ticket ticket) {
        return ticketService.update(ticket);
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public boolean delete(@PathParam("id") Long id) {
        return ticketService.delete(id);
    }

    @POST
    @Path("/search-by-dates")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public List<Ticket> findByDateRange(LocalDate dateFrom, LocalDate dateTo) {
        return ticketService.findByDateRange(dateFrom, dateTo);
    }

    @POST
    @Path("/search-by-date")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public List<Ticket> findByDate(LocalDate date) {
        return ticketService.findByDate(date);
    }

    @GET
    @Path("/search-by-customer-id/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public List<Ticket> findByCustomerId(@PathParam("id") Long id) {
        return ticketService.getTicketsByCustomerId(id);
    }




}
