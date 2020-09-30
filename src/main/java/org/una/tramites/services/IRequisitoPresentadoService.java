package org.una.tramites.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.una.tramites.dto.RequisitoPresentadoDTO;

/**
 *
 * @author LordLalo
 */
public interface IRequisitoPresentadoService {

    public Optional<List<RequisitoPresentadoDTO>> findAll();

    public Optional<RequisitoPresentadoDTO> findById(Long id);

    public Optional<List<RequisitoPresentadoDTO>> findByFechaRegistroBetween(Date startDate, Date endDate);

    public RequisitoPresentadoDTO create(RequisitoPresentadoDTO requisitoPresentadoDTO);

    public Optional<RequisitoPresentadoDTO> update(RequisitoPresentadoDTO requisitoPresentadoDTO, Long id);

    public void delete(Long id);

    public void deleteAll();

}
