package gr.codehub.telco.telcoproject.resource;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import gr.codehub.telco.telcoproject.dto.TicketDto;
import gr.codehub.telco.telcoproject.model.Ticket;
import gr.codehub.telco.telcoproject.service.TicketService;
import gr.codehub.telco.telcoproject.service.impl.TicketServiceImpl;
import gr.codehub.telco.telcoproject.transfer.ApiResponse;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


@Path("/tickets")
public class TicketResource {

    private static final Logger logger = LoggerFactory.getLogger(TicketResource.class);

    @Inject
    private TicketServiceImpl ticketService;

    @POST
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @RolesAllowed("ADMIN")
    public Response insert(@Valid Ticket ticket) {
        ticket.setDateTimeOfCreation(LocalDateTime.now());
       //return ticketService.create(ticket);
        return Response.ok().entity(ApiResponse.builder().data(ticketService.create(ticket)).build()).build();
    }

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({"ADMIN","CUSTOMER"})
    public Response findAll () {

       // return ticketService.findAll();
        return  Response.ok().entity(ApiResponse.builder().data(ticketService.findAll()).build()).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @RolesAllowed({"ADMIN","CUSTOMER"})
    public Response findByTicketId(@PathParam("id") Long id) {
        ticketService.findByTicketId(id);
        return Response.ok().entity(ApiResponse.builder().data(ticketService.findByTicketId(id)).build()).build();
    }

    @PUT
    @Path("/{ticketId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @RolesAllowed("ADMIN")
    public Ticket update(@PathParam("ticketId") long ticketId, @Valid Ticket ticket) {
        Ticket ticketOld=ticketService.findByTicketId(ticketId);
        ticket.setDateTimeOfCreation(ticketOld.getDateTimeOfCreation());
        ticket.setId(ticketId);
        return ticketService.update(ticket);
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @RolesAllowed("ADMIN")
    public Response delete(@PathParam("id") Long id) {
        return Response.ok().entity(ApiResponse.builder().data(ticketService.delete(id)).build()).build();
    }


    @GET
    @Path("/search-by-dates/{dateFrom}&{dateTo}")
    @RolesAllowed("ADMIN")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByDateRange(@PathParam("dateFrom") String dateFrom,@PathParam("dateTo")  String  dateTo) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDateFrom = LocalDate.parse(dateFrom, formatter);
        LocalDate localDateTo = LocalDate.parse(dateTo, formatter);
        return  Response.ok().entity(ApiResponse.builder().data(ticketService.findByDateRange(localDateFrom, localDateTo)).build()).build();
    }

    @GET
    @Path("/search-by-date/{date}")
    @RolesAllowed("ADMIN")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByDate(@PathParam("date") String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(date, formatter);
        return  Response.ok().entity(ApiResponse.builder().data(ticketService.findByDate(localDate)).build()).build();
    }

    @GET
    @Path("/search-by-customer-id/{id}")
    @RolesAllowed({"ADMIN","CUSTOMER"})
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response findByCustomerId(@PathParam("id") Long id) {
        return  Response.ok().entity(ApiResponse.builder().data(ticketService.getTicketsByCustomerId(id)).build()).build();
    }
}
