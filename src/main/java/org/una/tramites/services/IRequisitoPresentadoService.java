package org.una.tramites.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.una.tramites.dto.RequisitoPresentadoDTO;
import org.una.tramites.entities.RequisitoPresentado;

/**
 *
 * @author LordLalo
 */
public interface IRequisitoPresentadoService {

//    public Optional<List<RequisitoPresentado>> findAll();
//
//    public Optional<RequisitoPresentado> findById(Long id);
//
//    public Optional<RequisitoPresentado> findByFechaRegistroBetween(Date startDate, Date endDate);
//
//    public RequisitoPresentado create(RequisitoPresentado requisitoPresentado);
//
//    public Optional<RequisitoPresentado> update(RequisitoPresentado requisitoPresentado, Long id);
        public Optional<List<RequisitoPresentadoDTO>> findAll();

    public Optional<RequisitoPresentadoDTO> findById(Long id);

    public Optional<List<RequisitoPresentadoDTO>> findByFechaRegistroBetween(Date startDate, Date endDate);

    public RequisitoPresentadoDTO create(RequisitoPresentadoDTO requisitoPresentadoDTO);

    public Optional<RequisitoPresentadoDTO> update(RequisitoPresentadoDTO requisitoPresentadoDTO, Long id);

    public void delete(Long id);

    public void deleteAll();

}
