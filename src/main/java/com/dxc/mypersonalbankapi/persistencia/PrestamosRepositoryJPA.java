package com.dxc.mypersonalbankapi.persistencia;

import com.dxc.mypersonalbankapi.exceptions.PrestamoException;
import com.dxc.mypersonalbankapi.modelos.clientes.Cliente;
import com.dxc.mypersonalbankapi.modelos.prestamos.Prestamo;
import com.dxc.mypersonalbankapi.exceptions.ErrorCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Transient;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@Repository
public class PrestamosRepositoryJPA implements IPrestamosRepo {
    private String db_url;

    @PersistenceContext
    private EntityManager em;

    private static PrestamosInMemoryRepo instance;
    private static List<Prestamo> prestamos;
    private static IClientesRepo clientesRepo = ClientesInMemoryRepo.getInstance();


    @Override
    public List<Prestamo> getAll() throws RuntimeException{
        return em.createQuery("SELECT p FROM Prestamo p", Prestamo.class).getResultList();
    }

    @Override
    public Prestamo getLoanById(Integer id) throws Exception {
        return null;
    }

    @Override
    public Prestamo addLoan(Prestamo prestamo) throws Exception {
        return null;
    }

    @Override
    public boolean deleteLoan(Prestamo prestamo) throws Exception {
        return false;
    }

    @Override
    public Prestamo updateLoan(Prestamo prestamo) throws Exception {
        return null;
    }

    @Override
    public List<Prestamo> getLoansByClient(Integer uid) throws Exception {
        return null;
    }

    @Override
    public Prestamo getLoansByClientAndId(Integer uid, Integer aid) throws Exception {
        return null;
    }

    @Override
    public String getdb_url(String dbUrl) {
        return null;
    }
}
