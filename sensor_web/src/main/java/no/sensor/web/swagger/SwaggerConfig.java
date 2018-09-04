package no.sensor.web.swagger;

import com.mangofactory.swagger.configuration.SpringSwaggerConfig;
import com.mangofactory.swagger.models.dto.ApiInfo;
import com.mangofactory.swagger.plugin.EnableSwagger;
import com.mangofactory.swagger.plugin.SwaggerSpringMvcPlugin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.ResponseEntity;

/**
 * Created by jan.arne.sandnes on 18.10.15.
 */
@Configuration
// Enables Swagger
@EnableSwagger
// Mandatory scan for components in the swagger package
@ComponentScan(basePackages = "com.mangofactory.swagger")
// I have strapped in properties here which contains the application contextpath and version
@PropertySource(value = "classpath:application.properties")
public class SwaggerConfig {
    @Value("${app.basepath}")
    private String appBasepath;

    @Value("${app.version}")
    private String appVersion;

    @Autowired
    private SpringSwaggerConfig springSwaggerConfig;


    @Bean
    public SwaggerSpringMvcPlugin customImplementation() {
        return new SwaggerSpringMvcPlugin(this.springSwaggerConfig)
                .apiInfo(apiInfo())
                .apiVersion(appVersion)
                .pathProvider(apiPathProvider())
                .ignoredParameterTypes(ResponseEntity.class); // Supposedly fixes a bug in Swagger UI
    }

    /**
     * API INFO
     * @return
     */
    private ApiInfo apiInfo() {
        ApiInfo apiInfo = new ApiInfo(
                "Sensor PoC API",			    // Title of your API
                "Api for Sensor PoC WebApp", 	// Description of your API
                "http://terms",					// Url to usage terms for your API, if you have any
                "post@gmail.com", 				//
                "Apache 2.0",					// Name and URL of license for users of your API
                "http://www.apache.org/licenses/LICENSE-2.0.html");
        return apiInfo;
    }


    /**
     * Class that provides your applications url context path
     */
    @Bean
    public ApiPathProvider apiPathProvider() {
        ApiPathProvider apiPathProvider = new ApiPathProvider(appBasepath);
        apiPathProvider.setDefaultSwaggerPathProvider(springSwaggerConfig.defaultSwaggerPathProvider());
        return apiPathProvider;
    }
}
