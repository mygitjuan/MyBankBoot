package com.dxc.mypersonalbankapi.persistencia;

import com.dxc.mypersonalbankapi.modelos.clientes.Cliente;
import com.dxc.mypersonalbankapi.modelos.cuentas.Ahorro;
import com.dxc.mypersonalbankapi.modelos.cuentas.Corriente;
import com.dxc.mypersonalbankapi.modelos.cuentas.Cuenta;
import com.dxc.mypersonalbankapi.exceptions.CuentaException;
import com.dxc.mypersonalbankapi.exceptions.ErrorCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Repository
public class CuentasRepositoryJPA implements ICuentasRepo {
    private String db_url;

    @PersistenceContext
    private EntityManager em;

    private static CuentasInMemoryRepo instance;
    private static List<Cuenta> cuentas;
    private static IClientesRepo clientesRepo = ClientesInMemoryRepo.getInstance();


    @Override
    public List<Cuenta> getAll() throws RuntimeException{
        return em.createQuery("SELECT c FROM Cuenta c", Cuenta.class).getResultList();
    }

    @Override
    public Cuenta getAccountById(Integer id) throws Exception {
        return null;
    }

    @Override
    public Cuenta addAccount(Cuenta cuenta) throws Exception {
        return null;
    }

    @Override
    public boolean deleteAccount(Cuenta cuenta) throws Exception {
        return false;
    }

    @Override
    public Cuenta updateAccount(Cuenta cuenta) throws Exception {
        return null;
    }

    @Override
    public List<Cuenta> getAccountsByClient(Integer uid) throws Exception {
        return null;
    }

    @Override
    public Cuenta getAccountsByClientAndId(Integer uid, Integer aid) throws Exception {
        return null;
    }

    @Override
    public String getdb_url(String dbUrl) {
        return null;
    }
}
