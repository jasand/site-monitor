package no.sensor.db.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Created by jan.arne.sandnes on 16.10.15.
 */
@Configuration
@PropertySource("classpath:database-test.properties")
@EnableJpaRepositories(basePackages = {
        "no.sensor.db.jpa",
        "no.sensor.db.repo"
})
@EntityScan(basePackages = {
        "no.sensor.db.jpa",
        "no.sensor.db.repo"
})
public class DatabaseTestConfiguration {
    public static Logger log = LoggerFactory.getLogger(DatabaseTestConfiguration.class);
}
