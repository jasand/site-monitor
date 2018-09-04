package no.sensor.db.repo;

import no.sensor.db.jpa.MachineEntity;
import no.sensor.db.jpa.SensorGroupEntity;
import no.sensor.db.jpa.SiteEntity;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by jasand on 22.11.2016.
 */
public interface SensorGroupRepo extends JpaRepository<SensorGroupEntity, Long> {
    SensorGroupEntity findBySensorGroupId(String sensorGroupId);
    List<SensorGroupEntity> findByMachine(MachineEntity machine);
    List<SensorGroupEntity> findBySite(SiteEntity site);
}
