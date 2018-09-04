package no.sensor.service.exception;

/**
 * Created by jasand on 24.11.2016.
 */
public class ConflictException extends RuntimeException {
    public ConflictException() {
    }

    public ConflictException(String message) {
        super(message);
    }
}
