package no.sensor.service;

import no.sensor.db.configuration.DatabaseConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Created by jan.arne.sandnes on 16.10.15.
 */
@Configuration
@ComponentScan(basePackages = "no.sensor.service.impl")
@Import({
})
public class ServiceTestConfiguration {
}
