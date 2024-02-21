package com.dxc.mypersonalbankapi.modelos.prestamos;

import com.dxc.mypersonalbankapi.modelos.clientes.Cliente;
import lombok.*;

import javax.persistence.Entity;
import java.time.LocalDate;
import java.util.List;
import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "prestamo")
public class Prestamo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;
    @Column(name="fecha_concesion")
    private LocalDate fechaConcesion;
    @Column(name="monto")
    private Double monto;
    @Column(name="saldo")
    private Double saldo;
    @Column(name="mensualidad")
    private Double mensualidad;
    @Column(name="anios")
    private Integer anios;
    @Transient
    private List<Pago> pagos;
    @Transient
    private List<Mora> moras;
    @Column(name="interes")
    private Integer interes;
    @Column(name="interes_mora")
    private Integer interesMora;
    @Column(name="moroso")
    private boolean moroso;
    @Column(name="liquidado")
    private boolean liquidado;

    @ManyToOne(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinColumn(name = "cliente_id")
    @ToString.Exclude
    private Cliente cliente;

    /* CONSTRUCTOR */
    public Prestamo(Integer id, LocalDate fechaConcesion, Double monto, Double saldo, Integer interes, Integer interesMora, boolean moroso, boolean liquidado, Integer anios) {
        this.id = id;
        this.fechaConcesion = fechaConcesion;
        this.monto = monto;
        this.saldo = saldo;
        this.interes = interes;
        this.interesMora = interesMora;
        this.moroso = moroso;
        this.liquidado = liquidado;
        this.anios = anios;
    }

    /* LOGICA IMPORTANTE */
    public boolean validar() {
        if (this.fechaConcesion.isAfter(LocalDate.now())) return false;
        else if (this.monto <= 0) return false;
        else if (this.saldo < 0) return false;
        else if (this.interesMora < 0) return false;
        else if (anios < 1) return false;
        else return true;
    }

    private void actualizarSaldo(Double monto) {
        this.saldo += monto;
    }

    public void calcularMensualidad(Double mensualidad) {
        this.mensualidad = this.monto * interes / (anios * 12);
    }

    public void pagarMensualidad() {
        if (!this.liquidado) {
            this.saldo -= this.mensualidad;
            if (this.saldo <= 0) this.liquidado = true;
        }
    }

    public void aplicarMora() {
        if (!this.liquidado) {
            this.saldo += this.saldo * interesMora;
            this.moroso = true;
        }
    }

    /* GETTERS */
    public Integer getId() {
        return id;
    }

    public LocalDate getFechaConcesion() {
        return fechaConcesion;
    }

    public Double getMonto() {
        return monto;
    }

    public Double getSaldo() {
        return saldo;
    }

    public List<Pago> getPagos() {
        return pagos;
    }

    public List<Mora> getMoras() {
        return moras;
    }

    public Integer getInteres() {
        return interes;
    }

    public Integer getInteresMora() {
        return interesMora;
    }

    public boolean isMoroso() {
        return moroso;
    }

    public boolean isLiquidado() {
        return liquidado;
    }

    public Double getMensualidad() {
        return mensualidad;
    }

    public Integer getAnios() {
        return anios;
    }

    /* SETTERS */
    public void setId(Integer id) {
        this.id = id;
    }

    public void setFechaConcesion(LocalDate fechaConcesion) {
        this.fechaConcesion = fechaConcesion;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public void setInteres(Integer interes) {
        this.interes = interes;
    }

    public void setInteresMora(Integer interesMora) {
        this.interesMora = interesMora;
    }

    public void setAnios(Integer anios) {
        this.anios = anios;
    }

    /* TOSTRING */
    @Override
    public String toString() {
        return "Prestamo{" +
                "id=" + id +
                ", fechaConcesion=" + fechaConcesion +
                ", monto=" + monto +
                ", saldo=" + saldo +
                ", pagos=" + pagos +
                ", moras=" + moras +
                ", interes=" + interes +
                ", interesMora=" + interesMora +
                ", moroso=" + moroso +
                ", liquidado=" + liquidado +
                '}';
    }
}
