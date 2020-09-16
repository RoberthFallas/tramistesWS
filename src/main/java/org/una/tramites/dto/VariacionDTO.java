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
public class VariacionDTO {

    private Long id;
    private String descripcion;
    private int grupo;
    private Date fechaRegistro;
    private boolean estado;
    @Setter(AccessLevel.NONE)
    private TramiteTipoDTO tramite_tipo;

    private List<RequisitoDTO> requisitoDTOs = new ArrayList<>();

    public void adjuntarTipoTramite(TramiteTipoDTO tipoTramite) {
        this.tramite_tipo = tipoTramite;
    }
}
