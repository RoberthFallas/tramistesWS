/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tramites.services;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.una.tramites.dto.TramiteTipoDTO;
import org.una.tramites.entities.Departamento;
import org.una.tramites.entities.TramiteTipo;
import org.una.tramites.repositories.IDepartamentoRepository;
import org.una.tramites.repositories.ITipoTramiteRepository;
import org.una.tramites.utils.MapperUtils;

/**
 *
 * @author rober
 */
@Service
public class TipoTramiteServiceImplementation implements ITipoTramiteService {

    @Autowired
    private ITipoTramiteRepository tipoTramRepo;
    @Autowired
    private IDepartamentoRepository depaRepo;

    @Override
    public Optional<TramiteTipoDTO> create(TramiteTipoDTO tipoTramite) {
        Optional<Departamento> opt = depaRepo.findById(tipoTramite.getDepartamento().getId());
        if (opt.isPresent()) {
            Departamento entityDepa = opt.get();
            TramiteTipo toSave = MapperUtils.entityFromDto(tipoTramite, TramiteTipo.class);
            toSave.setDepartamento(entityDepa);
            toSave = tipoTramRepo.save(toSave);
            TramiteTipoDTO tramDto = MapperUtils.DtoFromEntity(toSave, TramiteTipoDTO.class);
            tramDto.adjuntarDepartamento(tipoTramite.getDepartamento());
            return Optional.ofNullable(tramDto);
        } else {
            return Optional.empty();
        }
    }

}
