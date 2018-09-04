package no.sensor.receiver;

import no.sensor.db.configuration.DatabaseConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

/**
 * Created by jan.arne.sandnes on 12.10.15.
 */
@SpringBootApplication
@Import({DatabaseConfiguration.class})
@PropertySource("classpath:receiver.properties")
public class ReceiverApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        ApplicationContext appCtx = SpringApplication.run(ReceiverApplication.class, args);
    }
}
