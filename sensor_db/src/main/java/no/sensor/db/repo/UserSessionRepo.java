package no.sensor.db.repo;

import no.sensor.db.jpa.UserSessionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserSessionRepo extends JpaRepository<UserSessionEntity, String> {
}
