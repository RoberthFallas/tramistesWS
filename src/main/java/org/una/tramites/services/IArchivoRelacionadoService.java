/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tramites.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.una.tramites.entities.ArchivoRelacionado;



/**
 *
 * @author LordLalo
 */
public interface IArchivoRelacionadoService {
    public Optional<List<ArchivoRelacionado>> findAll();

    public Optional<ArchivoRelacionado> findById(Long id);

    public Optional<ArchivoRelacionado> findByTipo(boolean tipo);
            public ArchivoRelacionado create(ArchivoRelacionado archivoRelacionado);

    public Optional<ArchivoRelacionado> update(ArchivoRelacionado archivoRelacionado, Long id);

    public Optional<ArchivoRelacionado> findByFechaRegistroBetween(Date startDate, Date endDate);
    public void delete(Long id);
   public  void deleteAll();
}
