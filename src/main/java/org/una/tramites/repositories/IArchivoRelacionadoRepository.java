/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tramites.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.una.tramites.entities.ArchivoRelacionado;

/**
 *
 * @author LordLalo
 */
import java.util.Date;
import java.util.Optional;
import java.util.List;
public interface IArchivoRelacionadoRepository extends JpaRepository<ArchivoRelacionado, Long>{

    public Optional<ArchivoRelacionado> findById(Long id);

    public List<ArchivoRelacionado> findByTipo(boolean tipo);

    public List<ArchivoRelacionado> findByFechaRegistroBetween(Date startDate, Date endDate);

}
