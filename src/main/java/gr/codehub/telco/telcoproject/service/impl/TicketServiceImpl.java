package gr.codehub.telco.telcoproject.service.impl;

import gr.codehub.telco.telcoproject.model.Ticket;
import gr.codehub.telco.telcoproject.repository.TicketRepository;
import gr.codehub.telco.telcoproject.repository.impl.TicketRepositoryImpl;
import gr.codehub.telco.telcoproject.service.TicketService;
import jakarta.inject.Inject;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class TicketServiceImpl implements TicketService {

    @Inject
    private TicketRepositoryImpl ticketRepositoryimpl;
    @Override
    public Ticket createTicket(Ticket ticket) {
        ticketRepositoryimpl.create(ticket);
        return ticket;
    }

    @Override
    public List<Ticket> findAll() {
       List tickets=ticketRepositoryimpl.read();
       return tickets;
    }

    @Override
    public Ticket findByTicketId(Long id) {
        Ticket ticket= ticketRepositoryimpl.read(id);
        return ticket;
    }

    @Override
    public Ticket update(Ticket ticket) {

        ticketRepositoryimpl.update(ticket);
        return ticket;
    }

    @Override
    public boolean delete(Long id) {
        if(ticketRepositoryimpl.delete(id))
        {
            return true;
        }else{
            return false;
        }

    }

    @Override
    public List<Ticket> findByDate(LocalDate date) {

       List tickets = ticketRepositoryimpl.getTicketsByDate(date);
        return tickets;
    }

    @Override
    public List<Ticket> findByDateRange(LocalDate dateFrom, LocalDate dateTo) {

        List tickets = ticketRepositoryimpl.getTicketsByDateRange(dateFrom,dateTo);
        return tickets;
    }

    @Override
    public List<Ticket> getTicketsByCustId(Long id) {
        List tickets=ticketRepositoryimpl.getTicketsByCustomerId(id);
        return tickets;
    }
}
