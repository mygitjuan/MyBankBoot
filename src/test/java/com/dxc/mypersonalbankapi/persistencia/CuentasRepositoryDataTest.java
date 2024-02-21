package com.dxc.mypersonalbankapi.persistencia;

import com.dxc.mypersonalbankapi.config.SpringConfig;
import com.dxc.mypersonalbankapi.modelos.cuentas.Cuenta;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {SpringConfig.class})
@TestMethodOrder( MethodOrderer.OrderAnnotation.class)
@EnableAutoConfiguration
class CuentasRepositoryDataTest {

    @Autowired
    private CuentasRepositoryData cuentasRepo;

    @Test
    void getAll() {
        List<Cuenta> cuentas = cuentasRepo.findAll();
        System.out.println(cuentas);

        assertNotNull(cuentas);
        assertTrue(cuentas.size() > 0);

    }
/*
    @Test
    void getAccountById() {
    }

    @Test
    void addAccount() {
    }

    @Test
    void deleteAccount() {
    }

    @Test
    void updateAccount() {
    }

    @Test
    void getAccountsByClient() {
    }

    @Test
    void getAccountsByClientAndId() {
    }*/
}