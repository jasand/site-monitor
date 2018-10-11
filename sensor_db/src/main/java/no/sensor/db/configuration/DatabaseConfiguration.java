package no.sensor.db.configuration;

import org.apache.commons.dbcp.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.sql.DataSource;

/**
 * Created by jan.arne.sandnes on 16.10.15.
 */
@Configuration
@EnableJpaRepositories(basePackages = {
        "no.sensor.db.jpa",
        "no.sensor.db.repo"
})
@EntityScan(basePackages = {
        "no.sensor.db.jpa",
        "no.sensor.db.repo"
})
public class DatabaseConfiguration {
    public static Logger log = LoggerFactory.getLogger(DatabaseConfiguration.class);

    @Bean
    public DataSource dataSource(Environment environment) {
        BasicDataSource dataSource = new BasicDataSource();
        System.out.println("Building datasource");
        String dbName = System.getProperty("RDS_DB_NAME");
        String userName = System.getProperty("RDS_USERNAME");
        String password = System.getProperty("RDS_PASSWORD");
        String hostname = System.getProperty("RDS_HOSTNAME");
        String port = System.getProperty("RDS_PORT");
        log.info("AWS: " + hostname + " - " + port + " - " + dbName);

        if (dbName != null) {
            log.info("AWS: building AWS datasource");
            String jdbcUrl = "jdbc:postgresql://" + hostname + ":" + port + "/" + dbName;
            dataSource.setDriverClassName(environment.getRequiredProperty("db.driver.classname"));
            dataSource.setUrl(jdbcUrl);
            dataSource.setUsername(userName);
            dataSource.setPassword(password);
        } else {
            log.info("No AWS environments. Defaulting to propertyfile.");
            dataSource.setDriverClassName(environment.getRequiredProperty("db.driver.classname"));
            dataSource.setUrl(environment.getRequiredProperty("spring.datasource.url"));
            dataSource.setUsername(environment.getRequiredProperty("spring.datasource.username"));
            dataSource.setPassword(environment.getRequiredProperty("spring.datasource.password"));
        }
        return dataSource;
    }

}
