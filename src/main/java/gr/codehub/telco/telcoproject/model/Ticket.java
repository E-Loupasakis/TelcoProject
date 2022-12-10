package gr.codehub.telco.telcoproject.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import gr.codehub.telco.telcoproject.enums.TicketStatus;
import gr.codehub.telco.telcoproject.enums.TicketType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDateTime;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.constraints.FutureOrPresent;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity

@Table(name="tickets")
public class Ticket{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long ticketId;

    @FutureOrPresent(message = "Date of ticket's creation cannot be past")
    @Column(name="date_time_of_creation")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dateTimeOfCreation;

    @NotNull(message = "Customer cannot be null")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    @JsonBackReference
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private User customer;



    @NotNull(message = "Ticket Status cannot be null")
    @Column(name="ticket_status")
    private TicketStatus ticketStatus;

    @FutureOrPresent(message = "Date of ticket's action cannot be past")
    @Column(name="date_time_of_action")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dateTimeOfAction;

    @NotNull(message = "Ticket Type cannot be null")
    @Column(name="ticket_type")
    private TicketType ticketType;

    @Min(value = 0, message = "Estimated cost should not be less than 0 €")
    @Max(value = 100000, message = "Estimated cost should not be greater than 100000 €")
    @NotNull(message = "The estimated cost cannot be null")
    @Column(name="estimated_cost")
    private double estimatedCost;

    @NotNull(message = "Address cannot be null")
    @Size(min = 10, max = 100, message
            = "Address of issue must be between 10 and 100 characters")
    @Column(name="address_of_issue")
    private String addressOfIssue;

    @NotNull(message = "Description cannot be null")
    @Size(min = 10, max = 200, message
            = "Description of the ticket must be between 10 and 100 characters")
    @Column(name="description")
    private String description;

}
