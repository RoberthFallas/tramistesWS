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
import org.una.tramites.dto.TramiteTipoDTO;
import org.una.tramites.entities.Departamento;
import org.una.tramites.entities.TramiteTipo;
import org.una.tramites.repositories.IDepartamentoRepository;
import org.una.tramites.repositories.ITipoTramiteRepository;
import org.una.tramites.utils.MapperUtils;

/**
 *
 * @author Roberth :)
 */
@Service
public class TipoTramiteServiceImplementation implements ITipoTramiteService {

    @Autowired
    private ITipoTramiteRepository tipoTramRepo;
    @Autowired
    private IDepartamentoRepository depaRepo;

    @Override
    @Transactional
    public Optional<TramiteTipoDTO> create(TramiteTipoDTO tipoTramite) {
        Optional<Departamento> opt = depaRepo.findById(tipoTramite.getDepartamento().getId());
        if (opt.isPresent()) {
            Departamento entityDepa = opt.get();
            TramiteTipo toSave = MapperUtils.entityFromDto(tipoTramite, TramiteTipo.class);
            toSave.setDepartamento(entityDepa);
            toSave = tipoTramRepo.save(toSave);
            TramiteTipoDTO tramDto = MapperUtils.DtoFromEntity(toSave, TramiteTipoDTO.class);
            tramDto.adjuntarDepartamento(tipoTramite.getDepartamento());
            return Optional.of(tramDto);
        } else {
            return Optional.empty();
        }
    }

    @Override
    //@Transactional
    public Optional<TramiteTipoDTO> update(TramiteTipoDTO tipoTramite) {
        Optional<TramiteTipo> result = tipoTramRepo.findById(tipoTramite.getId());
        if (result.isPresent()) {
            TramiteTipo entity = MapperUtils.entityFromDto(tipoTramite, TramiteTipo.class);
            entity = tipoTramRepo.save(entity);
            TramiteTipoDTO tramDto = MapperUtils.DtoFromEntity(entity, TramiteTipoDTO.class);
            tramDto.adjuntarDepartamento(tipoTramite.getDepartamento());
            return Optional.of(tramDto);
        } else {
            return null;
        }
    }

    @Override
    //@Transactional(readOnly = true)
    public Optional<List<TramiteTipoDTO>> getByEstado(boolean estado) {
        List<TramiteTipo> result = tipoTramRepo.findByEstado(estado);
        List<TramiteTipoDTO> dtoList = MapperUtils.DtoListFromEntityList(result, TramiteTipoDTO.class);
        return Optional.of(dtoList);
    }

    @Override
    //@Transactional(readOnly = true)
    public Optional<List<TramiteTipoDTO>> getByDescripcion(String descripcion) {
        List<TramiteTipo> result = tipoTramRepo.findByDescripcion(descripcion);
        List<TramiteTipoDTO> dtoList = MapperUtils.DtoListFromEntityList(result, TramiteTipoDTO.class);
        return Optional.of(dtoList);

    }

    @Override
    //@Transactional(readOnly = true)
    public Optional<List<TramiteTipoDTO>> getAll() {
        List<TramiteTipo> result = tipoTramRepo.findAll();
        List<TramiteTipoDTO> dtoList = MapperUtils.DtoListFromEntityList(result, TramiteTipoDTO.class);
        return Optional.of(dtoList);
    }

}
