package no.sensor.service.queue;

import no.sensor.db.jpa.SensorEntity;
import no.sensor.db.jpa.SensorGroupEntity;
import no.sensor.db.jpa.SensorReadingEntity;
import no.sensor.db.jpa.SensorReadingQueueEntity;
import no.sensor.db.repo.SensorGroupRepo;
import no.sensor.db.repo.SensorReadingQueueRepo;
import no.sensor.db.repo.SensorReadingRepo;
import no.sensor.db.repo.SensorRepo;
import no.sensor.service.conversion.SensorValueConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.sql.Timestamp;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by jasand on 24.11.2016.
 */

@Component
public class IncomingQueueProcessor {
    private static final Logger LOG = LoggerFactory.getLogger(IncomingQueueProcessor.class);

    private static final AtomicInteger ATOMIC_INTEGER = new AtomicInteger(0);

    private ExecutorService executorService;

    @Autowired
    private SensorReadingQueueRepo sensorReadingQueueRepo;
    @Autowired
    private SensorReadingRepo sensorReadingRepo;
    @Autowired
    private SensorRepo sensorRepo;
    @Autowired
    private SensorGroupRepo sensorGroupRepo;


    @PostConstruct
    public void initializeIncomingQueueProcessor() {
        LOG.info("initializeIncomingQueueProcessor");
        executorService = Executors.newSingleThreadExecutor(new SimpleThreadFactory());
        executorService.execute(new Runnable() {

            @Override
            public void run() {
                LOG.info("initializeIncomingQueueProcessor thread started");
                while (true) {
                    try {
                        boolean didProcess = processNextReadingInQueue();
                        if (!didProcess) {
                            try {
                                Thread.currentThread().sleep(1000);
                            } catch (InterruptedException intEx) {
                                LOG.info("{} was interrupted.", Thread.currentThread().getName());
                            }
                        }
                    } catch (Exception e) {
                        LOG.error("error: ", e);
                    }
                }
            }
        });

        executorService.shutdown();
    }


    private boolean processNextReadingInQueue() {
        SensorReadingQueueEntity sensorReadingQueueEntity = sensorReadingQueueRepo.getNextForProcessing();
        if (sensorReadingQueueEntity != null) {
            SensorGroupEntity sensorGroup = sensorGroupRepo.findBySensorGroupId(sensorReadingQueueEntity.getSensorGroupId());
            if (sensorGroup == null) {
                sensorGroup = new SensorGroupEntity(sensorReadingQueueEntity.getSensorGroupId());
                sensorGroup.setFirstConnect(sensorReadingQueueEntity.getReceived());
                sensorGroup = sensorGroupRepo.saveAndFlush(sensorGroup);
            }
            SensorEntity sensor = sensorRepo.findBySensorGroupAndSensorId(sensorGroup, sensorReadingQueueEntity.getSensorId());
            if (sensor == null) {
                sensor = new SensorEntity();
                sensor.setSensorId(sensorReadingQueueEntity.getSensorId());
                sensor.setSensorGroup(sensorGroup);
                sensor.setFirstConnect(sensorReadingQueueEntity.getReceived());
                // Copies rawData to value field as initial default.
                sensor.setConversionFunction("X");
                sensor = sensorRepo.saveAndFlush(sensor);
            }
            SensorReadingEntity newSensorReading = new SensorReadingEntity(sensor.getId(), sensorReadingQueueEntity.getSensorValue(),
                    SensorValueConverter.convertRawValue(sensorReadingQueueEntity.getSensorValue(), sensor.getConversionFunction()),
                    sensorReadingQueueEntity.getReceived());
            LOG.debug("Saving sensor reading: {}", newSensorReading);

            sensor.setLastValue(newSensorReading.getValue());
            sensor.setLastConnect(sensorReadingQueueEntity.getReceived());

            sensorReadingQueueEntity.setProcessed(new Timestamp(new Date().getTime()));
            sensorReadingRepo.save(newSensorReading);
            sensorRepo.save(sensor);
            sensorReadingQueueRepo.saveAndFlush(sensorReadingQueueEntity);

            // Could do more calculations and create event here...
            LOG.debug("Processed: {}", sensorReadingQueueEntity);
            return true;
        }
        return false;
    }

    class SimpleThreadFactory implements ThreadFactory {
        public Thread newThread(Runnable r) {
            Thread thread = new Thread(r);
            thread.setName("incoming-queue-processor-" + ATOMIC_INTEGER.incrementAndGet());
            thread.setDaemon(true);
            return thread;
        }
    }

}
