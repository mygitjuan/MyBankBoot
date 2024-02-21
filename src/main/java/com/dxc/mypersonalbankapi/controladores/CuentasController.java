package com.dxc.mypersonalbankapi.controladores;

import com.dxc.mypersonalbankapi.modelos.clientes.Cliente;
import com.dxc.mypersonalbankapi.modelos.cuentas.Cuenta;
import com.dxc.mypersonalbankapi.exceptions.CuentaException;
import com.dxc.mypersonalbankapi.persistencia.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Controller
public class CuentasController {
    //private static ICuentasRepo cuentasRepo = CuentasInMemoryRepo.getInstance();
    //private static IClientesRepo clientesRepo = ClientesInMemoryRepo.getInstance();

    @Autowired
    private static IClientesRepo clientesRepo;

    @Autowired
    private static ClienteRepositoryData clientesRepoData;

    @Autowired
    private static ICuentasRepo cuentasRepo;

    @Autowired
    private static CuentasRepositoryData cuentasRepositoryData;

    @Transactional
    public static void mostrarLista(Integer uid) {
        System.out.println("\nLista de cuentas del cliente: " + uid);
        System.out.println("───────────────────────────────────");

        try {
            List<Cuenta> cuentas = cuentasRepositoryData.findAllById(Collections.singleton(uid));

            if (cuentas != null && cuentas.size() > 0) System.out.println(cuentas);
            else System.out.println("El cliente no tiene cuentas!");
        } catch (Exception e) {
            System.out.println("Cliente NO encontrado 😞!");
        }
    }

    @Transactional
    public static void mostrarDetalle(Integer uid, Integer aid) {
        System.out.println("\nDetalle de cuenta: " + aid + ", del cliente: " + uid);
        System.out.println("───────────────────────────────────");

        try {
            Cuenta cuenta = cuentasRepo.getAccountsByClientAndId(uid, aid);
            System.out.println(cuenta);
        } catch (Exception e) {
            System.out.println("Cuenta NO encontrada para el cliente 😞!");
        }
    }

    @Transactional
    public static void eliminar(Integer uid, Integer aid) {
        System.out.println("\nBorrando cuenta: " + aid + ", para cliente: " + uid);
        System.out.println("───────────────────────────────────");

        try {
            Cuenta cu = cuentasRepo.getAccountsByClientAndId(uid, aid);
            cuentasRepositoryData.delete(cu);

            Cliente cl = clientesRepoData.getReferenceById(uid);
            cl.delisgarCuenta(cu);
            System.out.println("Cuenta borrada 🙂!!");
            mostrarLista(uid);

        } catch (CuentaException e) {
            System.out.println("Cuenta NO encontrado 😞! \nCode: " + e.getCode());
        } catch (Exception e) {
            System.out.println("Oops ha habido un problema, inténtelo más tarde 😞!");
        }

    }


    public static void add(Integer uid, String[] args) {
        System.out.println("uid: " + uid);
        for (String arg : args) {
            System.out.println(arg);
        }
    }


    public static void actualizar(Integer uid, Integer aid, String[] args) {
        System.out.println("uid: " + uid);
        System.out.println("aid: " + aid);
        for (String arg : args) {
            System.out.println(arg);
        }
    }
}
