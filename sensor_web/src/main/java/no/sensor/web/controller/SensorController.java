package no.sensor.web.controller;

import no.sensor.service.SensorService;
import no.sensor.service.exception.ConflictException;
import no.sensor.service.exception.NotFoundException;
import no.sensor.service.model.*;
import no.sensor.web.controller.error.ErrorResponse;
import no.sensor.service.exception.InvalidInputException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by jan.arne.sandnes on 09.01.15.
 */

@CrossOrigin(origins = "http://localhost:8088")
@RestController
@RequestMapping(value = "/api")
public class SensorController {
    static final Logger LOG = LoggerFactory.getLogger(SensorController.class);

    @Autowired
    SensorService sensorService;

    @ResponseBody
    @PreAuthorize("hasRole('ROLE_USER')")
    @RequestMapping(value = "/sensors", method = RequestMethod.GET)
    public List<Sensor> getSensors() {
        return sensorService.getAllSensors();
    }

    @ResponseBody
    @PreAuthorize("hasRole('ROLE_USER')")
    @RequestMapping(value = "/sensors/{sensorId}", method = RequestMethod.GET)
    public Sensor findSensorById(@PathVariable long sensorId) {
        return sensorService.findSensorById(sensorId);
    }


    @ResponseBody
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/sensors/{sensorId}", method = RequestMethod.PUT)
    public Sensor updateSensor(@PathVariable long sensorId,
                               @RequestBody Sensor sensor) {
        sensor = sensorService.updateSensor(sensorId, sensor);
        return sensor;
    }


    @ResponseBody
    @PreAuthorize("hasRole('ROLE_USER')")
    @RequestMapping(value = "/sensors/{sensorId}/readings", method = RequestMethod.GET)
    public List<SensorReading> getReadingsForSensorBetween(@PathVariable long sensorId,
                                                           @RequestParam(value = "from", required = false) String from,
                                                           @RequestParam(value = "to", required = false) String to,
                                                           HttpServletResponse response) {
        if (from == null && to == null) {
            throw new InvalidInputException("At least one of the 'from' and 'to' query parameters " +
                    "must be supplied when fetching sensor readings");
        }
        Date fromDate = null, toDate = null;
        LOG.info("SensorId: {}, from: {}, to: {}", sensorId, from, to);
        try {
            fromDate = parseDateOrTimestamp(from);
        } catch (ParseException pEx) {
            throw new InvalidInputException("Could not parse query param 'from'.");
        }
        try {
            toDate = parseDateOrTimestamp(to);
        } catch (ParseException pEx) {
            throw new InvalidInputException("Could not parse query param 'to'.");
        }
        if (fromDate == null) {
            fromDate = new Date(0);
        }
        if (toDate == null) {
            toDate = new Date();
        }
        LOG.info("Fetching sensor readings for sensorId " + sensorId);
        try {
            List<SensorReading> readings = sensorService.getSensorReadingsBetween(sensorId, fromDate, toDate);
            LOG.info("Returning " + readings.size() + " readings for sensorId " + sensorId);
            return readings;
        } catch (Exception e) {
            LOG.error("Error fetching sensor readings for sensorId " + sensorId, e);
            throw new RuntimeException("Error fetching sensor readings for sensorId " + sensorId, e);
        }
    }


    @ResponseBody
    @PreAuthorize("hasRole('ROLE_USER')")
    @RequestMapping(value = "/units", method = RequestMethod.GET)
    public List<UnitOfMeasure> getUnitsOfMeasure() {
        return sensorService.getUnitsOfMeasure();
    }

    @ResponseBody
    @PreAuthorize("hasRole('ROLE_USER')")
    @RequestMapping(value = "/sensortypes", method = RequestMethod.GET)
    public List<String> getLegalSensorTypes() {
        List<String> types = new ArrayList<>();
        for (SensorType type : SensorType.values()) {
            types.add(type.name());
        }
        return types;
    }

    @ResponseBody
    @PreAuthorize("hasRole('ROLE_USER')")
    @RequestMapping(value = "/sensorgroups", method = RequestMethod.GET)
    public List<SensorGroup> getAllSensorGroups() {
        return sensorService.getAllSensorGroups();
    }

    @ResponseBody
    @PreAuthorize("hasRole('ROLE_USER')")
    @RequestMapping(value = "/sensorgroups/{id}", method = RequestMethod.GET)
    public SensorGroup getSensorGroup(@PathVariable("id") Long id) {
        return sensorService.findSensorGroupById(id);
    }

    @ResponseBody
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/sensorgroups/{id}", method = RequestMethod.PUT)
    public SensorGroup updateSensorGroup(@PathVariable("id") Long id,
                                         @RequestBody SensorGroup sensorGroup) {
        LOG.info("Updating sensorgroup " + sensorGroup.getId() + ",  site: " + sensorGroup.getSiteId());
        return sensorService.updateSensorGroup(id, sensorGroup);
    }


    //------------------------
    // private methods
    //------------------------

    private Date parseDateOrTimestamp(String dateString) throws ParseException {
        if (dateString == null) {
            return null;
        }
        Date date = null;
        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdfTimestamp = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
        if (dateString.length() <= 10) {
            date = sdfDate.parse(dateString);
        } else {
            date = sdfTimestamp.parse(dateString);
        }
        return date;
    }

    //---------------------------------------------------------------------------
    // Exception handling
    //---------------------------------------------------------------------------

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(AccessDeniedException.class)
    @ResponseBody
    ErrorResponse handleAccessDeniedException(HttpServletRequest req, AccessDeniedException ex) {
        return new ErrorResponse("The current user does not have sufficient privileges.", ex.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidInputException.class)
    @ResponseBody
    ErrorResponse handleInvalidInputException(HttpServletRequest req, InvalidInputException ex) {
        LOG.info("Invalid input: {}", ex.getMessage());
        return new ErrorResponse("InvalidInput.", ex.getMessage());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    @ResponseBody
    ErrorResponse handleNotFoundException(HttpServletRequest req, NotFoundException ex) {
        LOG.info("Not found: {}", ex.getMessage());
        return new ErrorResponse("Not found.", ex.getMessage());
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(ConflictException.class)
    @ResponseBody
    ErrorResponse handleConflictException(HttpServletRequest req, ConflictException ex) {
        LOG.info("Conflict: {}", ex.getMessage());
        return new ErrorResponse("Conflict.", ex.getMessage());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    @ResponseBody
    ErrorResponse handleException(HttpServletRequest req, Exception ex) {
        LOG.info("Unexpected exception: {}", ex.getClass().getName());
        ex.printStackTrace();
        return new ErrorResponse("Unexpected Exception caught.", ex.getMessage());
    }
}
