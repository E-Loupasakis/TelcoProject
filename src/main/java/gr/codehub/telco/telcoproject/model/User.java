package gr.codehub.telco.telcoproject.model;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.*;
import java.util.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="user")
public class User extends AppUser{


    @Column(name="vat_number", unique = true)
    @NotNull(message="The vat number can't be null")
    @Min(value = 100_000_000, message = "Invalid Vat number (cannot be under 100000000)")
    @Max(value = 999_999_999, message = "Invalid Vat number (cannot be higher than 999999999")
    private int vatNumber;

    @Column(name="first_name")
    @NotNull(message="First name can't be null")
    @Size(min = 2, max = 20, message
            = "First name must be between 2 and 20 characters")
    @Pattern(regexp = "[A-Z][A-Za-z\\s]*$",message = "First name must starts with an uppercase, and can contain only characters and spaces.")
    private String firstName;


    @Column(name="last_name")
    @NotNull(message="Last name can't be null")
    @Size(min = 2, max = 20, message
            = "Your last name must be between 2 and 20 characters")
    @Pattern(regexp = "[A-Z][A-Za-z\\s]*$",message = "First name must starts with an uppercase, and can contain only characters and spaces.")
    private String lastName;

    @Valid
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name="EMAILS",joinColumns = @JoinColumn(name="OWNER_ID"))
    @NotNull(message="The email list can't be null")
    private List<Email> emailList;


    @Column(name="address")
    @NotNull(message = "The address can't be null")
    @Size(min = 5, max = 30, message
            = "The address must be between 5 and 30 characters")
    private String address;

    @Valid
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name="PHONE",joinColumns = @JoinColumn(name="OWNER_ID"))
    @NotNull(message="The phone list can't be null")
    private List<Phone> phones;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customer", cascade = {CascadeType.ALL})
    @JsonManagedReference
    private List<Ticket> tickets;


}
