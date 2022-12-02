package gr.codehub.telco.telcoproject.repository.impl;

import gr.codehub.telco.telcoproject.model.Ticket;
import gr.codehub.telco.telcoproject.repository.TicketRepository;
import jakarta.persistence.Query;


import java.time.LocalDateTime;
import java.util.List;

public class TicketRepositoryImpl extends RepositoryImpl<Ticket, Long> implements TicketRepository {
    @Override
    public Class<Ticket> getClassType() {
        return Ticket.class;
    }

    @Override
    public String getClassName() {
        return Ticket.class.getSimpleName();
    }

    public List<Ticket> getTicketsByCustomerId(Long customerId) {
        Query query = super.getEm().createQuery("select t from "+ getClassName()+ " t WHERE t.customer IN (SELECT DISTINCT u.userId FROM User u WHERE u.userId IN (?1)) " );

        query.setParameter(1, customerId);

        List<Ticket> tickets = query.getResultList();

        return tickets;
    }

   /* public List<Ticket> getTicketsByDate(LocalDateTime localDateTime) {
        Query query = super.getEm().createQuery("select t from "+ getClassName()+ " t WHERE t.dateTimeOfCreation IN (?1)) " );

        query.setParameter(1, localDateTime);

        List<Ticket> tickets = query.getResultList();

        return tickets;
    }*/
}