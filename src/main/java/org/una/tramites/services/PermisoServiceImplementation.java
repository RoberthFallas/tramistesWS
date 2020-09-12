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
import org.una.tramites.entities.Permiso;
import org.una.tramites.repositories.IPermisoRepository;

/**
 *
 * @author LordLalo
 */
@Service
public class PermisoServiceImplementation implements IPermisoService {

    @Autowired
    private IPermisoRepository permisoRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<Permiso> findById(Long id) {
        return permisoRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<Permiso>> findByEstado(boolean estado) {
        return Optional.ofNullable(permisoRepository.findByEstado(estado));

    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<Permiso>> findByFechaRegistroBetween(Date startDate, Date endDate) {
        return Optional.ofNullable(permisoRepository.findByFechaRegistroBetween(startDate, endDate));
    }

    @Override
    @Transactional
    public Permiso create(Permiso permiso) {
        return permisoRepository.save(permiso);
   //   return Optional.ofNullable(permisoRepository.save(permiso));
    }

    @Override
    @Transactional
    public Optional<Permiso> update(Permiso permiso, Long id) {
        if (permisoRepository.findById(id).isPresent()) {
            return Optional.ofNullable(permisoRepository.save(permiso));
        } else {
            return null;
        }
    }

    @Override
    @Transactional
    public void delete(Long id) {
        permisoRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteAll() {
        permisoRepository.deleteAll();
    }

    @Override
    public Long countByEstado(boolean estado) {
        return permisoRepository.countByEstado(estado);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Permiso> findByCodigo(String codigoPermiso) {
        return Optional.ofNullable(permisoRepository.findByCodigo(codigoPermiso));
    }

}
