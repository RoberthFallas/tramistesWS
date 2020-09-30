/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tramites.services;

import java.util.List;
import java.util.Optional;
import org.una.tramites.dto.DepartamentoDTO;
import org.una.tramites.entities.Departamento;

/**
 *
 * @author Roberth
 */
public interface IDepartamentoService {
    
    public Optional<List<DepartamentoDTO>> findAll();

    public DepartamentoDTO create(DepartamentoDTO departamento);

    public void delete(Long id);

    public Optional<DepartamentoDTO> findById(Long id);

    public Optional<List<DepartamentoDTO>> findByEstado(boolean estado);

    public Optional<DepartamentoDTO> findByNombre(String cedula);

    public Optional<DepartamentoDTO> update(DepartamentoDTO departamento, Long id);

}
