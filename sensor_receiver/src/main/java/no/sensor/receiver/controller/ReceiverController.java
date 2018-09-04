package no.sensor.receiver.controller;

import no.sensor.receiver.model.BasicSensorReading;
import no.sensor.receiver.service.ReceiverService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by jan.arne.sandnes on 03.01.15.
 */

@RestController
public class ReceiverController {
    static final Logger LOG = LoggerFactory.getLogger(ReceiverController.class);

    @Autowired
    ReceiverService receiverService;

    @ResponseBody
    @RequestMapping(value="/basic", method= RequestMethod.POST)
    public String storeSensorReading(@RequestBody BasicSensorReading reading) {
        try {
            receiverService.storeSensorReading(reading);
            LOG.debug("SensorReading stored");
            return "OK";
        } catch (Exception e) {
            LOG.error("FAILED storing SensorReading {}", reading, e);
            return "FAILED";
        }
    }

}
