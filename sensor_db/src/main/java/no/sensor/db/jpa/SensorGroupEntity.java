package no.sensor.db.jpa;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * Created by jasand on 22.11.2016.
 */

@Entity
@Table(name = "sensor_groups")
public class SensorGroupEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sensor_groups_id_seq")
    @javax.persistence.SequenceGenerator(name="sensor_groups_id_seq", sequenceName = "sensor_groups_id_seq")
    private Long id;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="site_id")
    private SiteEntity site;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="machine_id")
    private MachineEntity machine;

    @Column(name = "sensor_group_id")
    private String sensorGroupId;

    @Column(name = "description")
    private String description;

    @Column(name = "first_connect")
    private Timestamp firstConnect;

    @OneToMany(mappedBy = "sensorGroup", fetch = FetchType.EAGER)
    private List<SensorEntity> sensors;

    public SensorGroupEntity() {
        this.firstConnect = new Timestamp(new Date().getTime());
    }

    public SensorGroupEntity(String sensorGroupId) {
        this.sensorGroupId = sensorGroupId;
        this.firstConnect = new Timestamp(new Date().getTime());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SiteEntity getSite() {
        return site;
    }

    public void setSite(SiteEntity site) {
        this.site = site;
    }

    public MachineEntity getMachine() {
        return machine;
    }

    public void setMachine(MachineEntity machine) {
        this.machine = machine;
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

    public Timestamp getFirstConnect() {
        return firstConnect;
    }

    public void setFirstConnect(Timestamp firstConnect) {
        this.firstConnect = firstConnect;
    }

    public List<SensorEntity> getSensors() {
        return sensors;
    }

    public void setSensors(List<SensorEntity> sensors) {
        this.sensors = sensors;
    }
}
