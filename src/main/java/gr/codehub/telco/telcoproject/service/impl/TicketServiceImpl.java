package gr.codehub.telco.telcoproject.service.impl;

import gr.codehub.telco.telcoproject.model.Ticket;
import gr.codehub.telco.telcoproject.model.User;
import gr.codehub.telco.telcoproject.repository.impl.CustomerRepositoryImpl;
import gr.codehub.telco.telcoproject.repository.impl.TicketRepositoryImpl;
import gr.codehub.telco.telcoproject.service.TicketService;
import jakarta.inject.Inject;

import java.time.LocalDate;
import java.util.List;

public class TicketServiceImpl implements TicketService {

    @Inject
    private TicketRepositoryImpl ticketRepositoryImpl;

    @Inject
    private CustomerRepositoryImpl customerRepositoryImpl;



    @Override
    public Ticket create(Ticket ticket) {
        System.out.println(ticket);

//        long userId = ticket.getCustomer().getId();
//        User customer = customerRepositoryImpl.read(userId);
//        ticket.setCustomer(customer);
        //Ticket ticket = ticketDto.asTicket();
       ticketRepositoryImpl.create(ticket);
       return ticket;

    }

    @Override
    public List<Ticket> findAll() {
        //ticketRepositoryImpl.read().stream().map(Ticket::new).collect(Collectors.toList());
        //Hibernate.initialize(tickets);
        return  ticketRepositoryImpl.read();
    }

    @Override
    public Ticket findByTicketId(Long id) {
        return ticketRepositoryImpl.read(id);
    }

    @Override
    public Ticket update(Ticket ticket) {
//        Ticket ticket = ticketDto.asTicket();
//       ticket.setTicketId(ticketDto.getTicketId());
//
//        long userId = ticketDto.getCustomer().getId();
//        User customer = customerRepositoryImpl.read(userId);

       // ticket.setCustomer(customer);
       return ticketRepositoryImpl.update(ticket);
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
}
