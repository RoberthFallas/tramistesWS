package org.una.tramites.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.una.tramites.entities.Requisito;
import org.una.tramites.repositories.IRequisitoRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.una.tramites.dto.RequisitoDTO;
import org.una.tramites.utils.MapperUtils;
@Service
public class RequisitoServiceImplementation implements IRequisitoService{

    @Autowired
    private IRequisitoRepository requisitoRepository;
    private Optional<List<RequisitoDTO>> findList(List<Requisito> list) {
        if (list != null) {
            List<RequisitoDTO> usuariosDTO = MapperUtils.DtoListFromEntityList(list,RequisitoDTO.class);
            return Optional.ofNullable(usuariosDTO);
        } else {
            return null;
        }
    }


    private Optional<List<RequisitoDTO>> findList(Optional<List<Requisito>> list) {
        if (list.isPresent()) {
            return findList(list.get());
        } else {
            return null;
        }
    }

    private Optional<RequisitoDTO> oneToDto(Optional<Requisito> one) {
        if (one.isPresent()) {
          RequisitoDTO requisitoDTO= MapperUtils.DtoFromEntity(one.get(), RequisitoDTO.class);
            return Optional.ofNullable(requisitoDTO);
        } else {
            return null;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<RequisitoDTO> findById(Long id) {
        return oneToDto(requisitoRepository.findById(id));
    }

    @Override
    public Optional<List<RequisitoDTO>> findByDescripcion(String descripcion) {
        return findList(requisitoRepository.findByDescripcion(descripcion));
    }

    @Override
    public Optional<List<RequisitoDTO>> findByFechaRegistroBetween(Date startDate, Date endDate) {
        return findList(requisitoRepository.findByFechaRegistroBetween(startDate, endDate));
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<List<RequisitoDTO>> findAll() {
        return findList(requisitoRepository.findAll());
    }


    @Override
    public void delete(Long id) {
        requisitoRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        requisitoRepository.deleteAll();
    }
}