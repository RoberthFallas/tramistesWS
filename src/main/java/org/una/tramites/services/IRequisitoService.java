package org.una.tramites.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.una.tramites.dto.RequisitoDTO;


/**
 *
 * @author LordLalo
 */

public interface IRequisitoService {

     public Optional<List<RequisitoDTO>> findAll();

    public Optional<RequisitoDTO> findById(Long id);

    public Optional<List<RequisitoDTO>> findByDescripcion(String descripcion);

    public Optional<List<RequisitoDTO>> findByFechaRegistroBetween(Date startDate, Date endDate);

    public void delete(Long id);

    public void deleteAll();

}