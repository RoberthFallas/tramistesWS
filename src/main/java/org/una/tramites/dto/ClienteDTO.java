/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tramites.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.*;

/**
 *
 * @author LordLalo
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class  ClienteDTO {

    private Long id;
    private String nombreCompleto;
    private String cedula;
    private String telefono;
    private String direccion;
    private boolean estado;
    private Date fechaRegistro;
    private Date fechaModificacion;


    @Setter(AccessLevel.NONE)
    private List<TramiteRegistradoDTO> tramitesRegistrados = new ArrayList<>();
}
