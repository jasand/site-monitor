package no.sensor.db.repo;

import no.sensor.db.jpa.SensorEntity;
import no.sensor.db.jpa.SensorGroupEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by jan.arne.sandnes on 03.01.15.
 */
public interface SensorRepo extends JpaRepository<SensorEntity, Long> {
    SensorEntity findBySensorGroupAndSensorId(SensorGroupEntity sensorGroup, String sensorId);
}
