package no.sensor.service.exception;

/**
 * Created by jan.arne.sandnes on 14.10.15.
 */
public class InvalidInputException extends RuntimeException {

    public InvalidInputException(String message) {
        super(message);
    }
}
