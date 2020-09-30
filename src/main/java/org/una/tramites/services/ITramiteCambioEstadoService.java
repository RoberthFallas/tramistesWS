package org.una.tramites.services;

import java.util.List;
import java.util.Optional;
import org.una.tramites.dto.TramiteCambioEstadoDTO;
import org.una.tramites.entities.TramiteCambioEstado;

/**
 *
 * @author LordLalo
 */
public interface ITramiteCambioEstadoService {

    public TramiteCambioEstadoDTO create(TramiteCambioEstadoDTO nota);

    public Optional<TramiteCambioEstadoDTO> update(TramiteCambioEstadoDTO tramiteCambioEstadoDTO, Long id);

    public Optional<List<TramiteCambioEstadoDTO>> findAll();

    public Optional<TramiteCambioEstadoDTO> findById(Long id);

    public void delete(Long id);

    public void deleteAll();

}
