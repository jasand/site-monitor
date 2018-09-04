package no.sensor.db.jpa;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by jan.arne.sandnes on 03.01.15.
 */

@Entity
@Table(name = "sensor_readings")
public class SensorReadingEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sensor_readings_id_seq")
    @javax.persistence.SequenceGenerator(name="sensor_readings_id_seq", sequenceName = "sensor_readings_id_seq")
    private long id;

    @Column(name="sensor_id")
    private long sensorId;

    @Column(name = "raw")
    private Double raw;
    @Column(name = "value")
    private Double value;
    @Column(name = "time")
    private Timestamp time;
    @Column(name = "deleted")
    private Boolean deleted;

    public SensorReadingEntity() {
    }

    public SensorReadingEntity(long sensorId, Double raw, Double value, Timestamp received) {
        this.sensorId = sensorId;
        this.raw = raw;
        this.value = value;
        this.time = received;
        this.deleted = false;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getSensorId() {
        return sensorId;
    }

    public void setSensorId(long sensorId) {
        this.sensorId = sensorId;
    }

    public Double getRaw() {
        return raw;
    }

    public void setRaw(Double raw) {
        this.raw = raw;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public Boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    @Override
    public String toString() {
        return (new StringBuilder("SensorReadingEntity: id=").append(id).
                append(", sensorId=").append(sensorId).
                append(", raw=").append(raw)).
                append(", value=").append(value).
                append(", time=").append(time).
                append(", deleted=").append(deleted).
                toString();

    }

}
