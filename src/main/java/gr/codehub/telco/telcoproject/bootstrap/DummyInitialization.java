package gr.codehub.telco.telcoproject.bootstrap;


import gr.codehub.telco.telcoproject.enums.TicketStatus;
import gr.codehub.telco.telcoproject.model.Ticket;
import gr.codehub.telco.telcoproject.model.User;
import gr.codehub.telco.telcoproject.repository.TicketRepository;
import gr.codehub.telco.telcoproject.repository.impl.CustomerRepositoryImpl;
import gr.codehub.telco.telcoproject.repository.impl.TicketRepositoryImpl;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.inject.Inject;

import java.time.LocalDateTime;
import java.util.List;

@Singleton
@Startup
public class DummyInitialization {

    @Inject
    TicketRepositoryImpl ticketRepositoryImpl;
    @Inject
    CustomerRepositoryImpl cstimpl;

    @PostConstruct
    public void test() {
        User us1 = User.builder().lastName("nn").lastName("Kef").emailAddress("nn@gmail.com").build();
        User us2 = User.builder().lastName("Kef").emailAddress("nn@gmail.com").firstName("Kostas").build();
        System.out.println(us1);
        cstimpl.create(us1);
        cstimpl.create(us2);
        Ticket ticket1 = Ticket.builder().ticketStatus(TicketStatus.IN_PROGRESS).addressOfIssue("Zallogou 9").dateTimeOfAction(LocalDateTime.now().plusMonths(1))
                .description("Digital").estimatedCost(34.2).dateTimeOfCreation(LocalDateTime.now()).customer(us1).build();

        Ticket ticket2 = Ticket.builder().ticketStatus(TicketStatus.PENDING).addressOfIssue("Alimoy 9").dateTimeOfAction(LocalDateTime.now().plusMonths(6))
                .description("Telephone").estimatedCost(24.87).dateTimeOfCreation(LocalDateTime.now()).customer(us1).build();

        Ticket ticket3 = Ticket.builder().ticketStatus(TicketStatus.DEFAULT_STANDBY_MODE).addressOfIssue("Zografoy 9").dateTimeOfAction(LocalDateTime.now().plusMonths(3))
                .description("Internet").estimatedCost(50).dateTimeOfCreation(LocalDateTime.now()).customer(us1).build();

        Ticket ticket10 = Ticket.builder().ticketStatus(TicketStatus.COMPLETE).addressOfIssue("Ioannoy 9").dateTimeOfAction(LocalDateTime.now().plusMonths(5))
                .description("Ethernet").estimatedCost(1_000_000).dateTimeOfCreation(LocalDateTime.now()).customer(us2).build();



        ticketRepositoryImpl.create(ticket1);
        ticketRepositoryImpl.create(ticket2);
        ticketRepositoryImpl.create(ticket3);


        List<Ticket> tickets = ticketRepositoryImpl.read();
        for (Ticket ticket : tickets
             ) {
            System.out.println(ticket);
        }

        //ticketRepositoryImpl.delete(ticket1.getTicketId());

        Ticket ticketRead = ticketRepositoryImpl.read(ticket2.getTicketId());

        System.out.println("ticketRead ========> " + ticketRead);

        System.out.println(tickets);

        ticketRead.setDescription("UPDATE");

        ticketRepositoryImpl.update(ticketRead);

        List<Ticket> ticketsByCustomerId = ticketRepositoryImpl.getTicketsByCustomerId(us1.getUserId());

        System.out.println("====================TICKETS BY CUSTOMER ID=================================");

        for (Ticket ticket : ticketsByCustomerId
        ) {
            System.out.println(ticket);
        }




    }

}



