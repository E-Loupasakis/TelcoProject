package gr.codehub.telco.telcoproject.model;



import gr.codehub.telco.telcoproject.enums.UserCategory;
import jakarta.persistence.*;
import lombok.*;

import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@Entity
@Table(name="user")
public class User {

    @Id
    @Column(name="user_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long userId;

    @Column(name="user_category")
    private UserCategory userCategory;

    @Column(name="vat_number") //unique=True
    private long vatNumber;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Column(name="email_address")
    private String emailAddress;

    @Column(name="address")
    private String address;

    @Column(name="phone_number")
    private String phoneNumber;

    @Column(name="user_name")
    private String userName;

    @Column(name="password")
    private String password;

    @OneToMany(mappedBy = "customer" )
    private List<Ticket> tickets;

    public User(){}
}
