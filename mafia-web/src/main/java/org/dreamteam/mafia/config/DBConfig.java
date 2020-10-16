package org.dreamteam.mafia.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

/**
 * Класс для настроеек параметров подключений к БД
 */
@Configuration
@EnableJpaRepositories("org.dreamteam.mafia.repository.api")
public class DBConfig {

    /**
     * Bean для получения источника данных, подключенного к БД
     *
     * @return - настроенный источник данных
     */
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUsername("postgres");
        dataSource.setPassword("postgres");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/mafia");
        return dataSource;
    }
}
