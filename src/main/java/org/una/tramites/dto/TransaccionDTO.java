/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tramites.dto;

import java.util.Date;
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
public class TransaccionDTO {

    private Long id;

    private Date fechaRegistro;
    private String objeto;
    private String informacion;
    @Setter(AccessLevel.NONE)
    private PermisoOtorgadoDTO permisoOtorgado;
}
