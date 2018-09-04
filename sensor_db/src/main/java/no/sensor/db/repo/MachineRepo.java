package no.sensor.db.repo;

import no.sensor.db.jpa.MachineEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by jan.arne.sandnes on 03.01.15.
 */
public interface MachineRepo extends JpaRepository<MachineEntity, Long> {
}
