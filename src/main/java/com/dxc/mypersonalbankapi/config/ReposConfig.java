package com.dxc.mypersonalbankapi.config;

import com.dxc.mypersonalbankapi.persistencia.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;

@Configuration
public class ReposConfig {

    @Autowired
    Environment env;

    @Value("${db_url}")
    String dbUrl;


    @Bean
    public IClientesRepo getClientById () throws Exception {
        System.out.println(dbUrl);
        ClienteDBRepository repo = new ClienteDBRepository();
        repo.setDb_url(dbUrl);
        return repo;
    }

    @Bean
    public ICuentasRepo  getAccountById() throws Exception {
        CuentasInMemoryRepo repo = new CuentasInMemoryRepo();
        repo.setDb_url(dbUrl);
        return repo;
    }

    @Bean
    public IPrestamosRepo  getLoanById() throws Exception {
        PrestamosInMemoryRepo repo = new PrestamosInMemoryRepo();
        repo.setDb_url(dbUrl);
        return repo;
    }


}
