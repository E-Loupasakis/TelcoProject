package gr.codehub.telco.telcoproject.service;

import gr.codehub.telco.telcoproject.model.Ticket;

import java.time.LocalDate;
import java.util.List;

public interface TicketService {
    int createTicket(Ticket ticket);
    int update(int id);
    Ticket findByTicket(int customerId);
    Ticket findByDate(LocalDate date);
    Ticket findByDateRange(LocalDate dateFrom, LocalDate dateTo);
    List<Ticket> findAll();
    int delete(int id);
}
