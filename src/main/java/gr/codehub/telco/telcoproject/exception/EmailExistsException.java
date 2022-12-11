package gr.codehub.telco.telcoproject.exception;

public class EmailExistsException extends RuntimeException {
    public EmailExistsException(String s) {
        super(s);
    }
}