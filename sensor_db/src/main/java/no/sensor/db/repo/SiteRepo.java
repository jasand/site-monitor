package no.sensor.db.repo;

import no.sensor.db.jpa.SiteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.RepositoryDefinition;

/**
 * Created by jan.arne.sandnes on 03.01.15.
 */

@RepositoryDefinition(domainClass=SiteEntity.class, idClass=Long.class)
public interface SiteRepo extends JpaRepository<SiteEntity, Long> {
    public SiteEntity findBySiteIdent(String siteIdent);
}
