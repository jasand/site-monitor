package no.sensor.receiver.service;

import no.sensor.db.jpa.SensorReadingQueueEntity;
import no.sensor.db.repo.SensorReadingQueueRepo;
import no.sensor.receiver.model.BasicSensorReading;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ReceiverService {
    static final Logger LOG = LoggerFactory.getLogger(ReceiverService.class);

    @Autowired
    private SensorReadingQueueRepo sensorReadingQueueRepo;


    public void storeSensorReading(BasicSensorReading reading) {
        if (!validSensorReading(reading)) {
            throw new IllegalArgumentException("Invalid input data.");
        }
        SensorReadingQueueEntity entity = new SensorReadingQueueEntity(reading.getSensorGroupId(),
                reading.getSensorId(), reading.getSensorValue());
        entity = sensorReadingQueueRepo.saveAndFlush(entity);
        assert entity.getId() != null;
    }

    private boolean validSensorReading(BasicSensorReading reading) {
        return !(reading.getSensorGroupId() == null || reading.getSensorGroupId().equals("") ||
                reading.getSensorId() == null || reading.getSensorId().equals("") ||
                reading.getSensorValue() == null);
    }

}
