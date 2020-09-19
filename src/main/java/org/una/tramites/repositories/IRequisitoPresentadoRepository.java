/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tramites.repositories;


import org.una.tramites.entities.RequisitoPresentado;

/**
 *
 * @author LordLalo
 */
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRequisitoPresentadoRepository  extends JpaRepository<RequisitoPresentado, Long> {

    public Optional<RequisitoPresentado> findById(Long id);

    public List<RequisitoPresentado> findByFechaRegistroBetween(Date startDate, Date endDate);
}
