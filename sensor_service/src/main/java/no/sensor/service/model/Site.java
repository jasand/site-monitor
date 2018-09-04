package no.sensor.service.model;

import no.sensor.db.jpa.SiteEntity;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by jan.arne.sandnes on 04.01.15.
 */
public class Site {
    private long id;
    private String siteIdent;
    private String siteName;
    private String siteAddress;
    private String contactPerson;
    private String contactPhone;
    private String contactEmail;
    private List<SensorGroup> sensorGroups;
    private List<Machine> machines;
    private SensorStatus accumulatedSensorStatus;

    public Site() {
    }

    public Site(SiteEntity e, boolean deepCopy) {
        id = e.getId();
        siteIdent = e.getSiteIdent();
        siteName = e.getSiteName();
        siteAddress = e.getSiteAddress();
        contactPerson = e.getContactPerson();
        contactPhone = e.getContactPhone();
        contactEmail = e.getContactEmail();
        if (deepCopy) {
            if (e.getSensorGroups() != null) {
                sensorGroups = e.getSensorGroups().stream().map(sg -> new SensorGroup(sg)).collect(Collectors.toList());
            }
            if (e.getMachines() != null) {
                machines = e.getMachines().stream().map(m -> new Machine(m)).collect(Collectors.toList());
            }

            List<SensorGroup> inMachines = machines.stream().flatMap(x -> x.getSensorGroups().stream()).collect(Collectors.toList());
            sensorGroups.removeAll(inMachines);
        }
    }

    public SiteEntity toSiteEntity() {
        SiteEntity e = new SiteEntity();
        e.setSiteIdent(siteIdent);
        e.setSiteName(siteName);
        e.setSiteAddress(siteAddress);
        e.setContactPerson(contactPerson);
        e.setContactPhone(contactPhone);
        e.setContactEmail(contactEmail);
        return e;
    }

    public SiteEntity copyToSiteEntity(SiteEntity e) {
        e.setSiteIdent(siteIdent);
        e.setSiteName(siteName);
        e.setSiteAddress(siteAddress);
        e.setContactPerson(contactPerson);
        e.setContactPhone(contactPhone);
        e.setContactEmail(contactEmail);
        return e;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSiteIdent() {
        return siteIdent;
    }

    public void setSiteIdent(String siteIdent) {
        this.siteIdent = siteIdent;
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

    public List<SensorGroup> getSensorGroups() {
        return sensorGroups;
    }

    public void setSensorGroups(List<SensorGroup> sensorGroups) {
        this.sensorGroups = sensorGroups;
    }

    public List<Machine> getMachines() {
        return machines;
    }

    public void setMachines(List<Machine> machines) {
        this.machines = machines;
    }

    public SensorStatus getAccumulatedSensorStatus() {
        return accumulatedSensorStatus;
    }

    public void setAccumulatedSensorStatus(SensorStatus accumulatedSensorStatus) {
        this.accumulatedSensorStatus = accumulatedSensorStatus;
    }
}
