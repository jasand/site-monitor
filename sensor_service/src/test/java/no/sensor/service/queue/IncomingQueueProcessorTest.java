package no.sensor.service.queue;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

public class IncomingQueueProcessorTest {

//    @Mock
//    SensorGroupRepo sensorHubRepo;
//    @Mock
//    SensorRepo sensorRepo;
//    @Mock
//    SensorReadingRepo sensorReadingRepo;
//
//    @InjectMocks
//    ReceiverService receiverService = new ReceiverService();
//
//    BasicSensorReading basicSensorReading;
//    SensorGroupEntity sensorHubEntity;
//    SensorEntity sensorEntity;
//
//    @Before
//    public void setup() {
//        MockitoAnnotations.initMocks(this);
//
//        basicSensorReading = new BasicSensorReading();
//        basicSensorReading.setSensorGroupId("sensor-hub-id-1");
//        basicSensorReading.setSensorId("sensor-id-1");
//        basicSensorReading.setSensorValue(666);
//
//        sensorHubEntity = new SensorGroupEntity();
//        sensorHubEntity.setSensorGroupId("sensor-hub-id-1");
//        sensorHubEntity.setId(1L);
//
//        sensorEntity = new SensorEntity();
//        sensorEntity.setId(1L);
//        sensorEntity.setSensorIdent("sensor-id-1");
//        sensorEntity.setConversionFunction("X/2"); // => raw value / 2
//        sensorEntity.setSensorGroup(sensorHubEntity);
//
//    }
//
//    @Test
//    public void verifyBasicReadingIsStoredWhenSiteAndSensorFound() {
//        when(sensorRepo.findBySensorIdent("sensor-id-1")).thenReturn(sensorEntity);
//
//        receiverService.storeSensorReading(basicSensorReading);
//
//        verify(sensorRepo, times(1)).findBySensorIdent("sensor-id-1");
//        verify(sensorReadingRepo, times(1)).save(any(SensorReadingEntity.class));
//        verify(sensorRepo, times(1)).save(sensorEntity);
//        verifyNoMoreInteractions(sensorReadingRepo);
//        verifyNoMoreInteractions(sensorRepo);
//        assertEquals(new Integer(333), sensorEntity.getLastValue());
//    }
//
//    @Test
//    public void verifySiteAndSensorCreatedIfNotFound() {
//        when(sensorRepo.findBySensorIdent("sensor-id-1")).thenReturn(null);
//        when(sensorHubRepo.findBySensorGroupId("sensor-hub-id-1")).thenReturn(null);
//        when(sensorHubRepo.saveAndFlush(any(SensorGroupEntity.class))).thenReturn(sensorHubEntity);
//        when(sensorRepo.saveAndFlush(any(SensorEntity.class))).thenReturn(sensorEntity);
//
//        receiverService.storeSensorReading(basicSensorReading);
//
//        verify(sensorRepo).findBySensorIdent("sensor-id-1");
//        verify(sensorHubRepo).findBySensorGroupId("sensor-hub-id-1");
//        verify(sensorHubRepo).saveAndFlush(any(SensorGroupEntity.class));
//        verify(sensorReadingRepo, times(1)).save(any(SensorReadingEntity.class));
//        verify(sensorRepo, times(1)).saveAndFlush(any(SensorEntity.class));
//        verify(sensorRepo, times(1)).save(any(SensorEntity.class));
//    }

}
