package no.sensor.db.jpa;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by jan.arne.sandnes on 02.01.15.
 */

@Entity
@Table (name = "sensors")
public class SensorEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sensors_id_seq")
    @javax.persistence.SequenceGenerator(
            name="sensors_id_seq",
            sequenceName = "sensors_id_seq")
    private Long id;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="sensor_group_id")
    private SensorGroupEntity sensorGroup;

    @Column(name = "sensor_ident")
    private String sensorId;

    @Enumerated(EnumType.STRING)
    @Column(name = "sensor_type")
    private SensorTypeEnum sensorType;

    @Column(name = "first_connect")
    private Timestamp firstConnect;
    @Column(name = "last_connect")
    private Timestamp lastConnect;
    @Column(name = "description")
    private String description;
    @Column(name = "conversion_function")
    private String conversionFunction;

    @ManyToOne(fetch=FetchType.EAGER)
    @Fetch(FetchMode.SELECT)
    @JoinColumn(name="units_of_measure_id")
    private UnitOfMeasureEntity unitOfMeasure;

    @Column(name = "minimum_value")
    private Double minimumValue;
    @Column(name = "maximum_value")
    private Double maximumValue;

    @Column(name = "last_value")
    private Double lastValue;
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private SensorStatusEnum status;
    @Column(name = "status_time")
    private Timestamp statusTime;
    @Column(name = "warning_flag")
    private Boolean warningFlag;

    @Column(name = "mute")
    private Boolean mute;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SensorGroupEntity getSensorGroup() {
        return sensorGroup;
    }

    public void setSensorGroup(SensorGroupEntity sensorGroup) {
        this.sensorGroup = sensorGroup;
    }

    public String getSensorId() {
        return sensorId;
    }

    public void setSensorId(String sensorId) {
        this.sensorId = sensorId;
    }

    public SensorTypeEnum getSensorType() {
        return sensorType;
    }

    public void setSensorType(SensorTypeEnum sensorType) {
        this.sensorType = sensorType;
    }

    public Timestamp getFirstConnect() {
        return firstConnect;
    }

    public void setFirstConnect(Timestamp firstConnect) {
        this.firstConnect = firstConnect;
    }

    public Timestamp getLastConnect() {
        return lastConnect;
    }

    public void setLastConnect(Timestamp lastConnect) {
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

    public UnitOfMeasureEntity getUnitOfMeasure() {
        return unitOfMeasure;
    }

    public void setUnitOfMeasure(UnitOfMeasureEntity unitOfMeasure) {
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

    public SensorStatusEnum getStatus() {
        return status;
    }

    public void setStatus(SensorStatusEnum status) {
        this.status = status;
    }

    public Timestamp getStatusTime() {
        return statusTime;
    }

    public void setStatusTime(Timestamp statusTime) {
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
