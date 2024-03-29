package com.github.supercoding.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class JdbcConfig {

    @Bean
    public DataSource dataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUsername("root");
        dataSource.setPassword("12341234");
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver"); //이 스트링 값 넣어줘야 MySQL 드라이버 등록됨
        dataSource.setUrl("jdbc:mysql://localhost:3306/chapter_95");
        return dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate(){return new JdbcTemplate(dataSource());};


}
