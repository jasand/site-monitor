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
//
//    @Bean
//    public DataSource dataSource(Environment environment) {
//        BasicDataSource dataSource = new BasicDataSource();
//        dataSource.setDriverClassName(environment.getRequiredProperty("db.driver"));
//        dataSource.setUrl(environment.getRequiredProperty("db.url"));
//        dataSource.setUsername(environment.getRequiredProperty("db.username"));
//        dataSource.setPassword(environment.getRequiredProperty("db.password"));
//        return dataSource;
//    }
//
//    @Bean
//    LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource, Environment env) {
//        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
//        entityManagerFactoryBean.setDataSource(dataSource);
//        entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
//        entityManagerFactoryBean.setPackagesToScan("no.sensor.db");
//
//        Properties jpaProperties = new Properties();
//        jpaProperties.put("hibernate.dialect", env.getRequiredProperty("hibernate.dialect"));
//        jpaProperties.put("hibernate.hbm2ddl.auto", env.getRequiredProperty("hibernate.hbm2ddl.auto"));
//        jpaProperties.put("hibernate.ejb.naming_strategy", env.getRequiredProperty("hibernate.ejb.naming_strategy"));
//        jpaProperties.put("hibernate.show_sql", env.getRequiredProperty("hibernate.show_sql"));
//        jpaProperties.put("hibernate.format_sql", env.getRequiredProperty("hibernate.format_sql"));
//        String currentSchema = env.getProperty("hibernate.default_schema");
//
//        if (currentSchema != null) {
//            jpaProperties.put("hibernate.default_schema", currentSchema);
//            log.info("hibernate.default_schema=" + currentSchema);
//        }
//
//        // Må visst ha Hibernate 4.1.4.Final for at denne skal virke skikkelig...
//        jpaProperties.put("javax.persistence.lock.timeout", 0);
//
//        entityManagerFactoryBean.setJpaProperties(jpaProperties);
//
//        return entityManagerFactoryBean;
//    }
//
//    @Bean
//    public PlatformTransactionManager transactionManager(LocalContainerEntityManagerFactoryBean entityManagerFactory) {
//        JpaTransactionManager transactionManager = new JpaTransactionManager();
//        transactionManager.setEntityManagerFactory(entityManagerFactory.getObject());
//        return transactionManager;
//    }
}
