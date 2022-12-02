package gr.codehub.telco.telcoproject.bootstrap;


import gr.codehub.telco.telcoproject.enums.TicketStatus;
import gr.codehub.telco.telcoproject.model.Ticket;
import gr.codehub.telco.telcoproject.model.User;
import gr.codehub.telco.telcoproject.repository.impl.CustomerRepositoryImpl;
import gr.codehub.telco.telcoproject.repository.impl.TicketRepositoryImpl;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.inject.Inject;

import java.time.LocalDateTime;

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
        System.out.println(us1);
        cstimpl.create(us1);
        Ticket ticket= Ticket.builder().ticketStatus(TicketStatus.IN_PROGRESS).addressOfIssue("Zallogou 9").dateTimeOfAction(LocalDateTime.now().plusMonths(1))
                .description("Digital").estimatedCost(34.2).dateTimeOfCreation(LocalDateTime.now()).customer(us1).build();


        System.out.println(ticket);
       ticketRepositoryImpl.create(ticket);
        ticket.setAddressOfIssue("Zallogou 8");
        ticketRepositoryImpl.update(ticket);
    }

}



