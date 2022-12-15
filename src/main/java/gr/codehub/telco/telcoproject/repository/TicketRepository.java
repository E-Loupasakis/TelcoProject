package gr.codehub.telco.telcoproject.repository;

import gr.codehub.telco.telcoproject.model.Ticket;

import java.time.LocalDate;
import java.util.List;

public interface TicketRepository extends Repository<Ticket, Long> {

    List<Ticket> getTicketsByDateRange(LocalDate dateFrom, LocalDate dateTo);

    List<Ticket> getTicketsByDateRangeOfAction(LocalDate dateFrom, LocalDate dateTo);

    List<Ticket> getTicketsByDate(LocalDate date);

    List<Ticket> getTicketsByDateOfAction(LocalDate date);

    List<Ticket> getCustomerTicketsByDateRangeDateTimeOfCreationForCustomer(LocalDate dateFrom, LocalDate dateTo, long customerId);

    List<Ticket> getCustomerTicketsByDateRangeDateTimeOfActionForCustomer(LocalDate dateFrom, LocalDate dateTo, long customerId);

    List<Ticket> getTicketsByDateDateTimeOfCreationForCustomer(LocalDate date, long customerId);

    List<Ticket> getTicketsByDateDateTimeOfActionForCustomer(LocalDate date, long customerId);
    List<Ticket> getPendingTickets(int limit);
}
