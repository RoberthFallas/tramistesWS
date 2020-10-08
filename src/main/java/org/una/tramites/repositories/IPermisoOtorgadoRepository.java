/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tramites.repositories;

import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.una.tramites.entities.PermisoOtorgado;

/**
 *
 * @author LordLalo
 */
public interface IPermisoOtorgadoRepository extends JpaRepository<PermisoOtorgado, Long> {

    public List<PermisoOtorgado> findByUsuarioId(Long usuarioId);

    public List<PermisoOtorgado> findByPermisoId(Long permisoId);

    public List<PermisoOtorgado> findByUsuarioIdAndEstado(Long usuarioId, boolean estado);

    public List<PermisoOtorgado> findByPermisoIdAndEstado(Long permisoId, boolean estado);

    public List<PermisoOtorgado> findByFechaRegistroBetween(Date startDate, Date endDate);
}
