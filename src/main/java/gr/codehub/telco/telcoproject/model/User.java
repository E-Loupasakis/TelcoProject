package gr.codehub.telco.telcoproject.model;



import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import gr.codehub.telco.telcoproject.enums.UserCategory;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
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
    @NotNull(message="The vat number can't be null")
    @Pattern(regexp = "\\d{10}",message = "The vat number only allows max 10 digits")
    private int vatNumber;

    @Column(name="first_name")
    @NotNull(message="First number can't be null")
    @Size(min = 2, max = 20, message
            = "First name must be between 2 and 20 characters")
    @Pattern(regexp = "^[A-Z].*[\\s\\.]*$",message = "First name must starts with an uppercase")
    @Pattern(regexp = "^[\\S]+$", message="No white spaces are allowed")
    private String firstName;


    @Column(name="last_name")
    @NotNull(message="Last name can't be null")
    @Size(min = 2, max = 20, message
            = "Your last name must be between 2 and 20 characters")
    @Pattern(regexp = "^[A-Z].*[\\s\\.]*$",message = "First name must starts with an uppercase")
    @Pattern(regexp = "^[\\S]+$", message="No white spaces are allowed")
    private String lastName;


    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name="EMAILS",joinColumns = @JoinColumn(name="OWNER_ID"))
    @NotNull(message="The email list can't be null")
    private List<Email> emailList;


    @Column(name="address")
    @NotNull(message = "The address can't be null")
    @Size(min = 5, max = 30, message
            = "The address must be between 5 and 30 characters")
    private String address;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name="PHONE",joinColumns = @JoinColumn(name="OWNER_ID"))
    @NotNull(message="The phone list can't be null")
    private List<Phone> phones;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "customer", cascade = {CascadeType.ALL})
    @JsonManagedReference
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Ticket> tickets;

}
