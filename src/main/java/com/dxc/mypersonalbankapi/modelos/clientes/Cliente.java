package com.dxc.mypersonalbankapi.modelos.clientes;

import com.dxc.mypersonalbankapi.modelos.prestamos.Prestamo;
import com.dxc.mypersonalbankapi.modelos.cuentas.Cuenta;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.persistence.Entity;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

import javax.validation.constraints.*;


import org.springframework.format.annotation.DateTimeFormat;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "cliente")
@Schema(name = "cliente", description = "Modelo cliente")
public abstract class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    @Min(1)
    @Schema(name = "ClienteID", example = "1", required = false)
    private Integer id;
    @Column(name="nombre")
    @Size(min = 3, max = 20)
    @Schema(name = "Cliente name", example = "Juan Perez", required = true)
    private String nombre;
    @Column(name="email")
    @Size(min = 7, max = 30)
    @Schema(name = "Cliente email", example = "j@j.com", required = true)
    private String email;
    @Column(name="direccion")
    @Size(min = 3, max = 20)
    @Schema(name = "Cliente direcion", example = "Calle 123", required = true)
    private String direccion;
    @Column(name="alta")
    @DateTimeFormat
    @NotNull
    @Schema(name = "Cliente alta", example = "", required = true)
    private LocalDate alta;
    @Column(name="activo")
    @NotNull
    @Schema(name = "Cliente activo", example = "", required = true)
    private boolean activo;
    @Column(name="moroso")
    @NotNull
    @Schema(name = "Cliente moroso", example = "", required = true)
    private boolean moroso;

    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id")
    @JsonIgnore
    @Schema(name = "Cliente lista cuentas", example = "", required = true)
    private List<Cuenta> cuentas;
    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id")
    @JsonIgnore
    @Schema(name = "Cliente lista prestamos", example = "", required = true)
    private List<Prestamo> prestamos;


    /* CONSTRUCTOR */

    public Cliente(Integer id, String nombre, String email, String direccion, LocalDate alta, boolean activo, boolean moroso) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.direccion = direccion;
        this.alta = alta;
        this.activo = activo;
        this.moroso = moroso;

    }

    /* LOGICA IMPORTANTE */
    private boolean validarNombre() {
        return this.nombre != null && this.nombre.length() >= 3;
    }

    private boolean validarEmail() {
        return this.email != null && this.email.indexOf("@") > 0 && this.email.indexOf(".") > 0;
    }

    private boolean validarDireccion() {
        return this.direccion != null && this.direccion.length() >= 10;
    }

    private boolean validarAlta() {
        return this.alta != null && this.alta.compareTo(LocalDate.now()) <= 0;
    }

    protected boolean validarComun() {
        /*System.out.println("validarNombre():"+validarNombre());
        System.out.println("validarEmail():"+validarEmail());
        System.out.println("validarDireccion():"+validarDireccion() );
        System.out.println("validarAlta()"+validarAlta());*/
        return validarNombre() &&
                validarEmail() &&
                validarDireccion() &&
                validarAlta();
    }

    public abstract boolean validar() throws Exception;

    public void asociarCuenta(Cuenta cuenta) {
        if (this.cuentas == null) this.cuentas = new ArrayList<>();
        if (cuenta.validar()) this.cuentas.add(cuenta);
    }

    public void delisgarCuenta(Cuenta cuenta) {
        if (this.cuentas != null) {
            for (Cuenta aC : this.cuentas) {
                if (aC.getId().equals(cuenta.getId())) {
                    this.cuentas.remove(aC);
                    break;
                }
            }
        }
    }

    public void asociarPrestamo(Prestamo prestamo) {
        if (this.prestamos == null) this.prestamos = new ArrayList<>();
        if (prestamo.validar()) this.prestamos.add(prestamo);
    }

    public void delisgarPrestamo(Prestamo prestamo) {
        if (this.prestamos != null) {
            for (Prestamo aP : this.prestamos) {
                if (aP.getId().equals(prestamo.getId())) {
                    this.prestamos.remove(aP);
                    break;
                }
            }
        }
    }


    public double obtenerSaldoTotal() {
        if (this.getCuentas() != null && getCuentas().size() > 0) {
            double acumulador = 0;
            for (Cuenta cuenta : this.getCuentas()) {
                acumulador += cuenta.getSaldo();
            }
            return acumulador;
        } else return 0;
    }

    public boolean evaluarSolicitudPrestamo(Prestamo prestamo) {
        double monto = prestamo.getMonto();
        double saldoTotal = this.obtenerSaldoTotal();
        int numPrestamos = this.prestamos != null ? this.prestamos.size() : 0;
        double proporcionSaldoPrestamo = saldoTotal / monto;
        
        if (saldoTotal <= 0) return false;
        else if (numPrestamos == 0) {
            return proporcionSaldoPrestamo >= 0.3;
        } else if (numPrestamos == 1) {
            return proporcionSaldoPrestamo >= 0.5;
        } else if (numPrestamos == 2) {
            return proporcionSaldoPrestamo >= 0.7;
        } else return false;

    }

    /* GETTERS
    public Integer getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEmail() {
        return email;
    }

    public String getDireccion() {
        return direccion;
    }

    public LocalDate getAlta() {
        return alta;
    }

    public boolean isActivo() {
        return activo;
    }

    public boolean isMoroso() {
        return moroso;
    }

    public List<Cuenta> getCuentas() {
        return cuentas;
    }

    public List<Prestamo> getPrestamos() {
        return prestamos;
    }*/


    /* SETTERS

    public void setId(Integer id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setAlta(LocalDate alta) {
        this.alta = alta;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public void setMoroso(boolean moroso) {
        this.moroso = moroso;
    }*/

    /* TOSTRING */

    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", email='" + email + '\'' +
                ", direccion='" + direccion + '\'' +
                ", alta=" + alta +
                ", activo=" + activo +
                ", moroso=" + moroso +
                ", cuentas=" + cuentas +
                ", prestamos=" + prestamos +
                '}';
    }
}
