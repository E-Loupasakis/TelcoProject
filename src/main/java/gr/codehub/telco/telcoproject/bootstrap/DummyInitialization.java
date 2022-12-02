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
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
@Log4j2
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
        User us2 = User.builder().lastName("Loupasakis").firstName("Manolis").emailAddress("nn2@gmail.com").build();

        System.out.println(us1);
        cstimpl.create(us1);
        cstimpl.create(us2);
        Ticket ticket= Ticket
                .builder()
                .ticketStatus(TicketStatus.IN_PROGRESS)
                .addressOfIssue("Zallogou 9")
                .dateTimeOfAction(LocalDateTime.now().plusMonths(1))
                .description("Digital")
                .estimatedCost(34.2)
                .dateTimeOfCreation(LocalDateTime.now())
                .customer(us1)
                .build();
        Ticket ticket2= Ticket
                .builder()
                .ticketStatus(TicketStatus.IN_PROGRESS)
                .addressOfIssue("Zallogou 9")
                .dateTimeOfAction(LocalDateTime.now().plusMonths(1))
                .description("Digital")
                .estimatedCost(34.2)
                .dateTimeOfCreation(LocalDateTime.now())
                .customer(us1)
                .build();

        Ticket ticket3= Ticket
                .builder()
                .ticketStatus(TicketStatus.IN_PROGRESS)
                .addressOfIssue("Zallogou 9")
                .dateTimeOfAction(LocalDateTime.now().plusMonths(1))
                .description("Digital")
                .estimatedCost(34.2)
                .dateTimeOfCreation(LocalDateTime.now())
                .customer(us2)
                .build();


        System.out.println(ticket);
        ticketRepositoryImpl.create(ticket);
        ticketRepositoryImpl.create(ticket2);
        ticketRepositoryImpl.create(ticket3);

        ticket.setAddressOfIssue("Zallogou 8");
        ticketRepositoryImpl.update(ticket);
//        log.info( cstimpl.getCustomerByVat(0).getTickets());
        String email = "nn@gmail.com";
        log.info( cstimpl.getCustomerByEmail(email).getTickets());
    }

}



