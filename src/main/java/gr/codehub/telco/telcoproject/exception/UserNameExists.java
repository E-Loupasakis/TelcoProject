package gr.codehub.telco.telcoproject.exception;

public class UserNameExists extends RuntimeException{

    public UserNameExists(String s) {
        super(s);
    }

}
