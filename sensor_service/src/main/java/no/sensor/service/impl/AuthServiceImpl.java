package no.sensor.service.impl;

import no.sensor.db.jpa.RoleEntity;
import no.sensor.db.jpa.UserEntity;
import no.sensor.db.jpa.UserSessionEntity;
import no.sensor.db.repo.UserRepo;
import no.sensor.db.repo.UserSessionRepo;
import no.sensor.service.AuthService;
import no.sensor.service.model.auth.AuthException;
import no.sensor.service.model.auth.AuthRequest;
import no.sensor.service.model.auth.AuthResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AuthServiceImpl implements AuthService {

    private static long THIRTY_MIN_MILLISECS = 1000 * 60 * 30;

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private UserSessionRepo userSessionRepo;

    @Override
    public AuthResponse login(AuthRequest authRequest) {
        if (validUseNameAndPassword(authRequest)) {
            UserEntity userEntity = userRepo.findByUserName(authRequest.getUserName());
            String token = generateToken();
            UserSessionEntity userSessionEntity = new UserSessionEntity(
                    token,
                    userEntity.getUserName(),
                    new Timestamp(System.currentTimeMillis()+THIRTY_MIN_MILLISECS)
            );
            userSessionRepo.save(userSessionEntity);

            return new AuthResponse(authRequest.getUserName(),
                    userEntity.getRoles().stream().map(role -> role.getRole()).collect(Collectors.toList()),
                    token,
                    new Date(userSessionEntity.getExpires().getTime()));
        }
        throw new AuthException("User not authorized");
    }

    @Override
    public AuthResponse getAuthForToken(String token) {
        UserSessionEntity userSessionEntity = userSessionRepo.findOne(token);
        if (userSessionEntity != null) {
            UserEntity userEntity = userRepo.findByUserName(userSessionEntity.getUserName());
            if (userEntity != null) {
                return new AuthResponse(userEntity.getUserName(),
                        userEntity.getRoles().stream().map(RoleEntity::getRole).collect(Collectors.toList()),
                        token,
                        new Date(userSessionEntity.getExpires().getTime()));
            }
        }
        throw new AuthException("User not authorized");
    }



    private boolean validUseNameAndPassword(AuthRequest authRequest) {
        UserEntity userEntity = userRepo.findByUserName(authRequest.getUserName());
        return userEntity != null && userEntity.getPassword().equals(authRequest.getPassword());
    }

    private String generateToken() {
        String token;
        do {
            token = UUID.randomUUID().toString();
        } while (userSessionRepo.findOne(token) != null);
        return token;
    }
}
