package gr.codehub.telco.telcoproject.repository.impl;

import gr.codehub.telco.telcoproject.model.Ticket;
import gr.codehub.telco.telcoproject.model.User;
import gr.codehub.telco.telcoproject.repository.TicketRepository;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
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

//    @Override
//    public List<Ticket> read(){
//        return  em.createQuery("select u from " + getClassName() + " u left join fetch u.customer.id").getResultList();
//    }

    public List<Ticket> getTicketsByCustomerId(Long customerId) {
        Query query = super.getEm().createQuery("select t from "+ getClassName()+ " t WHERE t.customer IN (SELECT DISTINCT u.id FROM User u WHERE u.id IN (?1)) ORDER BY t.dateTimeOfCreation DESC " );

        query.setParameter(1, customerId);

        List<Ticket> tickets = query.getResultList();

        return tickets;
    }

    //Get tickets by Dates of Creation for Admin

   public List<Ticket> getTicketsByDateRange(LocalDate dateFrom, LocalDate dateTo) {

       LocalDateTime rangeFrom = dateFrom.atStartOfDay();

       LocalDateTime rangeTo = dateTo.atTime(23, 59, 59);

        Query query = super.getEm().createQuery("select t from "+ getClassName()+ " t WHERE t.dateTimeOfCreation BETWEEN (?1) AND (?2)  ORDER BY t.dateTimeOfCreation DESC" );

        query.setParameter(1, rangeFrom);
        query.setParameter(2, rangeTo);

        List<Ticket> tickets = query.getResultList();

        return tickets;
    }



    //Get tickets by Dates of Action for Admin

    public List<Ticket> getTicketsByDateRangeOfAction(LocalDate dateFrom, LocalDate dateTo) {

        LocalDateTime rangeFrom = dateFrom.atStartOfDay();

        LocalDateTime rangeTo = dateTo.atTime(23, 59, 59);

        Query query = super.getEm().createQuery("select t from "+ getClassName()+ " t WHERE t.dateTimeOfAction BETWEEN (?1) AND (?2)  ORDER BY t.dateTimeOfAction DESC" );

        query.setParameter(1, rangeFrom);
        query.setParameter(2, rangeTo);

        List<Ticket> tickets = query.getResultList();

        return tickets;
    }




    //Get tickets by Date of Creation for Admin

    public List<Ticket> getTicketsByDate(LocalDate date) {

        LocalDateTime dateTimeFrom = date.atStartOfDay();

        LocalDateTime dateTimeTo = date.atTime(23, 59, 59);

        Query query = super.getEm().createQuery("select t from "+ getClassName()+ " t WHERE t.dateTimeOfCreation BETWEEN (?1) AND (?2) ORDER BY t.dateTimeOfCreation DESC" );

        query.setParameter(1, dateTimeFrom);
        query.setParameter(2, dateTimeTo);

        List<Ticket> tickets = query.getResultList();

        return tickets;
    }


   // Get tickets by Date of Action for Admin


    public List<Ticket> getTicketsByDateOfAction(LocalDate date) {

        LocalDateTime dateTimeFrom = date.atStartOfDay();

        LocalDateTime dateTimeTo = date.atTime(23, 59, 59);

        Query query = super.getEm().createQuery("select t from "+ getClassName()+ " t WHERE t.dateTimeOfAction BETWEEN (?1) AND (?2) ORDER BY t.dateTimeOfAction DESC" );

        query.setParameter(1, dateTimeFrom);
        query.setParameter(2, dateTimeTo);

        List<Ticket> tickets = query.getResultList();

        return tickets;
    }


    //Get tickets by Dates of Creation for Customer
    public List<Ticket> getCustomerTicketsByDateRangeDateTimeOfCreationForCustomer(LocalDate dateFrom, LocalDate dateTo, long customerId) {

        LocalDateTime rangeFrom = dateFrom.atStartOfDay();

        LocalDateTime rangeTo = dateTo.atTime(23, 59, 59);

        Query query = super.getEm().createQuery("select t from "+ getClassName()+ " t WHERE t.dateTimeOfCreation BETWEEN (?1) AND (?2) and t.customer.id=(?3) ORDER BY t.dateTimeOfCreation DESC" );

        query.setParameter(1, rangeFrom);
        query.setParameter(2, rangeTo);
        query.setParameter(3, customerId);

        List<Ticket> tickets = query.getResultList();

        return tickets;
    }


    //Get Tickets by dates of Action for Customer

    public List<Ticket> getCustomerTicketsByDateRangeDateTimeOfActionForCustomer(LocalDate dateFrom, LocalDate dateTo, long customerId) {

        LocalDateTime rangeFrom = dateFrom.atStartOfDay();

        LocalDateTime rangeTo = dateTo.atTime(23, 59, 59);

        Query query = super.getEm().createQuery("select t from "+ getClassName()+ " t WHERE t.dateTimeOfAction BETWEEN (?1) AND (?2) and t.customer.id=(?3) ORDER BY t.dateTimeOfAction DESC" );

        query.setParameter(1, rangeFrom);
        query.setParameter(2, rangeTo);
        query.setParameter(3, customerId);

        List<Ticket> tickets = query.getResultList();

        return tickets;
    }


    //Get Tickets by date of Creation for Customer
    public List<Ticket> getTicketsByDateDateTimeOfCreationForCustomer(LocalDate date, long customerId) {

        LocalDateTime dateTimeFrom = date.atStartOfDay();

        LocalDateTime dateTimeTo = date.atTime(23, 59, 59);

        Query query = super.getEm().createQuery("select t from "+ getClassName()+ " t WHERE t.dateTimeOfCreation BETWEEN (?1) AND (?2) and t.customer.id=(?3) ORDER BY t.dateTimeOfCreation DESC" );

        query.setParameter(1, dateTimeFrom);
        query.setParameter(2, dateTimeTo);
        query.setParameter(3, customerId);

        List<Ticket> tickets = query.getResultList();

        return tickets;
    }


    //Get Tickets by date of Action

    public List<Ticket> getTicketsByDateDateTimeOfActionForCustomer(LocalDate date, long customerId) {

        LocalDateTime dateTimeFrom = date.atStartOfDay();

        LocalDateTime dateTimeTo = date.atTime(23, 59, 59);

        Query query = super.getEm().createQuery("select t from "+ getClassName()+ " t WHERE t.dateTimeOfAction BETWEEN (?1) AND (?2) and t.customer.id=(?3) ORDER BY t.dateTimeOfAction DESC" );

        query.setParameter(1, dateTimeFrom);
        query.setParameter(2, dateTimeTo);
        query.setParameter(3, customerId);

        List<Ticket> tickets = query.getResultList();

        return tickets;
    }


    @Override
    public List<Ticket> getPendingTickets(int limit) {
        if(limit>0){
            Query query = super.getEm().createQuery("select t from "+ getClassName()+ " t WHERE t.ticketStatus=0 ORDER BY t.dateTimeOfAction ASC").setMaxResults(limit);
            List<Ticket> tickets = query.getResultList();
            return tickets;
        }
        else{
            Query query = super.getEm().createQuery("select t from "+ getClassName()+ " t WHERE t.ticketStatus=0 ORDER BY t.dateTimeOfAction ASC");
            List<Ticket> tickets = query.getResultList();
            return tickets;
        }

    }




    @Override
    @Transactional
    public Ticket update(Ticket ticket){
        if (read(ticket.getId()) == null) return null;
        return getEm().merge(ticket);
    }


}