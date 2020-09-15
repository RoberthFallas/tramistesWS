/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tramites.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.una.tramites.entities.Variacion;
import org.una.tramites.repositories.IVariacionRepository;

/**
 *
 * @author LordLalo
 */
@Service
public class VariacionServiceImplementacion implements IVariacionService {

    @Autowired
    private IVariacionRepository variacionRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<List<Variacion>> findAll() {
        return Optional.ofNullable(variacionRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Variacion> findById(Long id) {
        return variacionRepository.findById(id);
    }

    @Override
    @Transactional
    public Variacion create(Variacion variacion) {
       return  variacionRepository.save(variacion);
    }

     @Override
    @Transactional
    public Optional<Variacion> update(Variacion variacion, Long id) {
        if(variacionRepository.findById(id).isPresent())
            return Optional.ofNullable(variacionRepository.save(variacion));
        return null;
    }

    @Override
    public void delete(Long id) {
       variacionRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
      
        variacionRepository.deleteAll();
    }

     @Override
    @Transactional(readOnly = true)
    public Optional<List<Variacion>> findByGrupo(boolean grupo) {
        return Optional.ofNullable(variacionRepository.findByGrupo(grupo));
    }

     @Override
    @Transactional(readOnly = true)
    public Optional<List<Variacion>> findByDescripcion(String descripcion) {
        return Optional.ofNullable(variacionRepository.findByDescripcion(descripcion));
    }

}
