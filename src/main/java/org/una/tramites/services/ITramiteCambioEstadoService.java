package org.una.tramites.services;

import java.util.List;
import java.util.Optional;
import org.una.tramites.dto.TramiteCambioEstadoDTO;

/**
 *
 * @author LordLalo
 */
public interface ITramiteCambioEstadoService {

    public TramiteCambioEstadoDTO create(TramiteCambioEstadoDTO nota);

    public Optional<TramiteCambioEstadoDTO> update(TramiteCambioEstadoDTO tramiteCambioEstadoDTO, Long id);

    public Optional<List<TramiteCambioEstadoDTO>> findAll();

    public Optional<TramiteCambioEstadoDTO> findById(Long id);

    public Optional<TramiteCambioEstadoDTO> modificarEstado(Long idTramite, Long idTramiteEstado);

    public Optional<TramiteCambioEstadoDTO> actualizarTramiteNuevo(Long idTramite, Long idTramiteEstado);

    public void delete(Long id);

    public void deleteAll();

}
