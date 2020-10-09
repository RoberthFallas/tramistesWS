/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tramites.repositories;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.una.tramites.entities.Nota;

/**
 *
 * @author LordLalo
 */
public interface INotaRepository extends JpaRepository<Nota, Long> {

    public Optional<Nota> findById(Long id);

    public List<Nota> findByTipo(boolean tipo);

    public List<Nota> findByFechaRegistroBetween(Date startDate, Date endDate);

}
