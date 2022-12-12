package gr.codehub.telco.telcoproject.service.impl;

import gr.codehub.telco.telcoproject.exception.DataNotFoundException;
import gr.codehub.telco.telcoproject.exception.InvalidDeletionException;
import gr.codehub.telco.telcoproject.exception.InvalidTicketException;
import gr.codehub.telco.telcoproject.model.Ticket;
import gr.codehub.telco.telcoproject.model.User;
import gr.codehub.telco.telcoproject.repository.impl.CustomerRepositoryImpl;
import gr.codehub.telco.telcoproject.repository.impl.TicketRepositoryImpl;
import gr.codehub.telco.telcoproject.service.TicketService;
import jakarta.inject.Inject;
import jakarta.ws.rs.Path;

import java.time.LocalDate;
import java.util.List;

public class TicketServiceImpl implements TicketService {

    @Inject
    private TicketRepositoryImpl ticketRepositoryImpl;

    @Inject
    private CustomerRepositoryImpl customerRepositoryImpl;



    @Override
    public Ticket create(Ticket ticket)  throws InvalidTicketException {
       String exceptionMessage = checkTicket(ticket);
       if (exceptionMessage.isEmpty()) {
           ticketRepositoryImpl.create(ticket);
           return ticket;
       }
       else {
           throw new InvalidTicketException(exceptionMessage);
       }
    }

    @Override
    public List<Ticket> findAll() {
        return ticketRepositoryImpl.read();
    }

    @Override
    public Ticket findByTicketId(Long id) {
        Ticket ticket = ticketRepositoryImpl.read(id);
        if (ticket == null) {
            throw new DataNotFoundException("Ticket with id " + id + "does not exist.");
        }
        else {
            return ticket;
        }
    }

    @Override
    public Ticket update(Ticket ticket) {
        String exceptionMessage = checkTicket(ticket);
        if (exceptionMessage.isEmpty()) {
            ticketRepositoryImpl.update(ticket);
            return ticket;
        }
        else {
            throw new InvalidTicketException(exceptionMessage);
        }
    }

    @Override
    public boolean delete(Long id) {
        boolean deletionSucceded = ticketRepositoryImpl.delete(id);
        if (deletionSucceded) {
            return true;
        }
        else {
            throw new InvalidDeletionException("The there is no item with id " + id + " in the database");
        }

    }

    @Override
    public List<Ticket> findByDate(LocalDate date) {

       List tickets = ticketRepositoryImpl.getTicketsByDate(date);
        return tickets;
    }

    @Override
    public List<Ticket> findByDateRange(LocalDate dateFrom, LocalDate dateTo) {

        List tickets = ticketRepositoryImpl.getTicketsByDateRange(dateFrom,dateTo);
        return tickets;
    }

    @Override
    public List<Ticket> getTicketsByCustomerId(Long id) {
        List tickets=ticketRepositoryImpl.getTicketsByCustomerId(id);
        return tickets;
    }

    private String checkTicket(Ticket ticket) {
        StringBuilder exceptionMessage = new StringBuilder();
        if (ticket.getEstimatedCost() < 0 || ticket.getEstimatedCost() > 100_000) {
            exceptionMessage.append("Estimated cost cannot be negative.");
        }

        List<User> customers = customerRepositoryImpl.read();
        for (User customer : customers) {
            if (customer.getId() == (ticket.getCustomer().getId())) {
                return exceptionMessage.toString();
            }
        }

        exceptionMessage.append("You must set a customer that already exists in the database.");

        return exceptionMessage.toString();
    }
}
