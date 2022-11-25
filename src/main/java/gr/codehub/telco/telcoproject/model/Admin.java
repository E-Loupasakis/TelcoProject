package gr.codehub.telco.telcoproject.model;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@ToString
@SuperBuilder
public class Admin extends User{

    private int employeeId; //right now the toString method produces only the employeeId
                            //this can be changed

}
