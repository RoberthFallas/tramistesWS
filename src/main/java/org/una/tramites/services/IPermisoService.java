/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tramites.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.una.tramites.entities.Permiso;

/**
 *
 * @author LordLalo
 */
public interface IPermisoService {
    
    public Optional<Permiso> findById(Long id);

    public Optional<List<Permiso>> findByEstado(boolean estado);

    public Optional<List<Permiso>> findByFechaRegistroBetween(Date startDate, Date endDate);

    public Permiso create(Permiso permiso);

    public Optional<Permiso> update(Permiso permiso, Long id);

    public void delete(Long id);

    public void deleteAll();
    
    public Long countByEstado(boolean estado); 
    
    public Optional<Permiso> findByCodigo(String codigoPermiso);

}
