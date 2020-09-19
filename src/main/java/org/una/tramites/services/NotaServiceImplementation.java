package org.una.tramites.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.una.tramites.entities.Nota;
import org.una.tramites.repositories.INotaRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class NotaServiceImplementation implements INotaService {

    @Autowired
    private INotaRepository notaRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<Nota> findById(Long id) {
        return notaRepository.findById(id);
    }

    @Override
    @Transactional
    public Nota create(Nota nota) {

        return notaRepository.save(nota);
    }

    @Override
    @Transactional
    public Optional<Nota> update(Nota nota, Long id) {

        if (notaRepository.findById(id).isPresent()) {
            return Optional.ofNullable(notaRepository.save(nota));
        } else {
            return null;
        }
    }

    @Override
    public Optional<Nota> findByTipo(boolean tipo) {
        return Optional.empty();
    }

    @Override
    public Optional<Nota> findByFechaRegistroBetween(Date startDate, Date endDate) {
        return Optional.empty();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<Nota>> findAll() {
        return Optional.ofNullable(notaRepository.findAll());
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
