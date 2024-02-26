package com.dxc.mypersonalbankapi.modelos.clientes;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.time.LocalDate;
import java.util.Arrays;

import lombok.*;

import javax.persistence.Entity;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

import javax.validation.constraints.*;


import io.swagger.v3.oas.annotations.media.Schema;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Empresa extends Cliente {
    //@NotBlank
    //@Size(max = 9)
    @Schema(name = "Empresa CIF", example = "B31313133", required = true)
    private String cif;
    @Column(name="unidades_de_negocio")
    @JsonIgnore
    @Schema(name = "Empresa unidades de negocio", example = "", required = true)
    private String[] unidadesNegocio;

    public Empresa(Integer id, String nombre, String email, String direccion, LocalDate alta, boolean activo, boolean moroso, String cif, String[] unidadesNegocio) throws Exception{
        super(id, nombre, email, direccion, alta, activo, moroso);
        setCif(cif);
        this.unidadesNegocio = unidadesNegocio;
    }

    private boolean validarCIF(String cif) throws Exception{
        if (cif != null && cif.length() == 9) {
            String intPartCIF = cif.trim().replaceAll(" ", "").substring(1, 9);
            char ltrCIF = cif.charAt(0);
            int valNumCif = Integer.parseInt(intPartCIF);
            return valNumCif > 0 || !Character.isLetter(ltrCIF);
        } else return false;
    }

    @Override
    public boolean validar() throws Exception{
        return this.validarComun() && validarCIF(this.cif);
    }

    public String getCif() {
        return cif;
    }

    public void setCif(String cif) throws Exception{
        if(validarCIF(cif)) this.cif = cif;
    }

    public String[] getUnidadesNegocio() {
        return unidadesNegocio;
    }

    public void setUnidadesNegocio(String[] unidadesNegocio) {
        this.unidadesNegocio = unidadesNegocio;
    }

    @Override
    public String toString() {
        return "Empresa{" +
                "cif='" + cif + '\'' +
                ", unidadesNegocio=" + Arrays.toString(unidadesNegocio) +
                "} " + super.toString();
    }
}
