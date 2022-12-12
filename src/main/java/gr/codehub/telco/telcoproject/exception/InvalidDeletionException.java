package gr.codehub.telco.telcoproject.exception;

import java.util.UUID;

public class InvalidDeletionException extends RuntimeException{

    private static final String SerialVersionUID = UUID.randomUUID().toString().toUpperCase();

    public InvalidDeletionException(String message) {
        super(message);
    }
}
