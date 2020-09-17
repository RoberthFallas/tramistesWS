/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tramites.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.una.tramites.entities.TramiteTipo;

/**
 *
 * @author Roberth :)
 */
public interface ITipoTramiteRepository extends JpaRepository<TramiteTipo, Long> {

    public List<TramiteTipo> findByEstado(boolean estado);

    public List<TramiteTipo> findByDescripcion(String descripcion);
}
