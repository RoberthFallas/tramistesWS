/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tramites.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.una.tramites.dto.TransaccionDTO;
import org.una.tramites.entities.Transaccion;

import org.una.tramites.repositories.ITransaccionRepository;
import org.una.tramites.utils.MapperUtils;

/**
 *
 * @author LordLalo
 */
@Service
public class TransaccionServiceImplementation implements ITransaccionService {

    @Autowired
    private ITransaccionRepository transaccionRepository;
    private Optional<List<TransaccionDTO>> findList(List<Transaccion> list) {
        if (list != null) {
            List<TransaccionDTO> transaccionDTO = MapperUtils.DtoListFromEntityList(list, TransaccionDTO.class);
            return Optional.ofNullable(transaccionDTO);
        } else {
            return null;
        }
    }


    private Optional<List<TransaccionDTO>> findList(Optional<List<Transaccion>> list) {
        if (list.isPresent()) {
            return findList(list.get());
        } else {
            return null;
        }
    }

    private Optional<TransaccionDTO> oneToDto(Optional<Transaccion> one) {
        if (one.isPresent()) {
            TransaccionDTO transaccionDTO = MapperUtils.DtoFromEntity(one.get(), TransaccionDTO.class);
            return Optional.ofNullable(transaccionDTO);
        } else {
            return null;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<TransaccionDTO> findById(Long id) {
        return oneToDto(transaccionRepository.findById(id));
    }
    @Override
    @Transactional
    public Optional<List<TransaccionDTO>> findByObjetoAndFechaRegistroBetween(String objeto, Date startDate, Date endDate) {
       return findList(transaccionRepository.findByObjetoAndFechaRegistroBetween(objeto, startDate, endDate));
    }

    @Override
    public Optional<List<TransaccionDTO>> findByFechaRegistroBetween(Date startDate, Date endDate) {
      return findList(transaccionRepository.findByFechaRegistroBetween(startDate, endDate));
    }

     @Override
    @Transactional
    public TransaccionDTO create(TransaccionDTO transaccionDTO) {
     Transaccion transaccion=MapperUtils.EntityFromDto(transaccionDTO,Transaccion.class);
     transaccion=transaccionRepository.save(transaccion);
     return MapperUtils.DtoFromEntity(transaccion,TransaccionDTO.class);
    }

    @Override
    public Optional<TransaccionDTO> update(TransaccionDTO transaccionDTO,Long id) {
               if (transaccionRepository.findById(id).isPresent()) {
         
           Transaccion transaccion = MapperUtils.EntityFromDto(transaccionDTO, Transaccion.class);
            transaccion= transaccionRepository.save(transaccion);
            return Optional.ofNullable(MapperUtils.DtoFromEntity(transaccion, TransaccionDTO.class));
        } else {
            return null;
        } 
    }


}
