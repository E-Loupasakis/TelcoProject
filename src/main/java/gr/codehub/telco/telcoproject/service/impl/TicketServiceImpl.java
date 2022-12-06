package gr.codehub.telco.telcoproject.service.impl;

import gr.codehub.telco.telcoproject.dto.TicketDto;
import gr.codehub.telco.telcoproject.model.Ticket;
import gr.codehub.telco.telcoproject.model.User;
import gr.codehub.telco.telcoproject.repository.TicketRepository;
import gr.codehub.telco.telcoproject.repository.impl.TicketRepositoryImpl;
import gr.codehub.telco.telcoproject.service.TicketService;
import jakarta.inject.Inject;
import org.hibernate.Hibernate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class TicketServiceImpl implements TicketService {

    @Inject
    private TicketRepositoryImpl ticketRepositoryimpl;
    @Override
    public TicketDto createTicket(TicketDto ticketDto) {
        Ticket ticket= ticketDto.asTicket();
        ticketRepositoryimpl.create(ticket);
        return new TicketDto(ticket);
    }

    @Override
    public List<Ticket> findAll() {
       List tickets=ticketRepositoryimpl.read();
       Hibernate.initialize(tickets);
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
    public List<Ticket> getTicketsByCustomerId(Long id) {
        List tickets=ticketRepositoryimpl.getTicketsByCustomerId(id);
        return tickets;
    }
}
