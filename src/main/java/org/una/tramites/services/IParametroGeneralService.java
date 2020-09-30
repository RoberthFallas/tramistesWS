/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tramites.services;

import java.util.List;
import java.util.Optional;
import org.una.tramites.dto.ParametroGeneralDTO;
import org.una.tramites.entities.ParametroGeneral;

/**
 *
 * @author LordLalo
 */
public interface IParametroGeneralService {

    public Optional<List<ParametroGeneralDTO>> findAll();

    public Optional<ParametroGeneralDTO> findById(Long id);

    public ParametroGeneralDTO create(ParametroGeneralDTO parametroGeneralDTO);

    public Optional<ParametroGeneralDTO> update(ParametroGeneralDTO parametroGeneralDTO, Long id);

    public void delete(Long id);

    public void deleteAll();
}
