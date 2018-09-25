package no.sensor.web.controller;

import no.sensor.service.AuthService;
import no.sensor.service.model.auth.AuthRequest;
import no.sensor.service.model.auth.AuthResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@CrossOrigin
@RestController
@RequestMapping(value = "/api")
public class AuthController {
    static final Logger LOG = LoggerFactory.getLogger(SensorController.class);

    @Autowired
    AuthService authService;

    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json")
    public AuthResponse login(@RequestBody AuthRequest authRequest, HttpServletResponse response) {
        AuthResponse authResponse = authService.login(authRequest);
        response.addCookie(new Cookie("X-Auth-Token", authResponse.getToken()));
        return authResponse;
    }

    @RequestMapping(value = "/logout/{token}", method = RequestMethod.DELETE, produces = "application/json")
    public void logout(@PathVariable String token, HttpServletResponse response) {
        authService.logout(token);
    }
}
