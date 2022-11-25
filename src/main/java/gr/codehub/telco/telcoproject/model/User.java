package gr.codehub.telco.telcoproject.model;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public abstract class User {

    private int userId;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String addressField;
    private String phoneNumber;
    private String userName;
    private String password;



}
