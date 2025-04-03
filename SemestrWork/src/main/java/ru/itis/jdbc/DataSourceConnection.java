package ru.itis.jdbc;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import ru.itis.servises.SignUpService;
import ru.itis.servises.SignUpServiceImpl;
import ru.itis.repositories.UsersRepository;
import ru.itis.repositories.impl.UsersRepositoryJdbcImpl;

import java.io.IOException;
import java.util.Properties;

public class DataSourceConnection {
    public static void main(String[] args) {

    Properties properties = new Properties();
        try {
        properties.load(ClassLoader.getSystemResourceAsStream("application.properties"));
    } catch (IOException e) {
        throw new IllegalArgumentException(e);
    }

    HikariConfig config = new HikariConfig();
        config.setUsername(properties.getProperty("db.user"));
        config.setPassword(properties.getProperty("db.password")) ;
        config.setDriverClassName(properties.getProperty("db.driver")) ;
        config.setJdbcUrl(properties.getProperty("db.url")) ;
        config.setMaximumPoolSize(Integer.parseInt(properties.getProperty("db.hikari.max-pool-size")));
    HikariDataSource dataSource = new HikariDataSource(config);

    UsersRepository usersRepository = new UsersRepositoryJdbcImpl(dataSource);
    SignUpService signUpService = new SignUpServiceImpl(usersRepository);
    }
}
