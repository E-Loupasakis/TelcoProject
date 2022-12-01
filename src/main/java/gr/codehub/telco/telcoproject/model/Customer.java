package gr.codehub.telco.telcoproject.model;


import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Set;

@Data
@EqualsAndHashCode (callSuper = true)
@SuperBuilder
@NoArgsConstructor
@ToString(callSuper = true)
@Entity
@Table(name="customer")
public class Customer extends User{

    private int vatNumber;

    @OneToMany(mappedBy="user")
    private Set<Ticket> ticket;

}
