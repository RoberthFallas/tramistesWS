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
import org.una.tramites.dto.TramiteEstadoDTO;
import org.una.tramites.entities.TramiteEstado;
import org.una.tramites.repositories.ITramiteEstadoRepository;
import org.una.tramites.utils.MapperUtils;

/**
 *
 * @author LordLalo
 */
@Service
public class TramiteEstadoServiceImplementation implements ITramiteEstadoService {

    @Autowired
    private ITramiteEstadoRepository tramiteEstadoRepository;

    private Optional<List<TramiteEstadoDTO>> findList(List<TramiteEstado> list) {
        if (list != null) {
            List<TramiteEstadoDTO> usuariosDTO = MapperUtils.DtoListFromEntityList(list, TramiteEstadoDTO.class);
            return Optional.ofNullable(usuariosDTO);
        } else {
            return null;
        }
    }

    private Optional<List<TramiteEstadoDTO>> findList(Optional<List<TramiteEstado>> list) {
        if (list.isPresent()) {
            return findList(list.get());
        } else {
            return null;
        }
    }

    private Optional<TramiteEstadoDTO> oneToDto(Optional<TramiteEstado> one) {
        if (one.isPresent()) {
            TramiteEstadoDTO requisitoDTO = MapperUtils.DtoFromEntity(one.get(), TramiteEstadoDTO.class);
            return Optional.ofNullable(requisitoDTO);
        } else {
            return null;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<TramiteEstadoDTO> findById(Long id) {
        return oneToDto(tramiteEstadoRepository.findById(id));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<TramiteEstadoDTO>> findAll() {
        return findList(tramiteEstadoRepository.findAll());
    }

    @Override
    @Transactional
    public TramiteEstadoDTO create(TramiteEstadoDTO tramiteEstadoDTO) {
        TramiteEstado tramiteEstado = MapperUtils.EntityFromDto(tramiteEstadoDTO,TramiteEstado.class);
        tramiteEstado = tramiteEstadoRepository.save(tramiteEstado);
        return MapperUtils.DtoFromEntity(tramiteEstado,TramiteEstadoDTO.class);
    }

    @Override
    @Transactional
    public Optional<TramiteEstadoDTO> update(TramiteEstadoDTO tramiteEstadoDTO, Long id) {

        if (tramiteEstadoRepository.findById(id).isPresent()) {
            TramiteEstado tramiteEstado = MapperUtils.EntityFromDto(tramiteEstadoDTO, TramiteEstado.class);
            tramiteEstado = tramiteEstadoRepository.save(tramiteEstado);
            return Optional.ofNullable(MapperUtils.DtoFromEntity(tramiteEstado, TramiteEstadoDTO.class));
        } else {
            return null;
        }
    }

    @Override
    public void delete(Long id) {
        tramiteEstadoRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        tramiteEstadoRepository.deleteAll();
    }

}
