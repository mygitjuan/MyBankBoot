package com.dxc.mypersonalbankapi.modelos.cuentas;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Transient;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Corriente extends Cuenta {
    @Transient
    private List<Cheque> chequesIngresados;
    @Transient
    private List<Cheque> chequesEmitidos;

    public Corriente(Integer id, LocalDate fechaCreacion, Double saldo, Double interes, Double comision) {
        super(id, fechaCreacion, saldo, interes, comision);
    }

    @Override
    public boolean validar() {
        return this.validarComun();
    }

    public void emitirCheque(Cheque cheque) {
        if (this.chequesEmitidos == null) this.chequesEmitidos = new ArrayList<>();
        this.chequesEmitidos.add(cheque);
        this.actualizarSaldo(cheque.getImporte());
    }

    public void ingresarCheque(Cheque cheque) {
        if (this.chequesIngresados == null) this.chequesEmitidos = new ArrayList<>();
        this.chequesEmitidos.add(cheque);
        this.actualizarSaldo(cheque.getImporte() * -1);
    }

    public List<Cheque> getChequesIngresados() {
        return chequesIngresados;
    }

    public List<Cheque> getChequesEmitidos() {
        return chequesEmitidos;
    }

    @Override
    public String toString() {
        return "Corriente{" +
                "chequesIngresados=" + chequesIngresados +
                ", chequesEmitidos=" + chequesEmitidos +
                "} " + super.toString();
    }
}
