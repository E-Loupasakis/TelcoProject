package gr.codehub.telco.telcoproject.service.impl;

import gr.codehub.telco.telcoproject.dto.CustomerDto;
import gr.codehub.telco.telcoproject.dto.TicketDto;
import gr.codehub.telco.telcoproject.model.Ticket;
import gr.codehub.telco.telcoproject.model.User;
import gr.codehub.telco.telcoproject.repository.TicketRepository;
import gr.codehub.telco.telcoproject.repository.impl.CustomerRepositoryImpl;
import gr.codehub.telco.telcoproject.repository.impl.TicketRepositoryImpl;
import gr.codehub.telco.telcoproject.service.TicketService;
import jakarta.inject.Inject;
import org.hibernate.Hibernate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class TicketServiceImpl implements TicketService {

    @Inject
    private TicketRepositoryImpl ticketRepositoryImpl;

    @Inject
    private CustomerRepositoryImpl customerRepositoryImpl;



    @Override
    public TicketDto create(TicketDto ticketDto) {
        long userId = ticketDto.getCustomer().getId();
        User customer = customerRepositoryImpl.read(userId);
        ticketDto.setCustomer(customer);
        Ticket ticket = ticketDto.asTicket();
        ticketRepositoryImpl.create(ticket);
        return new TicketDto(ticket);
    }

    @Override
    public List<TicketDto> findAll() {
        return ticketRepositoryImpl.read().stream().map(TicketDto::new).collect(Collectors.toList());
       //Hibernate.initialize(tickets);
    }

    @Override
    public TicketDto findByTicketId(Long id) {
        return new TicketDto(ticketRepositoryImpl.read(id));
    }

    @Override
    public TicketDto update(TicketDto ticketDto) {
        Ticket ticket = ticketDto.asTicket();
//       ticket.setTicketId(ticketDto.getTicketId());

        long userId = ticketDto.getCustomer().getId();
        User customer = customerRepositoryImpl.read(userId);

        ticket.setCustomer(customer);
        ticketRepositoryImpl.update(ticket);
        return new TicketDto(ticket);
    }

    @Override
    public boolean delete(Long id) {
        if(ticketRepositoryImpl.delete(id))
        {
            return true;
        }else{
            return false;
        }

    }

    @Override
    public List<TicketDto> findByDate(LocalDate date) {

       List tickets = ticketRepositoryImpl.getTicketsByDate(date);
        return tickets;
    }

    @Override
    public List<TicketDto> findByDateRange(LocalDate dateFrom, LocalDate dateTo) {

        List tickets = ticketRepositoryImpl.getTicketsByDateRange(dateFrom,dateTo);
        return tickets;
    }

    @Override
    public List<TicketDto> getTicketsByCustomerId(Long id) {
        List tickets=ticketRepositoryImpl.getTicketsByCustomerId(id);
        return tickets;
    }
}
