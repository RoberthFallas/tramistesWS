/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tramites.dto;

import lombok.*;
import org.una.tramites.entities.Cliente;
import org.una.tramites.entities.TramiteTipo;

import java.util.List;

/**
 *
 * @author LordLalo
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TramiteRegistradoDTO {

    private Long id;
    private TramiteTipoDTO tramiteTipo;
    private ClienteDTO cliente;
    private List<TramiteCambioEstadoDTO> tramiteCambioEstados;
    private List<NotaDTO> notas;



    // public void asociarCliente(ClienteDTO cliente){
      //  this.cliente = cliente;
   // }
}
