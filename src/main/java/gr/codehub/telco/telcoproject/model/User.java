package gr.codehub.telco.telcoproject.model;



import gr.codehub.telco.telcoproject.enums.UserCategory;
import jakarta.persistence.*;
import lombok.*;

import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="user")
public class User extends AppUser{


    @Column(name="vat_number", unique = true)
    private int vatNumber;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name="EMAILS",joinColumns = @JoinColumn(name="OWNER_ID"))
    private List<Email> emailList;

    @Column(name="address")
    private String address;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name="PHONE",joinColumns = @JoinColumn(name="OWNER_ID"))
    private List<Phone> phones;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "customer", cascade = {CascadeType.PERSIST,CascadeType.MERGE},orphanRemoval = true )
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Ticket> tickets;

}
