package com.library.proyect.datasource;

import com.library.proyect.model.BookEntity;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration("dataSourceLibrary")
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.library.proyect.repository.book",
        entityManagerFactoryRef = "libraryEntityManagerFactory",
        transactionManagerRef = "libraryTransactionManager")
public class LibraryDataSource {

    @Bean
    @Primary
    @ConfigurationProperties("spring.library")
    public DataSourceProperties libraryDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @Primary
    @ConfigurationProperties("spring.library.configuration")
    public DataSource libraryDataSource() {
        return libraryDataSourceProperties().initializeDataSourceBuilder()
                .type(HikariDataSource.class).build();
    }

    @Primary
    @Bean(name = "libraryEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean libraryEntityManagerFactory(EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(libraryDataSource())
                .packages(BookEntity.class)
                .build();
    }

    @Primary
    @Bean
    public PlatformTransactionManager libraryTransactionManager(
            final @Qualifier("libraryEntityManagerFactory") LocalContainerEntityManagerFactoryBean libraryEntityManagerFactory) {
        return new JpaTransactionManager(libraryEntityManagerFactory.getObject());
    }
}
