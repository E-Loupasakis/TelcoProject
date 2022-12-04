package gr.codehub.telco.telcoproject.model;

import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import gr.codehub.telco.telcoproject.enums.TicketStatus;
import gr.codehub.telco.telcoproject.enums.TicketType;
import jakarta.json.stream.JsonGenerator;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="tickets")
public class Ticket{

    @Id
    @Column(name="ticket_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long ticketId;

    @Column(name="date_time_of_creation")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime dateTimeOfCreation;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User customer;

    @Column(name="ticket_status")
    private TicketStatus ticketStatus;

    @Column(name="date_time_of_action")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
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
