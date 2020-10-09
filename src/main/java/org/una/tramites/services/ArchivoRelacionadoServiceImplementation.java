package org.una.tramites.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.una.tramites.entities.ArchivoRelacionado;
import org.una.tramites.repositories.IArchivoRelacionadoRepository;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.una.tramites.dto.ArchivoRelacionadoDTO;
import org.una.tramites.utils.MapperUtils;

@Service
public class ArchivoRelacionadoServiceImplementation implements IArchivoRelacionadoService {

    @Autowired
    private IArchivoRelacionadoRepository archivorelacionadoRepository;

    private Optional<List<ArchivoRelacionadoDTO>> findList(List<ArchivoRelacionado> list) {
        if (list != null) {
            List<ArchivoRelacionadoDTO> archivoRelacionadoDTO = MapperUtils.DtoListFromEntityList(list, ArchivoRelacionadoDTO.class);
            return Optional.ofNullable(archivoRelacionadoDTO);
        } else {
            return null;
        }
    }

    private Optional<List<ArchivoRelacionadoDTO>> findList(Optional<List<ArchivoRelacionado>> list) {
        if (list.isPresent()) {
            return findList(list.get());
        } else {
            return null;
        }
    }

    private Optional<ArchivoRelacionadoDTO> oneToDto(Optional<ArchivoRelacionado> one) {
        if (one.isPresent()) {
            ArchivoRelacionadoDTO archivoRelacionadoDTO = MapperUtils.DtoFromEntity(one.get(), ArchivoRelacionadoDTO.class);
            return Optional.ofNullable(archivoRelacionadoDTO);
        } else {
            return null;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ArchivoRelacionadoDTO> findById(Long id) {
        return oneToDto(archivorelacionadoRepository.findById(id));
    }

    @Override
    public Optional<List<ArchivoRelacionadoDTO>> findByTipo(boolean tipo) {
        return findList(archivorelacionadoRepository.findByTipo(tipo));
    }

    @Override
    public Optional<List<ArchivoRelacionadoDTO>> findByFechaRegistroBetween(Date startDate, Date endDate) {
        return findList(archivorelacionadoRepository.findByFechaRegistroBetween(startDate, endDate));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<ArchivoRelacionadoDTO>> findAll() {
        return findList(archivorelacionadoRepository.findAll());
    }

    @Override
    public void delete(Long id) {
        archivorelacionadoRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        archivorelacionadoRepository.deleteAll();
    }

    @Override
    @Transactional
    public ArchivoRelacionadoDTO create(ArchivoRelacionadoDTO archivoRelacionadoDTO) {
        ArchivoRelacionado archivoRelacionado = MapperUtils.EntityFromDto(archivoRelacionadoDTO, ArchivoRelacionado.class);
        archivoRelacionado = archivorelacionadoRepository.save(archivoRelacionado);
        return MapperUtils.DtoFromEntity(archivoRelacionado, ArchivoRelacionadoDTO.class);
    }

    @Override
    @Transactional
    public Optional<ArchivoRelacionadoDTO> update(ArchivoRelacionadoDTO archivoRelacionadoDTO, Long id) {

        if (archivorelacionadoRepository.findById(id).isPresent()) {
            ArchivoRelacionado archivoRelacionado = MapperUtils.EntityFromDto(archivoRelacionadoDTO, ArchivoRelacionado.class);
            archivoRelacionado = archivorelacionadoRepository.save(archivoRelacionado);
            return Optional.ofNullable(MapperUtils.DtoFromEntity(archivoRelacionado, ArchivoRelacionadoDTO.class));
        } else {
            return null;
        }
    }
}
