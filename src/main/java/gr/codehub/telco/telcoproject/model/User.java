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
    private String email;
    private String address;
    private String phoneNumber;
    private String userName;
    private String password;



}
