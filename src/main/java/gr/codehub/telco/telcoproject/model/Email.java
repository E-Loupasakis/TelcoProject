package gr.codehub.telco.telcoproject.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
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

    @Column(name="email_address", unique = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull(message = "Email cannot be null")
    @jakarta.validation.constraints.Email(message = "Email should be valid")
    private String email;
}
