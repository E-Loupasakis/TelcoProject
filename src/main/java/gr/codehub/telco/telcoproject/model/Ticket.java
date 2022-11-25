package gr.codehub.telco.telcoproject.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
public class Ticket {
    private Long id;
    private LocalDate dateTimeOfCreation;
    private Long customerId;
    private TicketStatus ticketStatus;
    private LocalDateTime dateTimeOfAction;
    private TicketType ticketType;
    private double estimatedCost;
    private String addressOfIssue;
    private String description;

}
