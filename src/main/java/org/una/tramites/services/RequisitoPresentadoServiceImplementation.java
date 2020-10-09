package org.una.tramites.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.una.tramites.entities.RequisitoPresentado;
import org.una.tramites.repositories.IRequisitoPresentadoRepository;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.una.tramites.dto.RequisitoPresentadoDTO;
import org.una.tramites.utils.MapperUtils;

@Service
public class RequisitoPresentadoServiceImplementation implements IRequisitoPresentadoService {

    @Autowired
    private IRequisitoPresentadoRepository requisitopresentadoRepository;

    private Optional<List<RequisitoPresentadoDTO>> findList(List<RequisitoPresentado> list) {
        if (list != null) {
            List<RequisitoPresentadoDTO> usuariosDTO = MapperUtils.DtoListFromEntityList(list, RequisitoPresentadoDTO.class);
            return Optional.ofNullable(usuariosDTO);
        } else {
            return null;
        }
    }

    private Optional<List<RequisitoPresentadoDTO>> findList(Optional<List<RequisitoPresentado>> list) {
        if (list.isPresent()) {
            return findList(list.get());
        } else {
            return null;
        }
    }

    private Optional<RequisitoPresentadoDTO> oneToDto(Optional<RequisitoPresentado> one) {
        if (one.isPresent()) {
            RequisitoPresentadoDTO requisitoPresentadoDTO = MapperUtils.DtoFromEntity(one.get(), RequisitoPresentadoDTO.class);
            return Optional.ofNullable(requisitoPresentadoDTO);
        } else {
            return null;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<RequisitoPresentadoDTO> findById(Long id) {
        return oneToDto(requisitopresentadoRepository.findById(id));
    }

    @Override
    public Optional<List<RequisitoPresentadoDTO>> findByFechaRegistroBetween(Date startDate, Date endDate) {
        return findList(requisitopresentadoRepository.findByFechaRegistroBetween(startDate, endDate));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<RequisitoPresentadoDTO>> findAll() {
        return findList(requisitopresentadoRepository.findAll());
    }

    @Override
    public void delete(Long id) {
        requisitopresentadoRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        requisitopresentadoRepository.deleteAll();
    }

    @Override
    @Transactional
    public RequisitoPresentadoDTO create(RequisitoPresentadoDTO requisitoPresentadoDTO) {
        RequisitoPresentado requisitoPresentado = MapperUtils.DtoFromEntity(requisitoPresentadoDTO, RequisitoPresentado.class);
        requisitoPresentado = requisitopresentadoRepository.save(requisitoPresentado);
        return MapperUtils.DtoFromEntity(requisitoPresentado, RequisitoPresentadoDTO.class);
    }

    @Override
    @Transactional
    public Optional<RequisitoPresentadoDTO> update(RequisitoPresentadoDTO requisitoPresentadoDTO, Long id) {
        if (requisitopresentadoRepository.findById(id).isPresent()) {
            RequisitoPresentado requisitoPresentado = MapperUtils.EntityFromDto(requisitoPresentadoDTO, RequisitoPresentado.class);
            requisitoPresentado = requisitopresentadoRepository.save(requisitoPresentado);
            return Optional.ofNullable(MapperUtils.DtoFromEntity(requisitoPresentado, RequisitoPresentadoDTO.class));
        } else {
            return null;
        }
    }
}
