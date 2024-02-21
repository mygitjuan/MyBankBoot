package com.dxc.mypersonalbankapi.controladores;

import com.dxc.mypersonalbankapi.modelos.clientes.Cliente;
import com.dxc.mypersonalbankapi.modelos.prestamos.Prestamo;
import com.dxc.mypersonalbankapi.exceptions.PrestamoException;
import com.dxc.mypersonalbankapi.persistencia.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Controller
public class PrestamosController {
    //private static IPrestamosRepo prestamosRepo = PrestamosInMemoryRepo.getInstance();
    //private static IClientesRepo clientesRepo = ClientesInMemoryRepo.getInstance();
    @Autowired
    private static IClientesRepo clientesRepo;
    @Autowired
    private static IPrestamosRepo prestamosRepo;

    @Autowired
    private static PrestamosRepositoryData prestamosRepositoryData;

    @Autowired
    private static ClienteRepositoryData clientesRepoData;

    @Transactional
    public static void mostrarLista(Integer uid) {
        System.out.println("\nLista de prestamos del cliente: " + uid);
        System.out.println("───────────────────────────────────");

        try {
            List<Prestamo> prestamos = prestamosRepositoryData.findAllById(Collections.singleton(uid));
            if (prestamos != null && prestamos.size() > 0) System.out.println(prestamos);
            else System.out.println("El cliente no tiene prestamos!");

        } catch (Exception e) {
            System.out.println("Oops ha habido un problema, inténtelo más tarde 😞!");
        }
    }

    public static void mostrarDetalle(Integer uid, Integer lid) {
        System.out.println("\nDetalle de prestamo: " + lid + ", del cliente: " + uid);
        System.out.println("───────────────────────────────────");

        try {
            Prestamo prestamo = prestamosRepo.getLoansByClientAndId(uid, lid);
            System.out.println(prestamo);
        } catch (PrestamoException e) {
            System.out.println("Prestamo NO encontrado para el cliente 😞! \nCode: " + e.getCode());
        } catch (Exception e) {
            System.out.println("Oops ha habido un problema, inténtelo más tarde 😞!");
        }
    }

    @Transactional
    public static void eliminar(Integer uid, Integer lid) {
        System.out.println("\nBorrando prestamo: " + lid + ", para cliente: " + uid);
        System.out.println("───────────────────────────────────");

        try {
            Prestamo pr = prestamosRepo.getLoansByClientAndId(uid, lid);

            prestamosRepositoryData.delete(pr);

            Cliente cl = clientesRepoData.getReferenceById(uid);
            cl.delisgarPrestamo(pr);
            System.out.println("Préstamo borrada 🙂!!");
            mostrarLista(uid);

        } catch (PrestamoException e) {
            System.out.println("Préstamo NO encontrado 😞! \nCode: " + e.getCode());
        } catch (Exception e) {
            System.out.println("Oops ha habido un problema, inténtelo más tarde 😞!");
        }

    }

    public static void add(Integer uid, String[] args) {
        System.out.println("uid: " + uid);
        for (String arg : args) {
            System.out.println(arg);
        }
        // TODO
    }

    public static void actualizar(Integer uid, Integer lid, String[] args) {
        System.out.println("uid: " + uid);
        System.out.println("lid: " + lid);
        for (String arg : args) {
            System.out.println(arg);
        }
        // TODO
    }
}
