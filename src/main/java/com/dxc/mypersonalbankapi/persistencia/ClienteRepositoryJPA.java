package com.dxc.mypersonalbankapi.persistencia;

import com.dxc.mypersonalbankapi.modelos.clientes.Cliente;
import com.dxc.mypersonalbankapi.modelos.clientes.Empresa;
import com.dxc.mypersonalbankapi.modelos.clientes.Personal;
import com.dxc.mypersonalbankapi.properties.PropertyValues;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.lang.reflect.Type;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Repository


public class ClienteRepositoryJPA implements IClientesRepo {


    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public Cliente addClient(Cliente cliente) throws Exception {
        em.persist(cliente);
        return cliente;
    }

    @Override
    public List<Cliente> getAll() throws RuntimeException {
        return em.createQuery("SELECT c FROM Cliente c WHERE c.id > 0", Cliente.class).getResultList();
    }

    @Override
    public Cliente getClientById(Integer id) throws Exception {
        return em.find(Cliente.class, id);
    }



    @Override
    public boolean deleteClient(Cliente cliente) throws Exception {
        return false;
    }

    @Override
    public Cliente updateClient(Cliente cliente) throws Exception {
        return null;
    }

    @Override
    public String getdb_url() {
        return null;
    }
}