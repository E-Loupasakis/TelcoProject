package gr.codehub.telco.telcoproject.repository.impl;

import gr.codehub.telco.telcoproject.model.Ticket;
import gr.codehub.telco.telcoproject.repository.TicketRepository;
import jakarta.persistence.Query;


import java.time.LocalDate;
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
        Query query = super.getEm().createQuery("select t from "+ getClassName()+ " t WHERE t.customer IN (SELECT DISTINCT u.userId FROM User u WHERE u.userId IN (?1)) ORDER BY t.dateTimeOfCreation DESC " );

        query.setParameter(1, customerId);

        List<Ticket> tickets = query.getResultList();

        return tickets;
    }

   public List<Ticket> getTicketsByDate(LocalDateTime dateTimeFrom, LocalDateTime dateTimeTo) {

       LocalDateTime rangeFrom = dateTimeFrom;
       dateTimeFrom.withHour(0).withMinute(0).withSecond(0);

       LocalDateTime rangeTo = dateTimeTo;
       dateTimeTo.withHour(23).withMinute(59).withSecond(59);

        Query query = super.getEm().createQuery("select t from "+ getClassName()+ " t WHERE t.dateTimeOfCreation BETWEEN (?1) AND (?2) ORDER BY t.dateTimeOfCreation DESC" );

        query.setParameter(1, rangeFrom);
        query.setParameter(2, rangeTo);

        List<Ticket> tickets = query.getResultList();

        return tickets;
    }

    public List<Ticket> getTicketsByDate(LocalDateTime dateTime) {

        LocalDateTime dateTimeFrom = dateTime;
        dateTimeFrom.withHour(0).withMinute(0).withSecond(0);

        LocalDateTime dateTimeTo = dateTime;
        dateTimeTo.withHour(23).withMinute(59).withSecond(59);

        Query query = super.getEm().createQuery("select t from "+ getClassName()+ " t WHERE t.dateTimeOfCreation BETWEEN (?1) AND (?2) ORDER BY t.dateTimeOfCreation DESC" );

        query.setParameter(1, dateTimeFrom);
        query.setParameter(2, dateTimeTo);

        List<Ticket> tickets = query.getResultList();

        return tickets;
    }


}