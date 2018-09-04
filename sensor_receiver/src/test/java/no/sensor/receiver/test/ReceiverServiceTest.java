package no.sensor.receiver.test;

import no.sensor.db.jpa.SensorReadingQueueEntity;
import no.sensor.db.repo.SensorReadingQueueRepo;
import no.sensor.receiver.model.BasicSensorReading;
import no.sensor.receiver.service.ReceiverService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;


public class ReceiverServiceTest {

    @Mock
    SensorReadingQueueRepo sensorReadingQueueRepo;

    @InjectMocks
    ReceiverService receiverService = new ReceiverService();

    BasicSensorReading basicSensorReading;
    SensorReadingQueueEntity sensorReadingQueueEntity;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        basicSensorReading = new BasicSensorReading("sensor-group-id-1", "sensor-id-1", 666d);
        sensorReadingQueueEntity = new SensorReadingQueueEntity("sensor-group-id-1", "sensor-id-1", 666d);
        sensorReadingQueueEntity.setId(1L);
    }

    @Test
    public void verifyBasicReadingIsStoredWhenValidReading() {
        when(sensorReadingQueueRepo.saveAndFlush(any(SensorReadingQueueEntity.class))).thenReturn(sensorReadingQueueEntity);
        receiverService.storeSensorReading(basicSensorReading);
        verify(sensorReadingQueueRepo, times(1)).saveAndFlush(any(SensorReadingQueueEntity.class));
    }

    @Test(expected = IllegalArgumentException.class)
    public void verifyMissingSensorGroupIdThrowsException() {
        basicSensorReading.setSensorGroupId("");
        receiverService.storeSensorReading(basicSensorReading);
    }

    @Test(expected = IllegalArgumentException.class)
    public void verifyMissingSensorIdThrowsException() {
        basicSensorReading.setSensorId(null);
        receiverService.storeSensorReading(basicSensorReading);
    }

//    @Test(expected = IllegalArgumentException.class)
//    public void verifyTooLowValueThrowsException() {
//        basicSensorReading.setSensorValue(-1d);
//        receiverService.storeSensorReading(basicSensorReading);
//    }

//    @Test(expected = IllegalArgumentException.class)
//    public void verifyTooHighValueThrowsException() {
//        basicSensorReading.setSensorValue(1024d);
//        receiverService.storeSensorReading(basicSensorReading);
//    }

    @Test
    public void verifyMinValueIsStored() {
        basicSensorReading.setSensorValue(0d);
        when(sensorReadingQueueRepo.saveAndFlush(any(SensorReadingQueueEntity.class))).thenReturn(sensorReadingQueueEntity);
        receiverService.storeSensorReading(basicSensorReading);
        verify(sensorReadingQueueRepo, times(1)).saveAndFlush(any(SensorReadingQueueEntity.class));
    }

    @Test
    public void verifyMaxValueIsStored() {
        basicSensorReading.setSensorValue(1023d);
        when(sensorReadingQueueRepo.saveAndFlush(any(SensorReadingQueueEntity.class))).thenReturn(sensorReadingQueueEntity);
        receiverService.storeSensorReading(basicSensorReading);
        verify(sensorReadingQueueRepo, times(1)).saveAndFlush(any(SensorReadingQueueEntity.class));
    }

}