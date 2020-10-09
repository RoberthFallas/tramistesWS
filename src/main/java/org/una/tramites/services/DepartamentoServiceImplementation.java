/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tramites.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.una.tramites.dto.DepartamentoDTO;
import org.una.tramites.entities.Departamento;
import org.una.tramites.repositories.IDepartamentoRepository;
import org.una.tramites.utils.MapperUtils;

/**
 *
 * @author roberth
 */
@Service
public class DepartamentoServiceImplementation implements IDepartamentoService {

    @Autowired
    private IDepartamentoRepository departamentoRepo;

    private Optional<List<DepartamentoDTO>> findList(List<Departamento> list) {
        if (list != null) {
            List<DepartamentoDTO> departamentoDTO = MapperUtils.DtoListFromEntityList(list, DepartamentoDTO.class);
            return Optional.ofNullable(departamentoDTO);
        } else {
            return null;
        }
    }

    private Optional<List<DepartamentoDTO>> findList(Optional<List<Departamento>> list) {
        if (list.isPresent()) {
            return findList(list.get());
        } else {
            return null;
        }
    }

    private Optional<DepartamentoDTO> oneToDto(Optional<Departamento> one) {
        if (one.isPresent()) {
            DepartamentoDTO departamentoDTO = MapperUtils.DtoFromEntity(one.get(), DepartamentoDTO.class);
            return Optional.ofNullable(departamentoDTO);
        } else {
            return null;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<DepartamentoDTO> findById(Long id) {
        return oneToDto(departamentoRepo.findById(id));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<DepartamentoDTO>> findAll() {
        return findList(departamentoRepo.findAll());
    }

    @Override
    public DepartamentoDTO create(DepartamentoDTO departamentoDTO) {
        Departamento departamento = MapperUtils.EntityFromDto(departamentoDTO, Departamento.class);
        departamento = departamentoRepo.save(departamento);
        return MapperUtils.DtoFromEntity(departamento, DepartamentoDTO.class);
    }

    @Override
    public void delete(Long id) {
        departamentoRepo.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<DepartamentoDTO>> findByEstado(boolean estado) {
        return findList(departamentoRepo.findByEstado(estado));

    }

    @Override
    @Transactional(readOnly = true)
    public Optional<DepartamentoDTO> findByNombre(String nombre) {
        return oneToDto(departamentoRepo.findByNombre(nombre));
    }

    @Override
    @Transactional
    public Optional<DepartamentoDTO> update(DepartamentoDTO departamentoDTO, Long id) {
        if (departamentoRepo.findById(id).isPresent()) {
            Departamento departamento = MapperUtils.EntityFromDto(departamentoDTO, Departamento.class);
            departamento = departamentoRepo.save(departamento);
            return Optional.ofNullable(MapperUtils.DtoFromEntity(departamento, DepartamentoDTO.class));
        } else {
            return null;
        }
    }

}
