package gr.codehub.telco.telcoproject.testing;


import gr.codehub.telco.telcoproject.enums.TicketStatus;
import gr.codehub.telco.telcoproject.enums.UserCategory;
import gr.codehub.telco.telcoproject.model.Ticket;
import gr.codehub.telco.telcoproject.model.User;
import gr.codehub.telco.telcoproject.repository.impl.CustomerRepositoryImpl;
import gr.codehub.telco.telcoproject.repository.impl.TicketRepositoryImpl;
import jakarta.inject.Inject;
import jakarta.persistence.PersistenceContext;

import java.time.LocalDateTime;

public class TestModels {

    @Inject
    private CustomerRepositoryImpl customerRepo;
    @Inject
    private TicketRepositoryImpl ticketRepo;


    public  void main(String[] args) {
/*
        User customer = User.builder().userName("tom").build();
        Ticket ticket= Ticket.builder().ticketStatus(TicketStatus.IN_PROGRESS).addressOfIssue("Zallogou 9").dateTimeOfAction(LocalDateTime.now())
               .description("Digital").estimatedCost(34.2).dateTimeOfCreation(LocalDateTime.now()).build();

        customerRepo.create(customer);
        ticketRepo.create(ticket);*/


    }
}
