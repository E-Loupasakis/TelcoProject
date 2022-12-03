package gr.codehub.telco.telcoproject.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Phone {

//    private String type;
//    private String areaCode;
    @Column(name="P_NUMBER")
    private int number;
}
