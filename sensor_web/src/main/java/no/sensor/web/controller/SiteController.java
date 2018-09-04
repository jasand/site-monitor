package no.sensor.web.controller;

import no.sensor.service.MachineService;
import no.sensor.service.SensorService;
import no.sensor.service.SiteService;
import no.sensor.service.exception.ConflictException;
import no.sensor.service.exception.InvalidInputException;
import no.sensor.service.exception.NotFoundException;
import no.sensor.service.model.Machine;
import no.sensor.service.model.Sensor;
import no.sensor.service.model.SensorGroup;
import no.sensor.service.model.Site;
import no.sensor.web.controller.error.ErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jan.arne.sandnes on 09.01.15.
 */

@CrossOrigin(origins = "http://localhost:8088")
@RestController
@RequestMapping(value = "/api")
public class SiteController {
    static final Logger LOG = LoggerFactory.getLogger(SiteController.class);

    @Autowired
    SiteService siteService;
    @Autowired
    MachineService machineService;
    @Autowired
    SensorService sensorService;


    //----------------------------------------------
    // Sites
    //----------------------------------------------
    @ResponseBody
    @RequestMapping(value = "/sites", method = RequestMethod.GET)
    public List<Site> getSites(@RequestParam(value = "siteIdent", required = false) String siteIdent, HttpServletResponse response) {
        List<Site> sites = new ArrayList<Site>();
        if (siteIdent != null) {
            Site site = siteService.getSiteBySiteIdent(siteIdent);
            LOG.info("Returning site with ident " + siteIdent);
            sites.add(site);

        } else {
            sites.addAll(siteService.getSites());
            LOG.info("Returning sites :" + sites.size());
        }
        return sites;
    }


    @ResponseBody
    @RequestMapping(value = "/sites/{siteId}", method = RequestMethod.GET)
    public Site getSite(@PathVariable long siteId, HttpServletResponse response) {
        return siteService.getSiteById(siteId);
    }

    @ResponseBody
    @RequestMapping(value = "/sites", method = RequestMethod.POST)
    public Site createSite(@RequestBody Site site) {
        return siteService.createSite(site);
    }

    @ResponseBody
    @RequestMapping(value = "/sites/{siteId}", method = RequestMethod.PUT)
    public Site updateSite(@PathVariable long siteId,
                           @RequestBody Site site,
                           HttpServletResponse response) {
        return siteService.updateSite(siteId, site);
    }

    @RequestMapping(value = "/sites/{siteId}", method = RequestMethod.DELETE)
    public void deleteSite(@PathVariable long siteId,
                           HttpServletResponse response) {
        siteService.deleteSite(siteId);
    }


    //----------------------------------------------
    // Machines
    //----------------------------------------------

    @ResponseBody
    @RequestMapping(value = "/sites/{siteId}/machines", method = RequestMethod.POST)
    public Machine createMachine(@PathVariable long siteId,
                                 @RequestBody Machine machine) {
        return machineService.createMachine(siteId, machine);
    }

    @ResponseBody
    @RequestMapping(value = "/sites/{siteId}/machines", method = RequestMethod.GET)
    public List<Machine> getMachines(@PathVariable long siteId) {
        return machineService.getMachines(siteId);
    }

    @ResponseBody
    @RequestMapping(value = "/sites/{siteId}/machines/{machineId}", method = RequestMethod.GET)
    public Machine getMachine(@PathVariable long siteId,
                              @PathVariable long machineId) {
        return machineService.getMachine(machineId);
    }

    @ResponseBody
    @RequestMapping(value = "/sites/{siteId}/machines/{machineId}", method = RequestMethod.PUT)
    public Machine updateMachine(@PathVariable long siteId,
                                 @PathVariable long machineId,
                                 @RequestBody Machine machine) {
        return machineService.updateMachine(machineId, machine);
    }

    @ResponseBody
    @RequestMapping(value = "/sites/{siteId}/machines/{machineId}", method = RequestMethod.DELETE)
    public void deleteMachine(@PathVariable long siteId,
                              @PathVariable long machineId) {
        machineService.deleteMachine(machineId);
    }

    @ResponseBody
    @RequestMapping(value = "/sites/{siteId}/machines/{machineId}/sensorgroups", method = RequestMethod.GET)
    public List<SensorGroup> getMachineSensors(@PathVariable long siteId,
                                               @PathVariable long machineId) {
        return machineService.getMachineSensorGroups(machineId);
    }

    @ResponseBody
    @RequestMapping(value = "/sites/{siteId}/machines/{machineId}/sensorgroups/{sensorGroupId}", method = RequestMethod.PUT)
    public SensorGroup addMachineSensor(@PathVariable long siteId,
                                   @PathVariable long machineId,
                                   @PathVariable long sensorGroupId) {
        return machineService.addSensorGroup(machineId, sensorGroupId);
    }

    @ResponseBody
    @RequestMapping(value = "/sites/{siteId}/machines/{machineId}/sensorgroups/{sensorGroupId}", method = RequestMethod.DELETE)
    public void removeMachineSensor(@PathVariable long siteId,
                                    @PathVariable long machineId,
                                    @PathVariable long sensorGroupId) {
        machineService.removeSensorGroup(machineId, sensorGroupId);
    }

    //

    @ResponseBody
    @RequestMapping(value = "/sites/{siteId}/sensorgroups", method = RequestMethod.GET)
    public List<SensorGroup> getSiteSensors(@PathVariable long siteId) {
        return siteService.getSiteSensorGroups(siteId);
    }


    @ResponseBody
    @RequestMapping(value = "/sites/{siteId}/sensorgroups/{sensorGroupId}", method = RequestMethod.PUT)
    public SensorGroup addSiteSensor(@PathVariable long siteId,
                                        @PathVariable long sensorGroupId) {
        return siteService.addSensorGroup(siteId, sensorGroupId);
    }

    @ResponseBody
    @RequestMapping(value = "/sites/{siteId}/sensorgroups/{sensorGroupId}", method = RequestMethod.DELETE)
    public void removeSiteSensor(@PathVariable long siteId,
                                    @PathVariable long sensorGroupId) {
        siteService.removeSensorGroup(siteId, sensorGroupId);
    }


    //---------------------------------------------------------------------------
    // Exception handling
    //---------------------------------------------------------------------------

//    @ResponseStatus(HttpStatus.UNAUTHORIZED)
//    @ExceptionHandler(AccessDeniedException.class)
//    @ResponseBody
//    ErrorResponse handleAccessDeniedException(HttpServletRequest req, AccessDeniedException ex) {
//        return new ErrorResponse("The current user does not have sufficient privileges.", ex.getMessage());
//    }

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
