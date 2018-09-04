package no.sensor.db.jpa;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name = "sensor_reading_queue")
public class SensorReadingQueueEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sensor_reading_queue_id_seq")
    @SequenceGenerator(name="sensor_reading_queue_id_seq", sequenceName = "sensor_reading_queue_id_seq")
    private Long id;

    @Column(name="sensor_group_id")
    private String sensorGroupId;

    @Column(name="sensor_id")
    private String sensorId;

    @Column(name = "sensor_value")
    private Double sensorValue;

    @Column(name = "received")
    private Timestamp received;

    @Column(name = "processed")
    private Timestamp processed;

    public SensorReadingQueueEntity() {
    }

    public SensorReadingQueueEntity(String sensorGroupId, String sensorId, Double sensorValue) {
        this.sensorGroupId = sensorGroupId;
        this.sensorId = sensorId;
        this.sensorValue = sensorValue;
        this.received = new Timestamp(new Date().getTime());
        this.processed = null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Timestamp getReceived() {
        return received;
    }

    public void setReceived(Timestamp received) {
        this.received = received;
    }

    public Timestamp getProcessed() {
        return processed;
    }

    public void setProcessed(Timestamp processed) {
        this.processed = processed;
    }

    @Override
    public String toString() {
        return (new StringBuilder("SensorReadingQueueEntity: id=").append(id).
                append(", sensorGroupId=").append(sensorGroupId).
                append(", sensorId=").append(sensorId).
                append(", sensorValue=").append(sensorValue)).
                append(", received=").append(received).
                append(", processed=").append(processed).
                toString();

    }

}
