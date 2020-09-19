package org.una.tramites.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.una.tramites.entities.Nota;

/**
 *
 * @author LordLalo
 */
public interface INotaService {

    public Optional<List<Nota>> findAll();

    public Optional<Nota> findById(Long id);

    public Optional<Nota> findByTipo(boolean tipo);

    public Nota create(Nota nota);

    public Optional<Nota> update(Nota nota, Long id);

    public Optional<Nota> findByFechaRegistroBetween(Date startDate, Date endDate);

    public void delete(Long id);

    public void deleteAll();

}
