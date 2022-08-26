package com.rocktech.config;

import com.rocktech.model.employee.Employee;
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
import java.util.Objects;


@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = "com.rocktech.repository.employee",
        entityManagerFactoryRef = "employeeEntityManager",
        transactionManagerRef = "employeeTransactionManager"
)
public class EmployeeConfig {

    @Bean
    @Primary
    @ConfigurationProperties("spring.datasource")
    public DataSourceProperties employeeDataSourceProperties() {
        return new DataSourceProperties();
    }


    @Bean
    @Primary
    public DataSource employeeDataSource() {
        return employeeDataSourceProperties()
                .initializeDataSourceBuilder()
                .type(HikariDataSource.class)
                .build();
    }


    @Bean("employeeEntityManager")
    @Primary
    public LocalContainerEntityManagerFactoryBean employeeEntityManager(
            EntityManagerFactoryBuilder builder
    ) {
        return builder.dataSource(employeeDataSource())
                .packages(Employee.class)
                .build();
    }


    @Bean("employeeTransactionManager")
    @Primary
    public PlatformTransactionManager employeeTransactionManager(
           @Qualifier("employeeEntityManager") LocalContainerEntityManagerFactoryBean managerFactoryBean
    ) {
        return new JpaTransactionManager(Objects.requireNonNull(managerFactoryBean.getObject()));
    }
}
