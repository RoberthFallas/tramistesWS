/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tramites.services;

import java.util.List;
import java.util.Optional;
import org.una.tramites.entities.ParametroGeneral;

/**
 *
 * @author LordLalo
 */
public interface IParametroGeneralService {

    public Optional<List<ParametroGeneral>> findAll();

    public Optional<ParametroGeneral> findById(Long id);

    public ParametroGeneral create(ParametroGeneral parametroGeneral);

    public Optional<ParametroGeneral> update(ParametroGeneral parametroGeneral, Long id);

    public void delete(Long id);

    public void deleteAll();
}
