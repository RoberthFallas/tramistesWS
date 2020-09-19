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
import org.una.tramites.dto.VariacionDTO;
import org.una.tramites.entities.TramiteTipo;
import org.una.tramites.entities.Variacion;
import org.una.tramites.repositories.ITipoTramiteRepository;
import org.una.tramites.repositories.IVariacionRepository;
import org.una.tramites.utils.MapperUtils;

/**
 *
 * @author LordLalo
 */
@Service
public class VariacionServiceImplementacion implements IVariacionService {

    @Autowired
    private IVariacionRepository variacionRepository;
    @Autowired
    private ITipoTramiteRepository tipoTramiteRepo;

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

//    @Override
//    @Transactional
//    public Optional<VariacionDTO> create(VariacionDTO variacion) {
//        Optional<TramiteTipo> opt = tipoTramiteRepo.findById(variacion.getTramite_tipo().getId());
//        if (opt.isPresent()) {
//            TramiteTipo entityTT = opt.get();
//            Variacion toSave = MapperUtils.entityFromDto(variacion, Variacion.class);
//            toSave.setTramite_tipos(entityTT);
//            toSave = variacionRepository.save(toSave);
//            VariacionDTO variacionDTO = MapperUtils.DtoFromEntity(toSave, VariacionDTO.class);
//            variacionDTO.adjuntarTipoTramite(variacion.getTramite_tipo());
//            return Optional.of(variacionDTO);
//        } else {
//            return Optional.empty();
//        }
//    }

    @Override
    @Transactional
    public Optional<Variacion> update(Variacion variacion, Long id) {
        if (variacionRepository.findById(id).isPresent()) {
            return Optional.ofNullable(variacionRepository.save(variacion));
        }
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
