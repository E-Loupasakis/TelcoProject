package gr.codehub.telco.telcoproject.model;



import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import gr.codehub.telco.telcoproject.enums.UserCategory;
import jakarta.persistence.*;
import lombok.*;

import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.*;

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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customer", cascade = {CascadeType.ALL})
    @JsonManagedReference
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Ticket> tickets;


}
