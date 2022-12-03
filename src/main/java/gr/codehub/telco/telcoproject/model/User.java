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
public class User {

    @Id
    @Column(name="user_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long userId;

    @Column(name="user_category")
    private UserCategory userCategory;

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

    @Column(name="user_name", unique = true)
    private String userName;

    @Column(name="password")
    private String password;


//    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
//    private List<Ticket> tickets = new ArrayList<>();

}
