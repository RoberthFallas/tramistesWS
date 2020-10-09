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
import org.una.tramites.entities.Requisito;

/**
 *
 * @author LordLalo
 */
import java.util.Date;
import java.util.List;
import java.util.Optional;
public interface IRequisitoRepository extends JpaRepository<Requisito, Long>{
    public Optional<Requisito> findById(Long id);

    public List<Requisito> findByDescripcion(String descripcion);

    public List<Requisito> findByFechaRegistroBetween(Date startDate, Date endDate);
}
