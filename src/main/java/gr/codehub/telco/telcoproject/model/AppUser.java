package gr.codehub.telco.telcoproject.model;
import gr.codehub.telco.telcoproject.enums.UserCategory;
import jakarta.persistence.Entity;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
@Data
@Entity
public class AppUser extends BaseEntity {

    @NotNull(message = "Username cannot be null")
    @Size(min = 8, max = 20, message
            = "Username must be between 10 and 20 characters")
    @Pattern(regexp = "^[A-Z].*[\\s\\.]*$",message = "Username must starts with an uppercase")
    private String username;

    @NotNull(message = "Password cannot be null")
    @Pattern(regexp="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&-+=()])(?=\\\\S+$).{8,20}$",
            message="0-9 at least once,a-z at least once,A-Z at least once," +
            "Special character at least once, no white spaces are allowed,min chars :8 , max chars:20 ")
    private String password;

    @NotNull(message = "User category cannot be null")
    private UserCategory userCategory;
}