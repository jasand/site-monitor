package no.sensor.service.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import no.sensor.db.jpa.SensorEntity;
import no.sensor.db.jpa.SensorStatusEnum;
import no.sensor.db.jpa.SensorTypeEnum;

import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by jan.arne.sandnes on 04.01.15.
 */
public class Sensor {
    private Long id;
    private Long sensorGroupId;
    private String sensorId;
    private SensorType sensorType;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private Date firstConnect;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private Date lastConnect;
    private String description;
    private String conversionFunction;
    private UnitOfMeasure unitOfMeasure;
    private Double minimumValue;
    private Double maximumValue;
    private Double lastValue;
    private SensorStatus status;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private Date statusTime;
    private Boolean warningFlag;
    private Boolean mute;

    public Sensor() {
    }

    public Sensor(SensorEntity sensorEntity) {
        this.id = sensorEntity.getId();
        this.sensorGroupId = sensorEntity.getSensorGroup().getId();
        this.sensorId = sensorEntity.getSensorId();
        if (sensorEntity.getSensorType() != null)
            this.sensorType = SensorType.valueOf(sensorEntity.getSensorType().name());
        this.firstConnect = timestampToDate(sensorEntity.getFirstConnect());
        this.lastConnect = timestampToDate(sensorEntity.getLastConnect());
        this.description = sensorEntity.getDescription();
        this.conversionFunction = sensorEntity.getConversionFunction();
        if (sensorEntity.getUnitOfMeasure() != null)
            this.unitOfMeasure = new UnitOfMeasure(sensorEntity.getUnitOfMeasure());
        this.minimumValue = sensorEntity.getMinimumValue();
        this.maximumValue = sensorEntity.getMaximumValue();
        this.lastValue = sensorEntity.getLastValue();
        if (sensorEntity.getStatus() != null)
            this.status = SensorStatus.valueOf(sensorEntity.getStatus().name());
        if (sensorEntity.getStatusTime() != null)
            this.statusTime = timestampToDate(sensorEntity.getStatusTime());
        this.warningFlag = sensorEntity.getWarningFlag();
        this.mute = sensorEntity.isMute();
    }

    /**
     * NB! This don't create sensorGroup, site or machine
     * @return
     */
    public SensorEntity toSensorEntity() {
        SensorEntity e = new SensorEntity();
        if (this.id != null && this.id > 0)
            e.setId(this.id);
        e.setSensorId(this.sensorId);
        if (this.sensorType != null)
            e.setSensorType(SensorTypeEnum.valueOf(this.sensorType.name()));
        e.setFirstConnect(dateToTimestamp(this.firstConnect));
        e.setDescription(this.description);
        e.setConversionFunction(this.conversionFunction);
        if (this.unitOfMeasure != null)
            e.setUnitOfMeasure(this.unitOfMeasure.toUnitOfMeasureEntity());
        e.setMinimumValue(this.minimumValue);
        e.setMaximumValue(this.maximumValue);
        e.setLastValue(this.lastValue);
        if (this.status != null)
            e.setStatus(SensorStatusEnum.valueOf(this.status.name()));
        e.setStatusTime(dateToTimestamp(this.statusTime));
        e.setWarningFlag(this.warningFlag);
        e.setMute(this.mute);
        return e;
    }


    public SensorEntity copyToSensorEntity(SensorEntity e) {
        if (this.sensorType != null)
            e.setSensorType(SensorTypeEnum.valueOf(this.sensorType.name()));
        e.setFirstConnect(dateToTimestamp(this.firstConnect));
        e.setDescription(this.description);
        e.setConversionFunction(this.conversionFunction);
        if (this.unitOfMeasure != null)
            e.setUnitOfMeasure(this.unitOfMeasure.toUnitOfMeasureEntity());
        e.setMinimumValue(this.minimumValue);
        e.setMaximumValue(this.maximumValue);
        e.setLastValue(this.lastValue);
        if (this.status != null)
            e.setStatus(SensorStatusEnum.valueOf(this.status.name()));
        if (this.statusTime != null)
            e.setStatusTime(dateToTimestamp(this.statusTime));
        e.setWarningFlag(this.warningFlag);
        e.setMute(this.mute);
        return e;
    }

    private Date timestampToDate(Timestamp ts) {
        return new Date(ts.getTime());
    }

    private Timestamp dateToTimestamp(Date d) {
        return new Timestamp(d.getTime());
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSensorGroupId() {
        return sensorGroupId;
    }

    public void setSensorGroupId(Long sensorGroupId) {
        this.sensorGroupId = sensorGroupId;
    }


    public String getSensorId() {
        return sensorId;
    }

    public void setSensorId(String sensorId) {
        this.sensorId = sensorId;
    }

    public SensorType getSensorType() {
        return sensorType;
    }

    public void setSensorType(SensorType sensorType) {
        this.sensorType = sensorType;
    }

    public Date getFirstConnect() {
        return firstConnect;
    }

    public void setFirstConnect(Date firstConnect) {
        this.firstConnect = firstConnect;
    }

    public Date getLastConnect() {
        return lastConnect;
    }

    public void setLastConnect(Date lastConnect) {
        this.lastConnect = lastConnect;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getConversionFunction() {
        return conversionFunction;
    }

    public void setConversionFunction(String conversionFunction) {
        this.conversionFunction = conversionFunction;
    }

    public UnitOfMeasure getUnitOfMeasure() {
        return unitOfMeasure;
    }

    public void setUnitOfMeasure(UnitOfMeasure unitOfMeasure) {
        this.unitOfMeasure = unitOfMeasure;
    }

    public Double getMinimumValue() {
        return minimumValue;
    }

    public void setMinimumValue(Double minimumValue) {
        this.minimumValue = minimumValue;
    }

    public Double getMaximumValue() {
        return maximumValue;
    }

    public void setMaximumValue(Double maximumValue) {
        this.maximumValue = maximumValue;
    }

    public Double getLastValue() {
        return lastValue;
    }

    public void setLastValue(Double lastValue) {
        this.lastValue = lastValue;
    }

    public SensorStatus getStatus() {
        return status;
    }

    public void setStatus(SensorStatus status) {
        this.status = status;
    }

    public Date getStatusTime() {
        return statusTime;
    }

    public void setStatusTime(Date statusTime) {
        this.statusTime = statusTime;
    }

    public Boolean getWarningFlag() {
        return warningFlag;
    }

    public void setWarningFlag(Boolean warningFlag) {
        this.warningFlag = warningFlag;
    }

    public Boolean isMute() {
        return mute;
    }

    public void setMute(Boolean mute) {
        this.mute = mute;
    }

}
