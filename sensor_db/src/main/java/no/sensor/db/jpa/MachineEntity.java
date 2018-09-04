package no.sensor.db.jpa;

import javax.persistence.*;
import java.util.List;

/**
 * Created by jan.arne.sandnes on 03.01.15.
 */

@Entity
@Table(name = "machines")
public class MachineEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "machines_id_seq")
    @javax.persistence.SequenceGenerator(name="machines_id_seq", sequenceName = "machines_id_seq")
    private Long id;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="site_id")
    private SiteEntity site;

    @OneToMany(mappedBy="machine", fetch = FetchType.EAGER)
    private List<SensorGroupEntity> sensorGroups;

    @Column(name = "machine_name")
    private String machineName;

    @Column(name = "description")
    private String description;
    @Column(name = "deleted")
    private Boolean deleted;

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

    public List<SensorGroupEntity> getSensorGroups() {
        return sensorGroups;
    }

    public void setSensorGroups(List<SensorGroupEntity> sensorGroups) {
        this.sensorGroups = sensorGroups;
    }

    public String getMachineName() {
        return machineName;
    }

    public void setMachineName(String machineName) {
        this.machineName = machineName;
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
}
