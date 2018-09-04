package no.sensor.service.impl;

import no.sensor.db.jpa.MachineEntity;
import no.sensor.db.jpa.SensorEntity;
import no.sensor.db.jpa.SensorGroupEntity;
import no.sensor.db.jpa.SiteEntity;
import no.sensor.db.repo.MachineRepo;
import no.sensor.db.repo.SensorGroupRepo;
import no.sensor.db.repo.SiteRepo;
import no.sensor.service.SiteService;
import no.sensor.service.exception.ConflictException;
import no.sensor.service.exception.NotFoundException;
import no.sensor.service.model.SensorGroup;
import no.sensor.service.model.SensorStatus;
import no.sensor.service.model.Site;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by jan.arne.sandnes on 04.01.15.
 */

@Service
public class SiteServiceImpl implements SiteService {
    private static final Logger LOG = LoggerFactory.getLogger(SiteServiceImpl.class);

    @Autowired
    private SiteRepo siteRepo;
    @Autowired
    private SensorGroupRepo sensorGroupRepo;
    @Autowired
    private MachineRepo machineRepo;

    @Override
    public List<Site> getSites() {
        List<SiteEntity> entities = siteRepo.findAll();
        List<Site> sites = entities.stream().map(e -> new Site(e, false)).collect(Collectors.toList());
        // TODO: Ineffektiv måte å sette akkumulert status, kun for demo.
//        for (Site site : sites) {
//            SiteEntity siteEntity = siteRepo.findBySiteIdent(site.getSiteIdent());
//            SensorStatus accuSensorStatus = SensorStatus.NA;
//            for (SensorEntity sensorEntity : siteEntity.getSensorGroups()) {
//                if (sensorEntity.getStatus() != null) {
//                    if (sensorEntity.getStatus().ordinal() > accuSensorStatus.ordinal()) {
//                        accuSensorStatus = SensorStatus.valueOf(sensorEntity.getStatus().name());
//                    }
//                }
//            }
//            site.setAccumulatedSensorStatus(accuSensorStatus);
//        }
        return sites;
    }

    @Override
    public Site getSiteById(long id) {
        SiteEntity siteEntity = siteRepo.findOne(id);
        if (siteEntity == null) {
            LOG.info("Site with id {} not found.", id);
            throw new NotFoundException("Site with id " + id + "not found.");
        }
        return new Site(siteEntity, true);
    }

    @Override
    public Site getSiteBySiteIdent(String siteIdent) {
        SiteEntity siteEntity = siteRepo.findBySiteIdent(siteIdent);
        if (siteEntity == null) {
            LOG.info("Site with site ident {} not found.", siteIdent);
            throw new NotFoundException("Site with site ident " + siteIdent + "not found.");
        }
        return new Site(siteEntity, true);
    }

    @Override
    public Site createSite(Site site) {
        SiteEntity siteEntity = site.toSiteEntity();
        siteEntity = siteRepo.save(siteEntity);
        return new Site(siteEntity, false);
    }

    @Override
    public Site updateSite(long id, Site site) {
        if (id != site.getId()) {
            throw new IllegalArgumentException("Site.getId() must match id given in path.");
        }
        SiteEntity siteEntity = siteRepo.findOne(id);
        if (siteEntity == null) {
            LOG.info("Site with id {} not found.", id);
            throw new NotFoundException("Site with id " + id + "not found.");
        }
        siteEntity = site.copyToSiteEntity(siteEntity);
        siteEntity = siteRepo.save(siteEntity);
        return new Site(siteEntity, false);
    }

    @Transactional
    public void deleteSite(long id) {
        SiteEntity siteEntity = siteRepo.findOne(id);
        if (siteEntity == null) {
            LOG.info("Site with id {} not found.", id);
            throw new NotFoundException("Site with id " + id + "not found.");
        }
        // Disconnect sensorGroups from site (These are not deleted)
        List<SensorGroupEntity> sensorGroups = siteEntity.getSensorGroups();
        for (SensorGroupEntity sge : sensorGroups) {
            sge.setMachine(null);
            sge.setSite(null);
        }
        sensorGroupRepo.save(sensorGroups);
        // Delete machines for site
        machineRepo.delete(siteEntity.getMachines());
        siteRepo.delete(siteEntity);
    }


    //----------------------------------------
    // Sensor groups
    //----------------------------------------

    @Override
    public List<SensorGroup> getSiteSensorGroups(long siteId) {
        SiteEntity siteEntity = siteRepo.findOne(siteId);
        if (siteEntity == null) {
            throw new NotFoundException("Site with ID " + siteId + " not found.");
        }
        List<SensorGroupEntity> sensorGroupEntities = sensorGroupRepo.findBySite(siteEntity);
        if (sensorGroupEntities == null) {
            sensorGroupEntities = new ArrayList<>();
        }
        return sensorGroupEntities.stream().map(e -> new SensorGroup(e)).collect(Collectors.toList());
    }

    @Override
    public SensorGroup addSensorGroup(long siteId, long sensorGroupId) {
        SensorGroupEntity sensorGroupEntity = sensorGroupRepo.findOne(sensorGroupId);
        SiteEntity siteEntity = siteRepo.findOne(siteId);
        if (siteEntity == null) {
            throw new NotFoundException("Site with ID " + siteId + " not found.");
        }
        if (sensorGroupEntity == null) {
            throw new NotFoundException("Sensor group with ID " + sensorGroupId + " not found.");
        }
        if (sensorGroupEntity.getSite() != null) {
            throw new ConflictException("Sensor group with ID " + sensorGroupId +
                    " belong to another site. Remove from other side first.");
        }
        sensorGroupEntity.setSite(siteEntity);
        sensorGroupEntity.setMachine(null);
        sensorGroupEntity = sensorGroupRepo.save(sensorGroupEntity);
        return new SensorGroup(sensorGroupEntity);
    }

    @Override
    public void removeSensorGroup(long siteId, long sensorGroupId) {
        SensorGroupEntity sensorGroupEntity = sensorGroupRepo.findOne(sensorGroupId);
        if (sensorGroupEntity == null || sensorGroupEntity.getSite() == null) {
            return;
        }
        if (!sensorGroupEntity.getSite().getId().equals(siteId)) {
            throw new ConflictException("Sensor group with ID " + sensorGroupId +
                    " does not belong to site with ID " + siteId);
        }
        sensorGroupEntity.setSite(null);
        sensorGroupEntity.setMachine(null);
        sensorGroupRepo.save(sensorGroupEntity);
    }

}
