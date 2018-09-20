package no.sensor.web.controller.error;

/**
 * Created by jan.arne.sandnes on 14.10.15.
 */
public class ErrorResponse {
    private String message;
    private String error;
    private String cause;

    public ErrorResponse() {
    }

    public ErrorResponse(String message, String cause) {
        this.message = message;
        this.cause = cause;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }
}
