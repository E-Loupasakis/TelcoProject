package gr.codehub.telco.telcoproject.model;


import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@ToString(callSuper = true)
public class Customer extends User{

    private int vatNumber;

}
