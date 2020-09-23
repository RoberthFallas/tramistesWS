/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tramites.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.una.tramites.dto.PermisoDTO;
import org.una.tramites.entities.Permiso;
import org.una.tramites.repositories.IPermisoRepository;
import org.una.tramites.utils.MapperUtils;

/**
 *
 * @author LordLalo
 */
@Service
public class PermisoServiceImplementation implements IPermisoService {

    @Autowired
    private IPermisoRepository permisoRepository;

    private Optional<List<PermisoDTO>> findList(List<Permiso> list) {
        if (list != null) {
            List<PermisoDTO> permisoDTO = MapperUtils.DtoListFromEntityList(list, PermisoDTO.class);
            return Optional.ofNullable(permisoDTO);
        } else {
            return null;
        }
    }

    private Optional<List<PermisoDTO>> findList(Optional<List<Permiso>> list) {
        if (list.isPresent()) {
            return findList(list.get());
        } else {
            return null;
        }
    }

    private Optional<PermisoDTO> oneToDto(Optional<Permiso> one) {
        if (one.isPresent()) {
            PermisoDTO permisoDTO = MapperUtils.DtoFromEntity(one.get(), PermisoDTO.class);
            return Optional.ofNullable(permisoDTO);
        } else {
            return null;
        }
    }

//    @Override
//    @Transactional(readOnly = true)
//    public Optional<Permiso> findById(Long id) {
//        return permisoRepository.findById(id);
//    }
    @Override
    @Transactional(readOnly = true)
    public Optional<PermisoDTO> findById(Long id) {
        return oneToDto(permisoRepository.findById(id));
    }

//    @Override
//    @Transactional(readOnly = true)
//    public Optional<List<Permiso>> findByEstado(boolean estado) {
//        return Optional.ofNullable(permisoRepository.findByEstado(estado));
//
//    }
    @Override
    @Transactional(readOnly = true)
    public Optional<List<PermisoDTO>> findByEstado(boolean estado) {
        return findList(permisoRepository.findByEstado(estado));

    }

//    @Override
//    @Transactional(readOnly = true)
//    public Optional<List<Permiso>> findByFechaRegistroBetween(Date startDate, Date endDate) {
//        return Optional.ofNullable(permisoRepository.findByFechaRegistroBetween(startDate, endDate));
//    }
    @Override
    @Transactional(readOnly = true)
    public Optional<List<PermisoDTO>> findByFechaRegistroBetween(Date startDate, Date endDate) {
        return findList(permisoRepository.findByFechaRegistroBetween(startDate, endDate));
    }

    @Override
    @Transactional
    public PermisoDTO create(PermisoDTO permisoDTO) {
        Permiso permiso = MapperUtils.EntityFromDto(permisoDTO, Permiso.class);
        permiso = permisoRepository.save(permiso);
        return MapperUtils.DtoFromEntity(permiso, PermisoDTO.class);
        // return permisoRepository.save(permiso);
        //   return Optional.ofNullable(permisoRepository.save(permiso));
    }

    @Override
    @Transactional
    public Optional<PermisoDTO> update(PermisoDTO permisoDTO, Long id) {
       if (permisoRepository.findById(id).isPresent()) {
            Permiso permiso = MapperUtils.EntityFromDto(permisoDTO, Permiso.class);
            permiso = permisoRepository.save(permiso);
            return Optional.ofNullable(MapperUtils.DtoFromEntity(permiso, PermisoDTO.class));
        } else {
            return null;
        }
    }

    @Override
    @Transactional
    public void delete(Long id) {
        permisoRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteAll() {
        permisoRepository.deleteAll();
    }

    @Override
    public Long countByEstado(boolean estado) {
        return permisoRepository.countByEstado(estado);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<PermisoDTO> findByCodigo(String codigoPermiso) {
        return oneToDto(permisoRepository.findByCodigo(codigoPermiso));
//      return Optional.ofNullable(permisoRepository.findByCodigo(codigoPermiso));
    }

}
