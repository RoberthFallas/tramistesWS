package org.una.tramites.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.una.tramites.entities.Requisito;

/**
 *
 * @author LordLalo
 */

public interface IRequisitoService {

    public Optional<List<Requisito>> findAll();

    public Optional<Requisito> findById(Long id);

    public Optional<Requisito> findByDescripcion(String descripcion);

    public Optional<Requisito> findByFechaRegistroBetween(Date startDate, Date endDate);

    public void delete(Long id);

    public void deleteAll();

}