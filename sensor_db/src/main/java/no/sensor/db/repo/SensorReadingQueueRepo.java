package no.sensor.db.repo;

import no.sensor.db.jpa.SensorReadingQueueEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by jasand on 24.11.2016.
 */
public interface SensorReadingQueueRepo extends JpaRepository<SensorReadingQueueEntity, Long> {
    @Query("select A from SensorReadingQueueEntity A where A.id = (select min(B.id) from SensorReadingQueueEntity B where B.processed is null)")
    SensorReadingQueueEntity getNextForProcessing();
}
