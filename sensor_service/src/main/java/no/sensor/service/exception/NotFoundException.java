package no.sensor.service.exception;

/**
 * Created by jasand on 24.11.2016.
 */
public class NotFoundException extends RuntimeException {
    public NotFoundException() {
    }

    public NotFoundException(String message) {
        super(message);
    }
}
