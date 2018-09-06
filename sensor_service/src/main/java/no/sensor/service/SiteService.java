package no.sensor.service;

import no.sensor.service.model.SensorGroup;
import no.sensor.service.model.Site;

import java.util.List;

/**
 * Created by jan.arne.sandnes on 04.01.15.
 */
public interface SiteService {
    List<Site> getSites();
    Site getSiteById(long id);
    Site createSite(Site site);
    Site updateSite(long id, Site site);
    void deleteSite(long id);

    List<SensorGroup> getSiteSensorGroups(long siteId);
    SensorGroup addSensorGroup(long siteId, long sensorGroupId);
    void removeSensorGroup(long siteId, long sensorGroupId);
}
