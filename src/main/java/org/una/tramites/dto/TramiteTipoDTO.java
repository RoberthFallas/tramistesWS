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
 * @author LordLalo
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TramiteTipoDTO {

    private Long id;
    private String descripcion;
    private Date fechaRegistro;
    private Date fechaModificacion;
    private boolean estado;
    @Setter(AccessLevel.NONE)
    private DepartamentoDTO departamento;
    @Setter(AccessLevel.NONE)
    private List<VariacionDTO> variacionDTOs = new ArrayList<>();
    
    
    public void adjuntarDepartamento(DepartamentoDTO depa){
        this.departamento = depa;
    }
}
