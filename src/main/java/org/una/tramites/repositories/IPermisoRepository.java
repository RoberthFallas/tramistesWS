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
import org.springframework.data.jpa.repository.Query;
import org.una.tramites.entities.Permiso;

/**
 *
 * @author LordLalo
 */
public interface IPermisoRepository extends JpaRepository<Permiso, Long> {

    public List<Permiso> findByEstado(boolean estado);

    List<Permiso> findByFechaRegistroBetween(Date startDate, Date endDate);

    public Long countByEstado(boolean estado);

    @Query("select p from Permiso p where p.codigo = :codigo")
    public  Optional<Permiso> findByCodigo(String codigo);
  
}
