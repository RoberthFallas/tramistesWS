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
import org.una.tramites.dto.VariacionDTO;
import org.una.tramites.entities.TramiteTipo;
import org.una.tramites.entities.Variacion;
import org.una.tramites.repositories.ITipoTramiteRepository;
import org.una.tramites.repositories.IVariacionRepository;
import org.una.tramites.utils.MapperUtils;

/**
 *
 * @author LordLalo
 */
@Service
public class VariacionServiceImplementacion implements IVariacionService {

    @Autowired
    private IVariacionRepository variacionRepository;
    @Autowired
    private ITipoTramiteRepository tipoTramiteRepo;
private Optional<List<VariacionDTO>> findList(List<Variacion> list) {
        if (list != null) {
            List<VariacionDTO> notaDTO = MapperUtils.DtoListFromEntityList(list, VariacionDTO.class);
            return Optional.ofNullable(notaDTO);
        } else {
            return null;
        }
    }

    private Optional<List<VariacionDTO>> findList(Optional<List<Variacion>> list) {
        if (list.isPresent()) {
            return findList(list.get());
        } else {
            return null;
        }
    }

    private Optional<VariacionDTO> oneToDto(Optional<Variacion> one) {
        if (one.isPresent()) {
          VariacionDTO notaDTO = MapperUtils.DtoFromEntity(one.get(), VariacionDTO.class);
            return Optional.ofNullable(notaDTO);
        } else {
            return null;
        }}
    
    @Override
    @Transactional(readOnly = true)
    public Optional<List<VariacionDTO>> findAll() {
        return findList(variacionRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<VariacionDTO> findById(Long id) {
        return oneToDto(variacionRepository.findById(id));
    }

    @Override
    @Transactional
    public Optional<VariacionDTO> create(VariacionDTO variacion) {
        Optional<TramiteTipo> opt = tipoTramiteRepo.findById(variacion.getTramite_tipo().getId());
        if (opt.isPresent()) {
            TramiteTipo entityTT = opt.get();
            Variacion toSave = MapperUtils.EntityFromDto(variacion, Variacion.class);
            toSave.setTramiteTipo(entityTT);
            toSave = variacionRepository.save(toSave);
            VariacionDTO variacionDTO = MapperUtils.DtoFromEntity(toSave, VariacionDTO.class);
            variacionDTO.adjuntarTipoTramite(variacion.getTramite_tipo());
            return Optional.of(variacionDTO);
        } else {
            return Optional.empty();
        }
    }

    @Override
    @Transactional
    public Optional<VariacionDTO> update(VariacionDTO variacionDTO, Long id) {
        if (variacionRepository.findById(id).isPresent()) {
             Variacion variacion=MapperUtils.EntityFromDto(variacionDTO,Variacion.class);
           variacion=variacionRepository.save(variacion);
           return Optional.ofNullable(MapperUtils.DtoFromEntity(variacion,VariacionDTO.class));
        } else {
            return null;
        }
      
    }

    @Override
    public void delete(Long id) {
        variacionRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {

        variacionRepository.deleteAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<VariacionDTO>> findByGrupo(boolean grupo) {
        return findList(variacionRepository.findByGrupo(grupo));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<VariacionDTO>> findByDescripcion(String descripcion) {
    return  findList(variacionRepository.findByDescripcion(descripcion));
    }

}
