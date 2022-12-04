package gr.codehub.telco.telcoproject.service;

import gr.codehub.telco.telcoproject.model.Ticket;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface TicketService {
    Ticket createTicket(Ticket ticket);
    List<Ticket> findAll();
    Ticket findByTicketId(Long id);
    Ticket update(Ticket ticket);
    boolean delete(Long id);
    List<Ticket> findByDate(LocalDate date);
    List<Ticket> findByDateRange(LocalDate dateFrom, LocalDate dateTo);
    List<Ticket> getTicketsByCustomerId(Long id);

}
