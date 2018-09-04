package no.sensor.service;

import no.sensor.service.model.Sensor;
import no.sensor.service.model.SensorGroup;
import no.sensor.service.model.SensorReading;
import no.sensor.service.model.UnitOfMeasure;

import java.util.Date;
import java.util.List;

/**
 * Created by jan.arne.sandnes on 11.01.15.
 */
public interface SensorService {
    // Sensors
    Sensor findSensorById(long id);
    Sensor updateSensor(long id, Sensor sensor);
    List<Sensor> getAllSensors();

    // Sensor readings
    public List<SensorReading> getSensorReadingsBetween(long sensorId, Date from, Date to);

    // Units of measure
    List<UnitOfMeasure> getUnitsOfMeasure();

    // Sensor groups
    List<SensorGroup> getAllSensorGroups();
    SensorGroup findSensorGroupById(Long id);
    SensorGroup findSensorGroupBySensorGroupId(String sensorGroupId);
    SensorGroup updateSensorGroup(Long id, SensorGroup sensorGroup);
}
