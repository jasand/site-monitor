package no.sensor.service.impl;

import no.sensor.db.jpa.MachineEntity;
import no.sensor.db.jpa.SensorGroupEntity;
import no.sensor.db.jpa.SiteEntity;
import no.sensor.db.repo.MachineRepo;
import no.sensor.db.repo.SensorGroupRepo;
import no.sensor.db.repo.SensorRepo;
import no.sensor.db.repo.SiteRepo;
import no.sensor.service.MachineService;
import no.sensor.service.exception.ConflictException;
import no.sensor.service.exception.NotFoundException;
import no.sensor.service.model.Machine;
import no.sensor.service.model.SensorGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by jan.arne.sandnes on 11.01.15.
 */

@Service
public class MachineServiceImpl implements MachineService {

    @Autowired
    private MachineRepo machineRepo;
    @Autowired
    private SiteRepo siteRepo;
    @Autowired
    private SensorGroupRepo sensorGroupRepo;

    @Override
    public Machine createMachine(long siteId, Machine machine) {
        SiteEntity siteEntity = siteRepo.findOne(siteId);
        MachineEntity machineEntity = new MachineEntity();
        machineEntity.setMachineName(machine.getName());
        machineEntity.setDescription(machine.getDescription());
        machineEntity.setDeleted(false);
        machineEntity.setSensorGroups(new ArrayList<SensorGroupEntity>());
        machineEntity.setSite(siteEntity);
        machineEntity = machineRepo.save(machineEntity);
        return new Machine(machineEntity);
    }

    @Override
    public List<Machine> getMachines(long siteId) {
        SiteEntity siteEntity = siteRepo.findOne(siteId);
        List<MachineEntity> entities = siteEntity.getMachines();
        if (entities == null) {
            entities = new ArrayList<>(); // Not 404 on list operations...
        }
        return entities.stream().map(e -> new Machine(e)).collect(Collectors.toList());
    }

    @Override
    public Machine getMachine(long id) {
        MachineEntity machineEntity = machineRepo.findOne(id);
        if (machineEntity == null) {
            throw new NotFoundException("Machine with ID " + id + " not found.");
        }
        return new Machine(machineEntity);
    }

    @Override
    public Machine updateMachine(long id, Machine machine) {
        if (id != machine.getId()) {
            throw new IllegalArgumentException("Machine.getId() must match id given in path.");
        }
        MachineEntity machineEntity = machineRepo.findOne(id);
        if (machineEntity == null) {
            throw new NotFoundException("Machine with ID " + id + " not found.");
        }
        machineEntity.setMachineName(machine.getName());
        machineEntity.setDescription(machine.getDescription());
        machineEntity.setDeleted(machine.isDeleted());
        machineEntity = machineRepo.save(machineEntity);
        return new Machine(machineEntity);
    }

    @Override
    public void deleteMachine(long id) {
        MachineEntity machineEntity = machineRepo.findOne(id);
        if (machineEntity != null) {
            machineRepo.delete(id);
        }
    }

    @Override
    public List<SensorGroup> getMachineSensorGroups(long id) {
        MachineEntity machineEntity = machineRepo.findOne(id);
        if (machineEntity == null) {
            throw new NotFoundException("Machine with ID " + id + " not found.");
        }
//        List<SensorGroupEntity> sensorGroupEntities = machineEntity.getSensorGroups();
        List<SensorGroupEntity> sensorGroupEntities = sensorGroupRepo.findByMachine(machineEntity);
        if (sensorGroupEntities == null) {
            sensorGroupEntities = new ArrayList<>();
        }
        return sensorGroupEntities.stream().map(e -> new SensorGroup(e)).collect(Collectors.toList());
    }

    @Override
    public SensorGroup addSensorGroup(long machineId, long sensorGroupId) {
        SensorGroupEntity sensorGroupEntity = sensorGroupRepo.findOne(sensorGroupId);
        MachineEntity machineEntity = machineRepo.findOne(machineId);
        if (!sensorGroupEntity.getSite().getId().equals(machineEntity.getSite().getId())) {
            throw new ConflictException("Sensor group with ID " + sensorGroupId +
                    " and machine with ID " + machineId + " belong to separate sites.");
        }
        sensorGroupEntity.setMachine(machineEntity);
        sensorGroupEntity = sensorGroupRepo.save(sensorGroupEntity);
        return new SensorGroup(sensorGroupEntity);
    }

    @Override
    public void removeSensorGroup(long machineId, long sensorGroupId) {
        SensorGroupEntity sensorGroupEntity = sensorGroupRepo.findOne(sensorGroupId);
        if (sensorGroupEntity == null || sensorGroupEntity.getMachine() == null) {
            return;
        }
        if (!sensorGroupEntity.getMachine().getId().equals(machineId)) {
            throw new ConflictException("Sensor group with ID " + sensorGroupId +
                    " does not belong to machine with ID " + machineId);
        }
        sensorGroupEntity.setMachine(null);
        sensorGroupRepo.save(sensorGroupEntity);
    }
}
