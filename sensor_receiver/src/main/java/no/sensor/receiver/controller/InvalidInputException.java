package no.sensor.receiver.controller;

public class InvalidInputException extends RuntimeException {
    public InvalidInputException() {
        super();
    }

    public InvalidInputException(String msg) {
        super(msg);
    }
}
