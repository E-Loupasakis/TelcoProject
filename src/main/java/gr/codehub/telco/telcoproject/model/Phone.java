package gr.codehub.telco.telcoproject.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
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
public class Phone {

//    private String type;
//    private String areaCode;
    @Column(name="P_NUMBER", unique = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull(message="Phone number can't be null")
    @jakarta.validation.constraints.Pattern(regexp = "^[0-9]{10}$",message = "The phone number only allows exactly 10 digits.")
    private String number;
}
