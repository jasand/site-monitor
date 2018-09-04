package no.sensor.service.impl;

import no.sensor.db.jpa.*;
import no.sensor.db.repo.*;
import no.sensor.service.SensorService;
import no.sensor.service.exception.ConflictException;
import no.sensor.service.exception.NotFoundException;
import no.sensor.service.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by jan.arne.sandnes on 11.01.15.
 */

@Service
public class SensorServiceImpl implements SensorService {

    @Autowired
    private SensorReadingRepo sensorReadingRepo;
    @Autowired
    private SensorRepo sensorRepo;
    @Autowired
    private UnitOfMeasureRepo unitOfMeasureRepo;
    @Autowired
    private SensorGroupRepo sensorGroupRepo;
    @Autowired
    private SiteRepo siteRepo;

    @Override
    public List<Sensor> getAllSensors() {
        List<SensorEntity> entities = sensorRepo.findAll();
        return entities.stream().map(e -> new Sensor(e)).collect(Collectors.toList());
    }

    @Override
    public Sensor findSensorById(long id) {
        SensorEntity entity = sensorRepo.findOne(id);
        if (entity == null) {
            throw new NotFoundException("Could not find sensor with ID " + id);
        }
        return new Sensor(entity);
    }

    @Override
    public Sensor updateSensor(long id, Sensor sensor) {
        if (id != sensor.getId()) {
            throw new IllegalArgumentException("Sensor.getId() must match id given in path.");
        }
        SensorEntity entity = sensorRepo.findOne(id);
        entity = sensor.copyToSensorEntity(entity);
        entity = sensorRepo.save(entity);
        return new Sensor(entity);
    }

    @Override
    public List<SensorReading> getSensorReadingsBetween(long sensorId, Date from, Date to) {
        List<SensorReadingEntity> entities =
                sensorReadingRepo.findBySensorIdAndTimeBetweenOrderByIdDesc(sensorId, dateToTs(from), dateToTs(to));
        List<SensorReading> readings = entities.stream().map(e -> new SensorReading(e)).collect(Collectors.toList());
        return readings;
    }

    private Timestamp dateToTs(Date d) {
        return new Timestamp(d.getTime());
    }

    @Override
    public List<UnitOfMeasure> getUnitsOfMeasure() {
        List<UnitOfMeasureEntity> entities = unitOfMeasureRepo.findAll();
        return entities.stream().map(e -> new UnitOfMeasure(e)).collect(Collectors.toList());
    }

    @Override
    public List<SensorGroup> getAllSensorGroups() {
        List<SensorGroupEntity> hubEntities = sensorGroupRepo.findAll();
        return hubEntities.stream().map(e -> new SensorGroup(e)).collect(Collectors.toList());
    }

    @Override
    public SensorGroup findSensorGroupById(Long id) {
        SensorGroupEntity e = sensorGroupRepo.findOne(id);
        if (e == null) {
            throw new NotFoundException("No sensor group with ID " + id);
        }
        return new SensorGroup(e);
    }

    @Override
    public SensorGroup findSensorGroupBySensorGroupId(String sensorGroupId) {
        SensorGroupEntity e = sensorGroupRepo.findBySensorGroupId(sensorGroupId);
        if (e == null) {
            throw new NotFoundException("No sensor group with sensorGroupId " + sensorGroupId);
        }
        return new SensorGroup(e);
    }

    @Override
    public SensorGroup updateSensorGroup(Long id, SensorGroup sensorGroup) {
        if (id == null || sensorGroup == null || !id.equals(sensorGroup.getId())) {
            throw new ConflictException("Path ID and Object ID not matching");
        }
        SensorGroupEntity e = sensorGroupRepo.findOne(sensorGroup.getId());
        if (e == null) {
            throw new NotFoundException("No sensor group with ID " + sensorGroup.getId());
        }
        e.setDescription(sensorGroup.getDescription());  // Only updateable here
        if (sensorGroup.getSiteId() != null) {
            SiteEntity siteEntity = siteRepo.findOne(sensorGroup.getSiteId());
            if (siteEntity == null) {
                throw new NotFoundException("No site with ID " + sensorGroup.getSiteId());
            }
            e.setSite(siteEntity);
        } else {
            e.setSite(null);
        }
        e = sensorGroupRepo.save(e);
        return new SensorGroup(e);
    }

}
