package gr.codehub.telco.telcoproject.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import gr.codehub.telco.telcoproject.enums.TicketStatus;
import gr.codehub.telco.telcoproject.enums.TicketType;
import gr.codehub.telco.telcoproject.model.Ticket;
import gr.codehub.telco.telcoproject.model.User;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.constraints.FutureOrPresent;

@Data
@NoArgsConstructor
public class TicketDto {

    private long ticketId;
    private String addressofIssue;


    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dateTimeOfAction;


    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dateTimeOfCreation;

    private String description;


    private double estimatedCost;


    private TicketStatus ticketStatus;


    private TicketType ticketType;


    private User customer;




    public TicketDto(Ticket ticket){
        if(ticket!=null){
            ticketId = ticket.getTicketId();
            addressofIssue = ticket.getAddressOfIssue();
            dateTimeOfAction = ticket.getDateTimeOfAction();
            dateTimeOfCreation = ticket.getDateTimeOfCreation();
            description = ticket.getDescription();
            estimatedCost = ticket.getEstimatedCost();
            ticketStatus = ticket.getTicketStatus();
            ticketType = ticket.getTicketType();
            customer = ticket.getCustomer();
        }
    }

    public Ticket asTicket() {
        Ticket ticket = new Ticket();
        ticket.setTicketId(ticketId);
        ticket.setAddressOfIssue(addressofIssue);
        ticket.setDateTimeOfAction(dateTimeOfAction);
        ticket.setDateTimeOfCreation(dateTimeOfCreation);
        ticket.setDescription(description);
        ticket.setEstimatedCost(estimatedCost);
        ticket.setTicketStatus(ticketStatus);
        ticket.setTicketType(ticketType);
        ticket.setCustomer(customer);
        return ticket;
    }
}
