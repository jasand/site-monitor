package no.sensor.service;

import no.sensor.service.model.Machine;
import no.sensor.service.model.Sensor;
import no.sensor.service.model.SensorGroup;

import java.util.List;

/**
 * Created by jan.arne.sandnes on 11.01.15.
 */
public interface MachineService {

    Machine createMachine(long siteId, Machine machine);
    List<Machine> getMachines(long siteId);
    Machine getMachine(long id);
    Machine updateMachine(long id, Machine machine);
    void deleteMachine(long id);

    List<SensorGroup> getMachineSensorGroups(long id);
    SensorGroup addSensorGroup(long machineId, long sensorGroupId);
    void removeSensorGroup(long machineId, long sensorGroupId);

}
