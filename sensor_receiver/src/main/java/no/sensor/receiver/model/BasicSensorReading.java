package no.sensor.receiver.model;

public class BasicSensorReading {
    private String sensorGroupId;
    private String sensorId;
    private Double sensorValue;

    public BasicSensorReading() {
    }

    public BasicSensorReading(String sensorGroupId, String sensorId, Double sensorValue) {
        this.sensorGroupId = sensorGroupId;
        this.sensorId = sensorId;
        this.sensorValue = sensorValue;
    }

    public String getSensorGroupId() {
        return sensorGroupId;
    }

    public void setSensorGroupId(String sensorGroupId) {
        this.sensorGroupId = sensorGroupId;
    }

    public String getSensorId() {
        return sensorId;
    }

    public void setSensorId(String sensorId) {
        this.sensorId = sensorId;
    }

    public Double getSensorValue() {
        return sensorValue;
    }

    public void setSensorValue(Double sensorValue) {
        this.sensorValue = sensorValue;
    }

    @Override
    public String toString() {
        return (new StringBuilder("BasicSensorReading: sensorGroupId=").append(sensorGroupId).
                append(" sensorId=").append(sensorId).append(" sensorValue=").append(sensorValue)).toString();

    }
}
