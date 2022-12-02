package gr.codehub.telco.telcoproject.testing;

import gr.codehub.telco.telcoproject.enums.TicketStatus;
import gr.codehub.telco.telcoproject.model.Ticket;
import gr.codehub.telco.telcoproject.model.User;
import gr.codehub.telco.telcoproject.repository.Repository;
import gr.codehub.telco.telcoproject.repository.TicketRepository;
import gr.codehub.telco.telcoproject.repository.impl.TicketRepositoryImpl;
import jakarta.ejb.Startup;

import java.time.LocalDateTime;

public class Main {

    public static void main(String[] args) {

        User us1 = User.builder().lastName("nn").lastName("Kef").emailAddress("nn@gmail.com").build();
        System.out.println(us1);
        Ticket ticket= Ticket.builder().ticketStatus(TicketStatus.IN_PROGRESS).addressOfIssue("Zallogou 9").dateTimeOfAction(LocalDateTime.now().plusMonths(1))
                .description("Digital").estimatedCost(34.2).dateTimeOfCreation(LocalDateTime.now()).customer(us1).build();
        System.out.println(ticket);
        TicketRepositoryImpl tr = new TicketRepositoryImpl();
        tr.create(ticket);
    }
}


