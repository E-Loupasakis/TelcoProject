package gr.codehub.telco.telcoproject.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import gr.codehub.telco.telcoproject.enums.TicketStatus;
import gr.codehub.telco.telcoproject.enums.TicketType;
import gr.codehub.telco.telcoproject.enums.UserCategory;
import gr.codehub.telco.telcoproject.model.Email;
import gr.codehub.telco.telcoproject.model.Phone;
import gr.codehub.telco.telcoproject.model.Ticket;
import gr.codehub.telco.telcoproject.model.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
public class TicketDto {

    private long ticketId;
    private String addressofIssue;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime dateTimeOfAction;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
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
//        ticket.setTicketId(ticketId);
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
