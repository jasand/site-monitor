package no.sensor.web;

import com.mangofactory.swagger.configuration.SpringSwaggerConfig;
import no.sensor.service.configuration.ServiceConfiguration;
import no.sensor.web.swagger.SwaggerConfig;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Created by jan.arne.sandnes on 09.01.15.
 */
@Configuration
//@EnableAutoConfiguration(exclude = {
//        org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration.class/*,
//        org.springframework.boot.actuate.autoconfigure.ManagementSecurityAutoConfiguration.class*/})
@ComponentScan(basePackages = "no.sensor.web.controller")
@Import({
        ServiceConfiguration.class,
        SwaggerConfig.class
})
public class WebAppConfig {

}
