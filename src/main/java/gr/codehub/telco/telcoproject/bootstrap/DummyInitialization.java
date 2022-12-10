package gr.codehub.telco.telcoproject.bootstrap;


import gr.codehub.telco.telcoproject.dto.TicketDto;
import gr.codehub.telco.telcoproject.enums.TicketStatus;
import gr.codehub.telco.telcoproject.model.Email;
import gr.codehub.telco.telcoproject.model.Ticket;
import gr.codehub.telco.telcoproject.model.User;
import gr.codehub.telco.telcoproject.repository.impl.CustomerRepositoryImpl;
import gr.codehub.telco.telcoproject.repository.impl.TicketRepositoryImpl;
import gr.codehub.telco.telcoproject.service.impl.TicketServiceImpl;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.inject.Inject;
import gr.codehub.telco.telcoproject.model.AppUser;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Singleton
@Startup
public class DummyInitialization {

    @Inject
    TicketRepositoryImpl ticketRepositoryImpl;
    @Inject
    CustomerRepositoryImpl cstimpl;

    @Inject
    TicketServiceImpl ticketServiceimpl;

    @PostConstruct
    public void test() {




        // User Creation

//        List<Email> emailist1 = new ArrayList();
//        List<Email> emailist2 = new ArrayList();
//
//        Email em1 = new Email("em@gmail.com");
//        Email em2 = new Email("em@gmail2.com");
//        emailist1.add(em1);
//        emailist2.add(em2);
//
//
//        User us1 = User.builder().lastName("nn").firstName("Kostas").emailList(emailist1).build();
       // User us2 = User.builder().lastName("Kef").emailList(emailist2).firstName("Kostas").build();

        //cstimpl.create(us1);
       // cstimpl.create(us2);
        // End of User Creation

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////


        // Ticket Creation
//
//        Ticket ticket1 = Ticket.builder().ticketStatus(TicketStatus.IN_PROGRESS).addressOfIssue("Zallogou 9").dateTimeOfAction(LocalDateTime.now().plusMonths(1))
//                .description("Digital1").estimatedCost(34.2).dateTimeOfCreation(LocalDateTime.now()).customer(us1).build();
//
//        Ticket ticket2 = Ticket.builder().ticketStatus(TicketStatus.PENDING).addressOfIssue("Alimoy 9").dateTimeOfAction(LocalDateTime.now().plusMonths(6))
//                .description("Telephone").estimatedCost(24.87).dateTimeOfCreation(LocalDateTime.now()).customer(us1).build();
//
//
//        Ticket ticket3 = Ticket.builder().ticketStatus(TicketStatus.DEFAULT_STANDBY_MODE).addressOfIssue("Zografoy 9").dateTimeOfAction(LocalDateTime.now().plusMonths(3))
//                .description("Internet").estimatedCost(50).dateTimeOfCreation(LocalDateTime.now()).customer(us1).build();
//
//        Ticket ticket10 = Ticket.builder().ticketStatus(TicketStatus.COMPLETE).addressOfIssue("Ioannoy 9").dateTimeOfAction(LocalDateTime.now().plusMonths(5))
//                .description("Ethernet").estimatedCost(1_000_000).dateTimeOfCreation(LocalDateTime.now()).customer(us2).build();
//
//        Ticket ticket25 = Ticket.builder().ticketStatus(TicketStatus.IN_PROGRESS).addressOfIssue("Zallogou 9").dateTimeOfAction(LocalDateTime.now().plusMonths(1))
//                .description("Digital25").estimatedCost(34.2).dateTimeOfCreation(LocalDateTime.now().plusMonths(4)).customer(us1).build();
//
//        Ticket ticket26 = Ticket.builder().ticketStatus(TicketStatus.IN_PROGRESS).addressOfIssue("Zallogou 9").dateTimeOfAction(LocalDateTime.now().plusMonths(1))
//                .description("Digital26").estimatedCost(34.2).dateTimeOfCreation(LocalDateTime.now().plusMonths(2)).customer(us1).build();


//        ticketRepositoryImpl.create(ticket1);
//        ticketRepositoryImpl.create(ticket2);
//        ticketRepositoryImpl.create(ticket3);
//        ticketRepositoryImpl.create(ticket25);
//        ticketRepositoryImpl.create(ticket26);
//        ticketRepositoryImpl.create(ticket10);
//
//        TicketDto ticketDto1 = new TicketDto(ticket1);
//        TicketDto ticketDto2 = new TicketDto(ticket2);
//        TicketDto ticketDto3 = new TicketDto(ticket3);
//
//        ticketServiceimpl.create(ticketDto1);
//        ticketServiceimpl.create(ticketDto2);
//        ticketServiceimpl.create(ticketDto3);
//
//        System.out.println("ssssssssssssssssssssssssssssssssssssssss");
//        System.out.println(cstimpl.GetTicketsByCustomerId(us1.getId()));
//        ticketServiceimpl.createTicket(ticket25);
//        ticketServiceimpl.createTicket(ticket26);
//        ticketServiceimpl.createTicket(ticket10);

    //End Of ticket Creation


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    // Print All Tickets
//
        /*List<Ticket> tickets = ticketServiceimpl.findAll();
        for (Ticket ticket : tickets)
              {
               System.out.println(ticket);
            }*/

      //End of Printing list of tickets


     ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    // Delete Ticket By Id

       // ticketRepositoryImpl.delete(ticket3.getTicketId());

    // End Of Delete


    // Print Ticket by id

//        Ticket ticketRead = ticketServiceimpl.findByTicketId(ticket10.getTicketId());
//
//       System.out.println("ticketRead ========> " + ticketRead);

    // End of Ticket Read

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    // Ticket Udpdate by ID

//        ticketRead.setDescription("New Updated");
//        ticketServiceimpl.update(ticketRead);

    // End Of Ticket Update


     // Print List By Customer ID

//          List<Ticket> ticketsByCustomerId = ticketServiceimpl.getTicketsByCustId(us1.getUserId());
//        for (Ticket ticket : ticketsByCustomerId)
//             {
//                    System.out.println(ticket);
//              }


     // End of Printing


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // List Tickets By 2 Dates
      /*List<Ticket> ticketsByDate = ticketServiceimpl.findByDateRange(LocalDateTime.now().minusMonths(1), LocalDateTime.now().plusMonths(1));
        System.out.println("================== Tickets by two dates ========================================");

       // System.out.println(ticketsByDate);


     for(Ticket ticket: ticketsByDate)
        {
           System.out.println(ticket + "\n");
        }*/

    // End Of List By 2 dates


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    // List Ticket By one date

    /*List<Ticket> ticketsByOneDate= ticketRepositoryImpl.getTicketsByDate(LocalDate.now());

        System.out.println("TICKETS BY ONE DATE");

    for(Ticket ticket:ticketsByOneDate)
        {
            System.out.println(ticket);
        }*/


    //End of Printing List By One Date


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        // Null Tickets By CustomerID
             //System.out.println(cstimpl.read(us1.getUserId()).getTickets());
        // End Of Null

    }

}



