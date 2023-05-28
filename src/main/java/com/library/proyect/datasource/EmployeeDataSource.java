package com.library.proyect.datasource;

import com.library.proyect.model.EmployeeEntity;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration("dataSourceEmployee")
@EnableJpaRepositories(basePackages = "com.library.proyect.repository.employee",
        entityManagerFactoryRef = "employeeEntityManagerFactory",
        transactionManagerRef = "employeeTransactionManager")
public class EmployeeDataSource {

    @Bean
    @ConfigurationProperties("spring.rrhh")
    public DataSourceProperties employeeDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @ConfigurationProperties("spring.rrhh.configuration")
    public DataSource employeeDataSource() {
        return employeeDataSourceProperties().initializeDataSourceBuilder()
                .type(HikariDataSource.class).build();
    }

    @Bean(name = "employeeEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean employeeEntityManagerFactory(
            EntityManagerFactoryBuilder builder
    ) {
        return builder
                .dataSource(employeeDataSource())
                .packages(EmployeeEntity.class)
                .build();
    }

    @Bean
    public PlatformTransactionManager employeeTransactionalManger(
            final @Qualifier("employeeEntityManagerFactory") LocalContainerEntityManagerFactoryBean employeeEntityManagerFactory
    ) {
        return new JpaTransactionManager(employeeEntityManagerFactory.getObject());
    }

}
