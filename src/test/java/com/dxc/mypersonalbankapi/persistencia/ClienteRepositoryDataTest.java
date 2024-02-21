package com.dxc.mypersonalbankapi.persistencia;


import com.dxc.mypersonalbankapi.config.SpringConfig;
import com.dxc.mypersonalbankapi.modelos.clientes.Cliente;
import com.dxc.mypersonalbankapi.modelos.clientes.Empresa;
import com.dxc.mypersonalbankapi.modelos.clientes.Personal;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {SpringConfig.class})
@TestMethodOrder( MethodOrderer.OrderAnnotation.class)
@EnableAutoConfiguration
class ClienteRepositoryDataTest {

    @Autowired
    private ClienteRepositoryData clientesRepo;

    @Test
    void addClient_personal() throws Exception{
        Cliente newCliente = new Personal(null,"Mario Bros","mariobData@m.com","Calle Nintendo", LocalDate.now(),true,false, "96845713G" );
        clientesRepo.save(newCliente);
        assertThat(newCliente, notNullValue());
        assertThat(newCliente.getId(), greaterThan(0));
    }

    @Test
    void addClient_empresa() throws Exception{

        String[] myCadena = {"Dev","Markey"};

        Cliente newCliente = new Empresa(null, "Servicios Mario Bros SL Data", "siData@s.com", "Calle SI 3", LocalDate.now(), true, false, "J12345678", myCadena);
        clientesRepo.save(newCliente);
        assertThat(newCliente, notNullValue());
        assertThat(newCliente.getId(), greaterThan(0));
    }

    @Test
    @Transactional
    void getAll() throws Exception {
        List<Cliente> clientes = clientesRepo.findAll();
        System.out.println(clientes);

        assertNotNull(clientes);
        assertTrue(clientes.size() > 0);
    }

 /*   @Test
    void deleteClient() {

    }
*/


    @Test
    @Transactional
    void getClientById() throws Exception{
        Cliente aCliente = clientesRepo.getReferenceById(14);
        System.out.println(aCliente);
        assertEquals(aCliente.getId(), 14);
        assertNotNull(aCliente);
    }
/*
    @Test
    void updateClient() {
    }*/


}