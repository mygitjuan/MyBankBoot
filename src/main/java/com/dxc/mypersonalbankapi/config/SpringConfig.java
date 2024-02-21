package com.dxc.mypersonalbankapi.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@Configuration
@PropertySource("classpath:application.properties")
@ComponentScan(basePackages = {"com.dxc.mypersonalbankapi.persistencia", "com.dxc.mypersonalbankapi.controladores"})
@EntityScan("com.dxc.mypersonalbankapi.modelos")
@EnableJpaRepositories("com.dxc.mypersonalbankapi.persistencia")
public class SpringConfig {

}
