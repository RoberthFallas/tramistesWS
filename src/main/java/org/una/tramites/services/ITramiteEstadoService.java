/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tramites.services;

import java.util.List;
import java.util.Optional;
import org.una.tramites.dto.TramiteEstadoDTO;
import org.una.tramites.entities.TramiteEstado;

/**
 *
 * @author LordLalo
 */

public interface ITramiteEstadoService {
//    public Optional<TramiteEstado> findById(Long id);
//
//    public Optional<List<TramiteEstado>> findAll();
//
//    public TramiteEstado create(TramiteEstado tramites);
//
//    public Optional<TramiteEstado> update(TramiteEstado tramites, Long id);
   public Optional<TramiteEstadoDTO> findById(Long id);

    public Optional<List<TramiteEstadoDTO>> findAll();

    public TramiteEstadoDTO create(TramiteEstadoDTO tramites);

    public Optional<TramiteEstadoDTO> update(TramiteEstadoDTO tramites, Long id);
    public void delete(Long id);

    public void deleteAll();
}
