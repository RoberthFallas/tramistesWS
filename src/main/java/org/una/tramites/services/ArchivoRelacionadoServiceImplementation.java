package org.una.tramites.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.una.tramites.entities.ArchivoRelacionado;
import org.una.tramites.repositories.IArchivoRelacionadoRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ArchivoRelacionadoServiceImplementation implements IArchivoRelacionadoService {

    @Autowired
    private IArchivoRelacionadoRepository archivorelacionadoRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<ArchivoRelacionado> findById(Long id) {
        return archivorelacionadoRepository.findById(id);
    }

    @Override
    public Optional<ArchivoRelacionado> findByTipo(boolean tipo) {
        return Optional.empty();
    }

    @Override
    public Optional<ArchivoRelacionado> findByFechaRegistroBetween(Date startDate, Date endDate) {
        return Optional.empty();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<ArchivoRelacionado>> findAll() {
        return Optional.ofNullable(archivorelacionadoRepository.findAll());
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
    public ArchivoRelacionado create(ArchivoRelacionado archivoRelacionado) {

        return archivorelacionadoRepository.save(archivoRelacionado);
    }

    @Override
    @Transactional
    public Optional<ArchivoRelacionado> update(ArchivoRelacionado archivoRelacionado, Long id) {

        if (archivorelacionadoRepository.findById(id).isPresent()) {
            return Optional.ofNullable(archivorelacionadoRepository.save(archivoRelacionado));
        } else {
            return null;
        }
    }

}
