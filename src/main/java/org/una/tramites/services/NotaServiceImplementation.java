package org.una.tramites.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.una.tramites.entities.Nota;
import org.una.tramites.repositories.INotaRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.una.tramites.dto.NotaDTO;
import org.una.tramites.utils.MapperUtils;

@Service
public class NotaServiceImplementation implements INotaService {

    @Autowired
    private INotaRepository notaRepository;

    private Optional<List<NotaDTO>> findList(List<Nota> list) {
        if (list != null) {
            List<NotaDTO> notaDTO = MapperUtils.DtoListFromEntityList(list, NotaDTO.class);
            return Optional.ofNullable(notaDTO);
        } else {
            return null;
        }
    }

    private Optional<List<NotaDTO>> findList(Optional<List<Nota>> list) {
        if (list.isPresent()) {
            return findList(list.get());
        } else {
            return null;
        }
    }

    private Optional<NotaDTO> oneToDto(Optional<Nota> one) {
        if (one.isPresent()) {
            NotaDTO notaDTO = MapperUtils.DtoFromEntity(one.get(), NotaDTO.class);
            return Optional.ofNullable(notaDTO);
        } else {
            return null;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<NotaDTO> findById(Long id) {
        return oneToDto(notaRepository.findById(id));
    }


    @Override
    @Transactional
    public NotaDTO create(NotaDTO notaDTO) {
        Nota nota = MapperUtils.EntityFromDto(notaDTO, Nota.class);
        nota = notaRepository.save(nota);
        return MapperUtils.DtoFromEntity(nota, NotaDTO.class);
    }

    @Override
    @Transactional
    public Optional<NotaDTO> update(NotaDTO notaDTO, Long id) {

        if (notaRepository.findById(id).isPresent()) {
            Nota nota = MapperUtils.EntityFromDto(notaDTO, Nota.class);
            nota = notaRepository.save(nota);
            return Optional.ofNullable(MapperUtils.DtoFromEntity(nota, NotaDTO.class));
        } else {
            return null;
        }
    }

    @Override
    public Optional<List<NotaDTO>> findByTipo(boolean tipo) {
        return findList(notaRepository.findByTipo(tipo));
    }

    @Override
    public Optional<List<NotaDTO>> findByFechaRegistroBetween(Date startDate, Date endDate) {
        return findList(notaRepository.findByFechaRegistroBetween(startDate, endDate));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<NotaDTO>> findAll() {
        return findList(notaRepository.findAll());
    }

    @Override
    public void delete(Long id) {
        notaRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        notaRepository.deleteAll();
    }
}
