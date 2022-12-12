package gr.codehub.telco.telcoproject.exception;

import java.util.UUID;

public class DataNotFoundException extends RuntimeException{

    private static final String SerialVersionUID = UUID.randomUUID().toString().toUpperCase();

    public DataNotFoundException(String message) {
        super(message);
    }
}
