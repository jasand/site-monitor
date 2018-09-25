package no.sensor.db.configuration;

import org.apache.commons.dbcp.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.Properties;

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
        if (System.getenv("RDS_HOSTNAME") != null) {
            String dbName = System.getenv("RDS_DB_NAME");
            String userName = System.getenv("RDS_USERNAME");
            String password = System.getenv("RDS_PASSWORD");
            String hostname = System.getenv("RDS_HOSTNAME");
            String port = System.getenv("RDS_PORT");
            String jdbcUrl = "jdbc:postgresql://" + hostname + ":" + port + "/" + dbName;
            dataSource.setDriverClassName(environment.getRequiredProperty("db.driver.classname"));
            dataSource.setUrl(jdbcUrl);
            dataSource.setUsername(userName);
            dataSource.setPassword(password);
        } else {
            dataSource.setDriverClassName(environment.getRequiredProperty("db.driver.classname"));
            dataSource.setUrl(environment.getRequiredProperty("spring.datasource.url"));
            dataSource.setUsername(environment.getRequiredProperty("spring.datasource.username"));
            dataSource.setPassword(environment.getRequiredProperty("spring.datasource.password"));
        }
        return dataSource;
    }

}
