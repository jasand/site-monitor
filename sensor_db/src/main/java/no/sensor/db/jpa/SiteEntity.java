package no.sensor.db.jpa;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

/**
 * Created by jan.arne.sandnes on 02.01.15.
 */

@Entity
@Table (name="sites")
public class SiteEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sites_id_seq")
    @javax.persistence.SequenceGenerator(name="sites_id_seq", sequenceName = "sites_id_seq")
    private Long id;
    @Column(name = "site_name")
    private String siteName;
    @Column(name = "site_address")
    private String siteAddress;
    @Column(name = "contact_person")
    private String contactPerson;
    @Column(name = "contact_phone")
    private String contactPhone;
    @Column(name = "contact_email")
    private String contactEmail;
    @Column(name = "deleted")

    @OneToMany(mappedBy="site", fetch = FetchType.EAGER)
    @Fetch(FetchMode.SELECT)
    private List<SensorGroupEntity> sensorGroups;

    private Boolean deleted;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public String getSiteAddress() {
        return siteAddress;
    }

    public void setSiteAddress(String siteAddress) {
        this.siteAddress = siteAddress;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public List<SensorGroupEntity> getSensorGroups() {
        return sensorGroups;
    }

    public void setSensorGroups(List<SensorGroupEntity> sensorGroups) {
        this.sensorGroups = sensorGroups;
    }

    public Boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }
}
