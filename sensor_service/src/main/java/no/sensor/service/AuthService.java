package no.sensor.service;

import no.sensor.service.model.auth.AuthRequest;
import no.sensor.service.model.auth.AuthResponse;

public interface AuthService {
    AuthResponse login(AuthRequest authRequest);
    AuthResponse getAuthForToken(String token);
    void logout(String token);
}
