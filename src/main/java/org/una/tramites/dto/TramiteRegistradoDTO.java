/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tramites.dto;

import lombok.*;
import org.una.tramites.entities.Cliente;

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
    private String tramiteTipo;

    private ClienteDTO cliente;

    // public void asociarCliente(ClienteDTO cliente){
      //  this.cliente = cliente;
   // }
}
