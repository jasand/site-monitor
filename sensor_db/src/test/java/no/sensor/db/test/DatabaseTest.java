package no.sensor.db.test;

import no.sensor.db.jpa.*;
import no.sensor.db.repo.*;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by jan.arne.sandnes on 18.10.15.
 */
@SuppressWarnings("SpringJavaAutowiringInspection")
@EnableAutoConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DatabaseTestConfiguration.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DatabaseTest {

    @Autowired
    SiteRepo siteRepo;
    @Autowired
    SensorRepo sensorRepo;
    @Autowired
    UnitOfMeasureRepo unitOfMeasureRepo;
    @Autowired
    SensorReadingRepo sensorReadingRepo;

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

    //--------------------------------------------------------------------------------
    // NB! Tests are run in alphabetical order. Tests depends upon previous tests
    //     to have updated the H2 DB.
    //--------------------------------------------------------------------------------

    @Test
    public void test1_canCreateSite() {
        SiteEntity siteEntity = createDataSite1();

        SiteEntity saved = siteRepo.save(siteEntity);
        assertNotEquals(new Long(0), saved.getId());
        Long id = saved.getId();

        SiteEntity fetched = siteRepo.findBySiteIdent(saved.getSiteIdent());
        assertEquals(saved.getId(), fetched.getId());
        assertEquals("SiteName1", fetched.getSiteName());
        assertEquals("ident1", fetched.getSiteIdent());
    }

    @Test
    public void test2_canInsertAndReadUnitOfMeasures() {
        List<UnitOfMeasureEntity> unitOfMeasureEntities = createUnitOfMeasureEntities();
        unitOfMeasureRepo.save(unitOfMeasureEntities);
        UnitOfMeasureEntity unitOfMeasureEntity = unitOfMeasureRepo.findByUnitNameIgnoreCase("herz");
        assertEquals("Herz", unitOfMeasureEntity.getUnitName());
        assertEquals("Hz", unitOfMeasureEntity.getUnitSymbol());
    }

//    @Test
//    public void test3_canCreateSensorsForSite() {
//        SiteEntity siteEntity = siteRepo.findBySiteIdent("ident1");
//        SensorEntity s1 = createDataSensor1();
//        SensorEntity s2 = createDataSensor2();
//        UnitOfMeasureEntity tempUnit = unitOfMeasureRepo.findByUnitNameIgnoreCase("celsius");
//        UnitOfMeasureEntity freqUnit = unitOfMeasureRepo.findByUnitNameIgnoreCase("herz");
//        s1.setUnitOfMeasure(tempUnit);
//        s2.setUnitOfMeasure(freqUnit);
//        s1.setSite(siteEntity);
//        s2.setSite(siteEntity);
//        sensorRepo.save(s1);
//        sensorRepo.save(s2);
//
//        SiteEntity updatedSite = siteRepo.findBySiteIdent("ident1");
//        assertEquals(2L, updatedSite.getSensorGroups().size());
//
//    }


    private SiteEntity createDataSite1() {
        SiteEntity siteEntity = new SiteEntity();
        siteEntity.setSiteIdent("ident1");
        siteEntity.setSiteName("SiteName1");
        siteEntity.setSiteAddress("Addr1");
        siteEntity.setContactPerson("cp1");
        siteEntity.setContactPhone("12345678");
        siteEntity.setContactEmail("c1@mail.no");
        return siteEntity;
    }

    private List<UnitOfMeasureEntity> createUnitOfMeasureEntities() {
        List<UnitOfMeasureEntity> unitOfMeasureEntities = new ArrayList<>();
        unitOfMeasureEntities.add(new UnitOfMeasureEntity("NA", "NA"));
        unitOfMeasureEntities.add(new UnitOfMeasureEntity("Celsius", "°C"));
        unitOfMeasureEntities.add(new UnitOfMeasureEntity("Lux", "lx"));
        unitOfMeasureEntities.add(new UnitOfMeasureEntity("Lumen", "lu"));
        unitOfMeasureEntities.add(new UnitOfMeasureEntity("Herz", "Hz"));
        unitOfMeasureEntities.add(new UnitOfMeasureEntity("Newton", "N"));
        unitOfMeasureEntities.add(new UnitOfMeasureEntity("Pascal", "P"));
        unitOfMeasureEntities.add(new UnitOfMeasureEntity("Joule", "J"));
        unitOfMeasureEntities.add(new UnitOfMeasureEntity("Watt", "W"));
        unitOfMeasureEntities.add(new UnitOfMeasureEntity("Volt", "V"));
        unitOfMeasureEntities.add(new UnitOfMeasureEntity("Ohm", "Ω"));
        unitOfMeasureEntities.add(new UnitOfMeasureEntity("Ampere", "A"));
        unitOfMeasureEntities.add(new UnitOfMeasureEntity("Omdreininger per minutt", "o/min"));
        return unitOfMeasureEntities;
    }

    private SensorEntity createDataSensor1() {
        SensorEntity sensor = new SensorEntity();
        sensor.setDescription("Sensor 1");
        sensor.setSensorType(SensorTypeEnum.Temperature);
        sensor.setStatus(SensorStatusEnum.NA);
        return sensor;
    }

    private SensorEntity createDataSensor2() {
        SensorEntity sensor = new SensorEntity();
        sensor.setDescription("Sensor 2");
        sensor.setSensorType(SensorTypeEnum.Light);
        sensor.setStatus(SensorStatusEnum.NA);
        return sensor;
    }

}
