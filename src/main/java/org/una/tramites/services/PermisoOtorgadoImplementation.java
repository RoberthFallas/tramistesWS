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
import org.una.tramites.dto.PermisoOtorgadoDTO;
import org.una.tramites.entities.PermisoOtorgado;
import org.una.tramites.repositories.IPermisoOtorgadoRepository;
import org.una.tramites.utils.MapperUtils;

/**
 *
 * @author LordLalo
 */
@Service
public class PermisoOtorgadoImplementation implements IPermisoOtorgadoService {

    @Autowired
    private IPermisoOtorgadoRepository permisoOtorgadoRepository;

    private Optional<List<PermisoOtorgadoDTO>> findList(List<PermisoOtorgado> list) {
        if (list != null) {
            List<PermisoOtorgadoDTO> usuariosDTO = MapperUtils.DtoListFromEntityList(list, PermisoOtorgadoDTO.class);
            return Optional.ofNullable(usuariosDTO);
        } else {
            return null;
        }
    }

    private Optional<List<PermisoOtorgadoDTO>> findList(Optional<List<PermisoOtorgado>> list) {
        if (list.isPresent()) {
            return findList(list.get());
        } else {
            return null;
        }
    }

    private Optional<PermisoOtorgadoDTO> oneToDto(Optional<PermisoOtorgado> one) {
        if (one.isPresent()) {
            PermisoOtorgadoDTO permisoOtorgadoDTO = MapperUtils.DtoFromEntity(one.get(), PermisoOtorgadoDTO.class);
            return Optional.ofNullable(permisoOtorgadoDTO);
        } else {
            return null;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<PermisoOtorgadoDTO> findById(Long usuarioId) {
        return oneToDto(permisoOtorgadoRepository.findById(usuarioId));
    }

//    @Override
//    @Transactional(readOnly = true)
//    public Optional<List<PermisoOtorgado>> findByUsuarioId(Long usuarioId) {
//        return Optional.ofNullable(permisoOtorgadoRepository.findByUsuarioId(usuarioId));
//    }
    @Override
    @Transactional(readOnly = true)
    public Optional<List<PermisoOtorgadoDTO>> findByUsuarioId(Long usuarioId) {
      return findList(permisoOtorgadoRepository.findByUsuarioId(usuarioId));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<PermisoOtorgadoDTO>> findByPermisoId(Long permisoId) {
//        return Optional.ofNullable(permisoOtorgadoRepository.findByPermisoId(permisoId));
          return findList(permisoOtorgadoRepository.findByPermisoId(permisoId));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<PermisoOtorgadoDTO>> findByUsuarioIdAndEstado(Long usuarioId, boolean estado) {
//        return Optional.ofNullable(permisoOtorgadoRepository.findByUsuarioIdAndEstado(usuarioId, estado));
          return findList(permisoOtorgadoRepository.findByUsuarioIdAndEstado(usuarioId, estado));
    }

    @Override
    public Optional<List<PermisoOtorgadoDTO>> findByPermisoIdAndEstado(Long permisoId, boolean estado) {
//        return Optional.ofNullable(permisoOtorgadoRepository.findByPermisoIdAndEstado(permisoId, estado));
 return findList(permisoOtorgadoRepository.findByPermisoIdAndEstado(permisoId, estado));
    }

    @Override
    public Optional<List<PermisoOtorgadoDTO>> findByFechaRegistroBetween(Date startDate, Date endDate) {
   //     return Optional.ofNullable(permisoOtorgadoRepository.findByFechaRegistroBetween(startDate, endDate));
        return findList(permisoOtorgadoRepository.findByFechaRegistroBetween(startDate, endDate));
    }

//     @Override
//    @Transactional
//    public PermisoOtorgado create(PermisoOtorgado permisoOtorgado) {
//         return permisoOtorgadoRepository.save(permisoOtorgado);
//    }
    @Override
    @Transactional
    public PermisoOtorgadoDTO create(PermisoOtorgadoDTO permisoOtorgadoDTO) {
        PermisoOtorgado permisoOtorgado = MapperUtils.EntityFromDto(permisoOtorgadoDTO, PermisoOtorgado.class);
        permisoOtorgado = permisoOtorgadoRepository.save(permisoOtorgado);
        return MapperUtils.DtoFromEntity(permisoOtorgado, PermisoOtorgadoDTO.class);
    }

    @Override
    @Transactional
    public Optional<PermisoOtorgadoDTO> update(PermisoOtorgadoDTO permisoOtorgadoDTO, Long id) {
          if (permisoOtorgadoRepository.findById(id).isPresent()) {
           PermisoOtorgado permisoOtorgado = MapperUtils.EntityFromDto(permisoOtorgadoDTO, PermisoOtorgado.class);
            permisoOtorgado = permisoOtorgadoRepository.save(permisoOtorgado);
            return Optional.ofNullable(MapperUtils.DtoFromEntity(permisoOtorgado,PermisoOtorgadoDTO.class));
        } else {
            return null;
        }
    }

    @Override
    public void delete(Long id) {
        permisoOtorgadoRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        permisoOtorgadoRepository.deleteAll();
    }

}
