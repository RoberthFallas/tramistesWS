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
import org.una.tramites.entities.ParametroGeneral;
import org.una.tramites.repositories.IParametroGeneralRepository;

/**
 *
 * @author LordLalo
 */
@Service
public class ParametroGeneralImplementation implements IParametroGeneralService {

    @Autowired
    private IParametroGeneralRepository parametroGeneralRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<List<ParametroGeneral>> findAll() {
        return Optional.ofNullable(parametroGeneralRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ParametroGeneral> findById(Long id) {
    return  parametroGeneralRepository.findById(id);
    }

     @Override
    @Transactional
    public ParametroGeneral create(ParametroGeneral parametroGeneral) {
    
        return  parametroGeneralRepository.save(parametroGeneral);
    }

    @Override
    public Optional<ParametroGeneral> update(ParametroGeneral parametroGeneral, Long id) {
           if (parametroGeneralRepository.findById(id).isPresent()) {
         return Optional.ofNullable(parametroGeneralRepository.save(parametroGeneral));
       } else {
            return null;
        }
    }
   @Override
    @Transactional
    public void delete(Long id) {
       parametroGeneralRepository.deleteById(id);
    }

      @Override
    @Transactional
    public void deleteAll() {
     parametroGeneralRepository.deleteAll();
    }
}
