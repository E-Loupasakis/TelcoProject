package gr.codehub.telco.telcoproject.service.impl;

import gr.codehub.telco.telcoproject.dto.TicketDto;
import gr.codehub.telco.telcoproject.exception.DataNotFoundException;
import gr.codehub.telco.telcoproject.exception.InvalidDeletionException;
import gr.codehub.telco.telcoproject.exception.InvalidTicketException;
import gr.codehub.telco.telcoproject.model.Ticket;
import gr.codehub.telco.telcoproject.model.User;
import gr.codehub.telco.telcoproject.repository.impl.CustomerRepositoryImpl;
import gr.codehub.telco.telcoproject.repository.impl.TicketRepositoryImpl;
import gr.codehub.telco.telcoproject.service.TicketService;
import jakarta.inject.Inject;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

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


    public TicketDto read(long id) {

        return new TicketDto(ticketRepositoryImpl.read(id));
    }

    public List<TicketDto> read() {
        return ticketRepositoryImpl.read().stream().map(TicketDto::new).collect(Collectors.toList());
    }

    @Override
    public List<Ticket> findAll() {
        return ticketRepositoryImpl.read();
    }

    @Override
    public List<Ticket> getPendingTickets(int limit) {
        return ticketRepositoryImpl.getPendingTickets(limit);
    }

    @Override
    public Ticket findByTicketId(Long id) {
        Ticket ticket = ticketRepositoryImpl.read(id);
        if (ticket == null) {
            throw new DataNotFoundException("Ticket with id " + id + " does not exist.");
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

    @Override
    public List<Ticket> getTicketsByDateRangeOfAction(LocalDate dateFrom, LocalDate dateTo) {
        List tickets = ticketRepositoryImpl.getTicketsByDateRangeOfAction(dateFrom, dateTo);
        return tickets;
    }

    @Override
    public List<Ticket> getTicketsByDateOfAction(LocalDate date) {
        List tickets = ticketRepositoryImpl.getTicketsByDateOfAction(date);
        return tickets;
    }

    @Override
    public List<Ticket> getCustomerTicketsByDateRangeDateTimeOfCreationForCustomer(LocalDate dateFrom, LocalDate dateTo, long customerId) {
        List tickets = ticketRepositoryImpl.getCustomerTicketsByDateRangeDateTimeOfCreationForCustomer(dateFrom, dateTo, customerId);
        return tickets;
    }

    @Override
    public List<Ticket> getCustomerTicketsByDateRangeDateTimeOfActionForCustomer(LocalDate dateFrom, LocalDate dateTo, long customerId) {
        List tickets = ticketRepositoryImpl.getCustomerTicketsByDateRangeDateTimeOfActionForCustomer(dateFrom, dateTo, customerId);
        return tickets;
    }

    @Override
    public List<Ticket> getTicketsByDateDateTimeOfCreationForCustomer(LocalDate date, long customerId) {
        List tickets = ticketRepositoryImpl.getTicketsByDateDateTimeOfCreationForCustomer(date, customerId);
        return tickets;
    }

    @Override
    public List<Ticket> getTicketsByDateDateTimeOfActionForCustomer(LocalDate date, long customerId) {
        List tickets = ticketRepositoryImpl.getTicketsByDateDateTimeOfActionForCustomer(date, customerId);
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
