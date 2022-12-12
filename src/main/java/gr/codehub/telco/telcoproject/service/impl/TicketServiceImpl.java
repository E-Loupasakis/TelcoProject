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
    public Ticket create(Ticket ticket) {
        if (ticket.getEstimatedCost() < 0 || ticket.getEstimatedCost() > 100_000) {
            throw new InvalidTicketException("Estimated cost cannot be negative.");
        }

        List<User> customers = customerRepositoryImpl.read();
        for (User customer : customers) {
            if (customer.getId() == (ticket.getCustomer().getId())) {
                break;
            } else {
                throw new InvalidTicketException("You must set a customer that already exists in the database.");
            }
        }
       this.checkTicket(ticket);
       ticketRepositoryImpl.create(ticket);
       return ticket;

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
        if (ticket.getEstimatedCost() < 0 || ticket.getEstimatedCost() > 100_000) {
            throw new InvalidTicketException("Estimated cost cannot be negative.");
        }

        List<User> customers = customerRepositoryImpl.read();
        for (User customer : customers) {
            if (customer.getId() == (ticket.getCustomer().getId())) {
                break;
            } else {
                throw new InvalidTicketException("You must set a customer that already exists in the database.");
            }
        }

       return ticketRepositoryImpl.update(ticket);
       // return new TicketDto(ticket);
    }

    @Override
    public boolean delete(Long id) {
        boolean deletionSucceded = ticketRepositoryImpl.delete(id);
        if (deletionSucceded == true) {
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

    private void checkTicket(Ticket ticket) {
        /*if (ticket.getEstimatedCost() < 0 && ticket.getEstimatedCost() > 100_000) {
            throw new InvalidTicketException("Estimated cost cannot be negative.");
        }

        List<User> customers = customerRepositoryImpl.read();
        for (User customer : customers) {
            if (customer.getId() == (ticket.getCustomer().getId())) {
                break;
            } else {
                throw new InvalidTicketException("You must set a customer that already exists in the database.");
            }
        }*/
    }
}
