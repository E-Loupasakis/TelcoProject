package gr.codehub.telco.telcoproject.model;


import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class Customer extends User{

    private int ssn;//right now the toString method produces only the ssn

    //this can be changed

}
