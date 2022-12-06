package gr.codehub.telco.telcoproject.model;
import gr.codehub.telco.telcoproject.enums.UserCategory;
import jakarta.persistence.Entity;
import lombok.Data;
@Data
@Entity
public class AppUser extends BaseEntity {
    private String username;
    private String password;
    private UserCategory userCategory;
}