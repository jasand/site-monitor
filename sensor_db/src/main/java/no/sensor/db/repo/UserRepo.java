package no.sensor.db.repo;

import no.sensor.db.jpa.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.RepositoryDefinition;

@RepositoryDefinition(domainClass=UserEntity.class, idClass=Long.class)
public interface UserRepo extends JpaRepository<UserEntity, Long> {
    UserEntity findByUserName(String userName);
}
