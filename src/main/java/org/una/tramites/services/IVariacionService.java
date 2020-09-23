/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tramites.services;

import java.util.List;
import java.util.Optional;
import org.una.tramites.dto.VariacionDTO;
import org.una.tramites.entities.Variacion;

/**
 *
 * @author LordLalo
 */
public interface IVariacionService {

//    public Optional<List<Variacion>> findAll();
//
//    public Optional<Variacion> findById(Long id);
//
//    public Optional<VariacionDTO> create(VariacionDTO variacion);
//
//    public Optional<Variacion> update(Variacion variacion, Long id);
//
//    public void delete(Long id);
//
//    public void deleteAll();
//
//    public Optional<List<Variacion>> findByGrupo(boolean grupo);
//
//    public Optional<List<Variacion>> findByDescripcion(String descripcion);
   public Optional<List<VariacionDTO>> findAll();

    public Optional<VariacionDTO> findById(Long id);

    public Optional<VariacionDTO> create(VariacionDTO variacionDTO);

    public Optional<VariacionDTO> update(VariacionDTO variacionDTO, Long id);

    public void delete(Long id);

    public void deleteAll();

    public Optional<List<VariacionDTO>> findByGrupo(boolean grupo);

    public Optional<List<VariacionDTO>> findByDescripcion(String descripcion);

}
