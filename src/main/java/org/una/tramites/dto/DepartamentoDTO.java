/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tramites.dto;

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
 * @author roberth
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DepartamentoDTO {

    private Long id;
    private String nombre;
    private Date fechaRegistro;
    private Date fechaModificacion;
    private boolean estado;
    @Setter(AccessLevel.NONE)
    private List<UsuarioDTO> usuarios;
    
    public void adherirUsuarios(List<UsuarioDTO> usuarios){
        this.usuarios.addAll(usuarios);
    }
}
