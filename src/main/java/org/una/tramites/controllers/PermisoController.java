/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tramites.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.una.tramites.dto.PermisoDTO;
import org.una.tramites.entities.Permiso;
import org.una.tramites.services.IPermisoService;
import org.una.tramites.utils.MapperUtils;

@RestController
@RequestMapping("/permiso")
@Api(tags = {"Permisos"})
public class PermisoController {

    @Autowired
    private IPermisoService permisoService;

    @GetMapping("/{id}")
    @ApiOperation(value = "Obtiene un permiso de acuerdo al id", response = PermisoDTO.class, tags = "Permisos")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {
            Optional<Permiso> permisoFound = permisoService.findById(id);
            if (permisoFound.isPresent()) {
                PermisoDTO permisoDTO = MapperUtils.DtoFromEntity(permisoFound.get(), PermisoDTO.class);
                return new ResponseEntity<>(permisoDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/estado")
    @ResponseBody
    @ApiOperation(value = "Obtiene una lista de los estados", response = PermisoDTO.class, responseContainer = "List", tags = "Permisos")
    public ResponseEntity<?> findByEstado(@PathVariable(value = "Estado") boolean estado) {
        try {
            Optional<List<Permiso>> result = permisoService.findByEstado(estado);
            if (result.isPresent()) {
                List<PermisoDTO> usuariosDTO = MapperUtils.DtoListFromEntityList(result.get(), PermisoDTO.class);
                return new ResponseEntity<>(usuariosDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/findByFechaRegistroBetween")
    @ResponseBody
    @ApiOperation(value = "Obtiene un rango de fechas", response = PermisoDTO.class, responseContainer = "List", tags = "Permisos")
    public ResponseEntity<?> findByFechaRegistroBetween(@PathVariable(value = "Date") Date strDate, @PathVariable(value = "endDate") Date endDate) {
        try {
            Optional<List<Permiso>> result = permisoService.findByFechaRegistroBetween(strDate, endDate);
            if (result.isPresent()) {
                List<PermisoDTO> usuariosDTO = MapperUtils.DtoListFromEntityList(result.get(), PermisoDTO.class);
                return new ResponseEntity<>(usuariosDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/creat")
    @ResponseBody
    public ResponseEntity<?> create(@RequestBody Permiso permiso) {
        try {
            Permiso permisoCreated = permisoService.create(permiso);
            PermisoDTO permisoDTO = MapperUtils.DtoFromEntity(permisoCreated, PermisoDTO.class);
            return new ResponseEntity<>(permisoDTO, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/actualizar")
    @ResponseBody
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody Permiso permisoModified) {
        try {
            Optional<Permiso> permisoUpdated = permisoService.update(permisoModified, id);
            if (permisoUpdated.isPresent()) {
                PermisoDTO permisoDto = MapperUtils.DtoFromEntity(permisoUpdated.get(), PermisoDTO.class);
                return new ResponseEntity<>(permisoDto, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
   
}
