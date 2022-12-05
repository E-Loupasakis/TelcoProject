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
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Path("/tickets")
public class TicketResource {

    @Inject
    private TicketServiceImpl ticketService;

    @POST
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public TicketDto insert(TicketDto ticketDto) {
        ticketDto.setDateTimeOfCreation(LocalDateTime.now());
        return ticketService.create(ticketDto);
    }

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public List<TicketDto> findAll () {
        return ticketService.findAll();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public TicketDto findByTicketId(@PathParam("id") Long id) {
        return ticketService.findByTicketId(id);
    }

    @PUT
    @Path("/{ticketId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public TicketDto update(@PathParam("ticketId") long ticketId, TicketDto ticketDto) {
        ticketDto.setTicketId(ticketId);
        return ticketService.update(ticketDto);
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public boolean delete(@PathParam("id") Long id) {
        return ticketService.delete(id);
    }

    @GET
    @Path("/search-by-dates/{dateFrom}&{dateTo}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<TicketDto> findByDateRange(@PathParam("dateFrom") String dateFrom,@PathParam("dateTo")  String  dateTo) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDateFrom = LocalDate.parse(dateFrom, formatter);
        LocalDate localDateTo = LocalDate.parse(dateTo, formatter);
        return ticketService.findByDateRange(localDateFrom, localDateTo);
    }

    @GET
    @Path("/search-by-date/{date}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<TicketDto> findByDate(@PathParam("date") String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(date, formatter);
        return ticketService.findByDate(localDate);
    }

    @GET
    @Path("/search-by-customer-id/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public List<TicketDto> findByCustomerId(@PathParam("id") Long id) {
        return ticketService.getTicketsByCustomerId(id);
    }




}
