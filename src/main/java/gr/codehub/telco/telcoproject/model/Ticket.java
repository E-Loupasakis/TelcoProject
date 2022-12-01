package gr.codehub.telco.telcoproject.model;

import gr.codehub.telco.telcoproject.enums.TicketStatus;
import gr.codehub.telco.telcoproject.enums.TicketType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import gr.codehub.telco.telcoproject.model.Customer;
import lombok.NoArgsConstructor;
import org.graalvm.compiler.lir.LIRInstruction;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="tickets")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long ticketId;

    @Column(name="date_time_of_creation")
    private LocalDateTime dateTimeOfCreation;

    @Column(name="user_id")
    @ManyToOne
    @JoinColumn(name="user_id")
    private Customer customer;

    @Column(name="ticket_status")
    private TicketStatus ticketStatus;

    @Column(name="date_time_of_action")
    private LocalDateTime dateTimeOfAction;

    @Column(name="ticket_type")
    private TicketType ticketType;

    @Column(name="estimated_cost")
    private double estimatedCost;

    @Column(name="address_of_issue")
    private String addressOfIssue;

    @Column(name="description")
    private String description;

}
