package org.una.tramites.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.una.tramites.entities.Permiso;
import org.una.tramites.entities.Requisito;
import org.una.tramites.repositories.IRequisitoRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;
@Service
public class RequisitoServiceImplementation implements IRequisitoService{

    @Autowired
    private IRequisitoRepository requisitoRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<Requisito> findById(Long id) {
        return requisitoRepository.findById(id);
    }

    @Override
    public Optional<Requisito> findByDescripcion(String descripcion) {
        return Optional.empty();
    }

    @Override
    public Optional<Requisito> findByFechaRegistroBetween(Date startDate, Date endDate) {
        return Optional.empty();
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<List<Requisito>> findAll() {
        return Optional.ofNullable(requisitoRepository.findAll());
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