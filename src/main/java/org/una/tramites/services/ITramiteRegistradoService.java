package org.una.tramites.services;

import org.una.tramites.entities.TramiteEstado;
import org.una.tramites.entities.TramiteRegistrado;

import java.util.List;
import java.util.Optional;

public interface ITramiteRegistradoService {
    public Optional<List<TramiteRegistrado>> findAll();

    public Optional<TramiteRegistrado> findById(Long id);

    public TramiteRegistrado create(TramiteRegistrado tramitesRegistrados);

    public Optional<TramiteRegistrado> update(TramiteRegistrado tramitesRegistrados, Long id);

    public void delete(Long id);

    public void deleteAll();

}
