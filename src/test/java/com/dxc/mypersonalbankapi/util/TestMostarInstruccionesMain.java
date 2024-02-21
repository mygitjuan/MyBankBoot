package com.dxc.mypersonalbankapi.util;

public class TestMostarInstruccionesMain {
    public static void mostrarInstrucciones() {
        System.out.println("⚠ LAS OPCIONES DEL PROGRAMA SON LAS SIGUIENTES: ⚠");

        System.out.println("\nCLIENTES:");
        System.out.println("clients list");
        System.out.println("clients {uid}");
        System.out.println("clients add {personal/empresa} {nombre} {email} {direccion} {fechaAlta (yyyy-mm-dd)} {dni/nif} {unidadesNegocio (opcional)}");
        System.out.println("clients remove {uid}");
        System.out.println("clients update {uid} {nombre} {email} {direccion} {fechaAlta (yyyy-mm-dd)} {activo} {moroso} {dni/nif} {unidadesNegocio (opcional)}");

        System.out.println("\nCUENTAS:");
        System.out.println("clients {uid} accounts");
        System.out.println("clients {uid} accounts {aid}");
        System.out.println("clients {uid} accounts add {ahorro/corriente} {fechaCreacion} {saldo} {interes} {comision}");
        System.out.println("clients {uid} accounts remove {aid}");
        System.out.println("clients {uid} accounts update {aid} {fechaCreacion} {saldo} {interes} {comision}");

        System.out.println("\nPRÉSTAMOS:");
        System.out.println("clients {uid} loans");
        System.out.println("clients {uid} loans {lid}");
        System.out.println("clients {uid} loans add {fechaConcesion (yyyy-mm-dd)} {monto} {interes} {interesMora}");
        System.out.println("clients {uid} loans remove {lid}");
        System.out.println("clients {uid} loans update {lid} {fechaConcesion (yyyy-mm-dd)} {monto} {interes} {interesMora}");

        System.out.println("\nEVALUAR PRÉSTAMO:");
        System.out.println("clients {uid} loan-evaluation {cantidad > 0}");
    }
}
