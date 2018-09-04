package no.sensor.service.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import no.sensor.db.jpa.SensorReadingEntity;

import java.util.Date;

/**
 * Created by jan.arne.sandnes on 04.01.15.
 */
public class SensorReading {
    private Long id;
    private Long sensorId;
    private Double raw;
    private Double value;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssZ")
    private Date time;
    private Boolean deleted;

    public SensorReading() {
    }

    public SensorReading(SensorReadingEntity e) {
        this.id = e.getId();
        this.sensorId = e.getSensorId();
        this.raw = e.getRaw();
        this.value = e.getValue();
        this.time = new Date(e.getTime().getTime());
        this.deleted = e.isDeleted();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSensorId() {
        return sensorId;
    }

    public void setSensorId(Long sensorId) {
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

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }
}
