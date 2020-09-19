/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tramites.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.una.tramites.entities.Cliente;

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
    private Cliente cliente;
}
