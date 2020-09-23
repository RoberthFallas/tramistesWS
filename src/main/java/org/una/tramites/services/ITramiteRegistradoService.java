package org.una.tramites.services;

import org.una.tramites.entities.TramiteEstado;
import org.una.tramites.entities.TramiteRegistrado;

import java.util.List;
import java.util.Optional;
import org.una.tramites.dto.TramiteRegistradoDTO;

public interface ITramiteRegistradoService {
//    public Optional<List<TramiteRegistrado>> findAll();
//
//    public Optional<TramiteRegistrado> findById(Long id);
//
//    public TramiteRegistrado create(TramiteRegistrado tramitesRegistrados);
//
//    public Optional<TramiteRegistrado> update(TramiteRegistrado tramitesRegistrados, Long id);

    public Optional<List<TramiteRegistradoDTO>> findAll();

    public Optional<TramiteRegistradoDTO> findById(Long id);

    public TramiteRegistradoDTO create(TramiteRegistradoDTO tramitesRegistradosDTO);

    public Optional<TramiteRegistradoDTO> update(TramiteRegistradoDTO tramitesRegistradosDTO, Long id);

    public void delete(Long id);

    public void deleteAll();

}
