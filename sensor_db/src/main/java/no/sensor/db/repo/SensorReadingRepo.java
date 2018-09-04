package no.sensor.db.repo;

import no.sensor.db.jpa.SensorEntity;
import no.sensor.db.jpa.SensorReadingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * Created by jan.arne.sandnes on 03.01.15.
 */
public interface SensorReadingRepo extends JpaRepository<SensorReadingEntity, Long> {
    List<SensorReadingEntity> findBySensorIdAndTimeAfterOrderByIdDesc(long sensorId, Timestamp after);
    List<SensorReadingEntity> findBySensorIdAndTimeBetweenOrderByIdDesc(long sensorId, Timestamp from, Timestamp to);
}
