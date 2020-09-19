/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tramites.dto;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.una.tramites.entities.TramiteEstado;
import org.una.tramites.entities.TramiteRegistrado;
import org.una.tramites.entities.Usuario;

/**
 *
 * @author LordLalo
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TramiteCambioEstadoDTO {

    private Long id;
    private Usuario usuario;
    private TramiteRegistrado tramiteRegistrado;
    private TramiteEstado tramiteEstado;
    private Date fechaRegistro;
    //private List<UsuarioDTO> usuarios = new ArrayList<>();
    //private List<TramiteRegistradoDTO> tramitesRegistrados = new ArrayList<>();
    //private List<TramiteEstadoDTO> tramitesEstado = new ArrayList<>();
}
