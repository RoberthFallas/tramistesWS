/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tramites.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author rober
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PermisoOtorgadoDTO {

    private Long id;
    private Date fechaRegistro;
    private boolean estado;
    @Setter(AccessLevel.NONE)
    private UsuarioDTO usuario;
    //@Setter(AccessLevel.NONE)
    private PermisoDTO permiso;
    @Setter(AccessLevel.NONE)
    private List<TransaccionDTO> transacciones = new ArrayList<>();
    
    public void setAgregarUsuario(UsuarioDTO usuarioDTO){
     usuario=usuarioDTO;
    }
    public void setAgregarPermiso(PermisoDTO permisoDTO){
     permiso=permisoDTO;
    }
}
