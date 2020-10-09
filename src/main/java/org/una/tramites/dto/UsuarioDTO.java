/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tramites.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.Date;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.una.tramites.entities.PermisoOtorgado;

/**
 *
 * @author Roberth
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class UsuarioDTO {

    private Long id;
    private String nombreCompleto;
    private String cedula;
    private boolean estado;
    private Date fechaRegistro;
    private Date fechaModificacion;
    private boolean esJefe;
    private String passwordEncriptado;
    @Setter(AccessLevel.NONE)
    private DepartamentoDTO departamento;
    //@Setter(AccessLevel.NONE)
    private List<PermisoOtorgadoDTO> permisosOtorgado;
    
    
    public void asociarDepartamento(DepartamentoDTO depart){
        this.departamento = depart;
    }
   
}
