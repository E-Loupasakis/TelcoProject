package gr.codehub.telco.telcoproject.service;

import gr.codehub.telco.telcoproject.model.Ticket;
import jakarta.transaction.Transactional;

import java.time.LocalDate;
import java.util.List;

@Transactional
public interface TicketService {
    Ticket create(Ticket ticketD);
    List<Ticket> findAll();
    Ticket findByTicketId(Long id);
    Ticket update(Ticket ticket);
    boolean delete(Long id);
    List<Ticket> findByDate(LocalDate date);
    List<Ticket> findByDateRange(LocalDate dateFrom, LocalDate dateTo);
    List<Ticket> getTicketsByCustomerId(Long id);

    List<Ticket> getTicketsByDateRangeOfAction(LocalDate dateFrom, LocalDate dateTo);

    List<Ticket> getTicketsByDateOfAction(LocalDate date);

    List<Ticket> getCustomerTicketsByDateRangeDateTimeOfCreationForCustomer(LocalDate dateFrom, LocalDate dateTo, long customerId);

    List<Ticket> getCustomerTicketsByDateRangeDateTimeOfActionForCustomer(LocalDate dateFrom, LocalDate dateTo, long customerId);

    List<Ticket> getTicketsByDateDateTimeOfCreationForCustomer(LocalDate date, long customerId);

    List<Ticket> getTicketsByDateDateTimeOfActionForCustomer(LocalDate date, long customerId);

    List<Ticket> getPendingTickets(int limit);
}
