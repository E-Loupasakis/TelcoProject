package gr.codehub.telco.telcoproject.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Email {

    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotEmpty(message = "Email cannot be null")
    @jakarta.validation.constraints.Email(message = "Email should be valid.")
    @Column(name="email_address", unique = true)
    private String email;
}
