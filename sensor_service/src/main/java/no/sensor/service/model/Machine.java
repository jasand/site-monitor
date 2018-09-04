package no.sensor.service.model;

import no.sensor.db.jpa.MachineEntity;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by jan.arne.sandnes on 04.01.15.
 */
public class Machine {
    private Long id;
    private Long siteId;
    private List<SensorGroup> sensorGroups;
    private String name;
    private String description;
    private Boolean deleted;
    private SensorStatus accumulatedSensorStatus;


    public Machine() {
    }

    public Machine(MachineEntity e) {
        this.id = e.getId();
        this.siteId = e.getSite().getId();
        if (e.getSensorGroups() != null) {
            this.sensorGroups = e.getSensorGroups().stream().map(s -> new SensorGroup(s)).collect(Collectors.toList());
        }
        this.name = e.getMachineName();
        this.description = e.getDescription();
        this.deleted = e.isDeleted();
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

    public List<SensorGroup> getSensorGroups() {
        return sensorGroups;
    }

    public void setSensorGroups(List<SensorGroup> sensorGroups) {
        this.sensorGroups = sensorGroups;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public SensorStatus getAccumulatedSensorStatus() {
        return accumulatedSensorStatus;
    }

    public void setAccumulatedSensorStatus(SensorStatus accumulatedSensorStatus) {
        this.accumulatedSensorStatus = accumulatedSensorStatus;
    }
}
