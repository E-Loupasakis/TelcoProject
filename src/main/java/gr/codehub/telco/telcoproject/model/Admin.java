package gr.codehub.telco.telcoproject.model;

import lombok.*;
import lombok.experimental.SuperBuilder;


@Data
@EqualsAndHashCode (callSuper = true)
@SuperBuilder
@ToString(callSuper = true)
public class Admin extends User{

    private int employeeId; //right now the toString method produces only the employeeId


    //this can be changed

}
