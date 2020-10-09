package org.una.tramites.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.una.tramites.dto.NotaDTO;

/**
 *
 * @author LordLalo
 */
public interface INotaService {


    public Optional<List<NotaDTO>> findAll();

    public Optional<NotaDTO> findById(Long id);

    public Optional<List<NotaDTO>>findByTipo(boolean tipo);

    public NotaDTO create(NotaDTO notaDTO);

    public Optional<NotaDTO> update(NotaDTO notaDTO, Long id);

    public Optional<List<NotaDTO>> findByFechaRegistroBetween(Date startDate, Date endDate);

    public void delete(Long id);

    public void deleteAll();

}
