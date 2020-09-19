package org.una.tramites.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.una.tramites.entities.RequisitoPresentado;
import org.una.tramites.repositories.IRequisitoPresentadoRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;
@Service
public class RequisitoPresentadoServiceImplementation implements IRequisitoPresentadoService{

    @Autowired
    private IRequisitoPresentadoRepository requisitopresentadoRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<RequisitoPresentado> findById(Long id) {
        return requisitopresentadoRepository.findById(id);
    }


    @Override
    public Optional<RequisitoPresentado> findByFechaRegistroBetween(Date startDate, Date endDate) {
        return Optional.empty();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<RequisitoPresentado>> findAll() {
        return Optional.ofNullable(requisitopresentadoRepository.findAll());
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
    public RequisitoPresentado create(RequisitoPresentado requisitoPresentado) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Optional<RequisitoPresentado> update(RequisitoPresentado requisitoPresentado, Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}