package no.sensor.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

/**
 * Created by jan.arne.sandnes on 12.10.15.
 */
@SpringBootApplication
@Import({WebAppConfig.class})
@PropertySource("classpath:application.properties")
public class SensorApplication extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(SensorApplication.class, args);
    }
}
