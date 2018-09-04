package no.sensor.db.repo;

import no.sensor.db.jpa.UnitOfMeasureEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by jan.arne.sandnes on 03.01.15.
 */
public interface UnitOfMeasureRepo extends JpaRepository<UnitOfMeasureEntity, Long> {
    UnitOfMeasureEntity findByUnitNameIgnoreCase(String unitName);
}
