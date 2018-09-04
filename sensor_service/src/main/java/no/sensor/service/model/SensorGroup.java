package no.sensor.service.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import no.sensor.db.jpa.SensorGroupEntity;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by jasand on 24.11.2016.
 */
public class SensorGroup {
    private Long id;
    private Long siteId;
    private Long machineId;
    private String sensorGroupId;
    private String description;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssZ")
    private Date firstConnect;
    List<Sensor> sensors;

    public SensorGroup() {
    }

    public SensorGroup(SensorGroupEntity e) {
        this.id = e.getId();
        if (e.getSite() != null) {
            this.siteId = e.getSite().getId();
        }
        if (e.getMachine() != null) {
            this.siteId = e.getMachine().getId();
        }
        this.sensorGroupId = e.getSensorGroupId();
        this.description = e.getDescription();
        this.firstConnect = new Date(e.getFirstConnect().getTime());
        if (e.getSensors() != null) {
            this.sensors = e.getSensors().stream().map(s -> new Sensor(s)).collect(Collectors.toList());
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSiteId() {
        return siteId;
    }

    public void setSiteId(Long siteId) {
        this.siteId = siteId;
    }

    public Long getMachineId() {
        return machineId;
    }

    public void setMachineId(Long machineId) {
        this.machineId = machineId;
    }

    public String getSensorGroupId() {
        return sensorGroupId;
    }

    public void setSensorGroupId(String sensorGroupId) {
        this.sensorGroupId = sensorGroupId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getFirstConnect() {
        return firstConnect;
    }

    public void setFirstConnect(Date firstConnect) {
        this.firstConnect = firstConnect;
    }

    public List<Sensor> getSensors() {
        return sensors;
    }

    public void setSensors(List<Sensor> sensors) {
        this.sensors = sensors;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SensorGroup that = (SensorGroup) o;

        return id != null ? id.equals(that.id) : that.id == null;

    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
