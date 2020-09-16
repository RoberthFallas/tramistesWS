/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tramites.services;

import java.util.List;
import java.util.Optional;
import org.una.tramites.dto.TramiteTipoDTO;

/**
 *
 * @author Roberth :)
 */
public interface ITipoTramiteService {

    public Optional<TramiteTipoDTO> create(TramiteTipoDTO tipoTramite);

    public Optional<TramiteTipoDTO> update(TramiteTipoDTO tipoTramite);

    public Optional<List<TramiteTipoDTO>> getByEstado(boolean estado);

    public Optional<List<TramiteTipoDTO>> getByDescripcion(String descripcion);

    public Optional<List<TramiteTipoDTO>> getAll();
}
