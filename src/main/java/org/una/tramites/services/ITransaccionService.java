/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tramites.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.una.tramites.dto.TransaccionDTO;
import org.una.tramites.entities.Transaccion;

/**
 *
 * @author LordLalo
 */
public interface ITransaccionService {

    public Optional<TransaccionDTO> findById(Long id);

    public Optional<List<TransaccionDTO>> findByUsuarioIdAndFechaRegistroBetween(Long usuarioId, Date startDate, Date endDate);

    public Optional<List<TransaccionDTO>> findByPermisoIdAndFechaRegistroBetween(Long permisoId, Date startDate, Date endDate);

    public Optional<List<TransaccionDTO>> findByObjetoAndFechaRegistroBetween(String objeto, Date startDate, Date endDate);

    public Optional<List<TransaccionDTO>> findByFechaRegistroBetween(Date startDate, Date endDate);

    public TransaccionDTO create(TransaccionDTO transaccionDTO);

    public Optional<TransaccionDTO> update(TransaccionDTO transaccionDTO,Long id);

}
