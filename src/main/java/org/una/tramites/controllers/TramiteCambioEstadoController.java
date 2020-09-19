/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tramites.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.una.tramites.dto.TramiteCambioEstadoDTO;
import org.una.tramites.entities.TramiteCambioEstado;
import org.una.tramites.services.ITramiteCambioEstadoService;
import org.una.tramites.utils.MapperUtils;

/**
 *
 * @author LordLalo
 */
@RestController
@RequestMapping("/tramiteCambiEstado")
@Api(tags = {"Tramite_Cambio_Estado"})
public class TramiteCambioEstadoController {

    @Autowired
    private ITramiteCambioEstadoService tramiteCambioEstadoService;

    @GetMapping()
    @ApiOperation(value = "Obtiene una lista de todos los Usuarios", response = TramiteCambioEstadoDTO.class, responseContainer = "List", tags = "Tramite_Cambio_Estado")
    public @ResponseBody
    ResponseEntity<?> findAll() {
        try {
            Optional<List<TramiteCambioEstado>> result = tramiteCambioEstadoService.findAll();
            if (result.isPresent()) {
                List<TramiteCambioEstadoDTO> usuariosDTO = MapperUtils.DtoListFromEntityList(result.get(), TramiteCambioEstadoDTO.class);
                return new ResponseEntity<>(usuariosDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Obtiene el tramite a travez de su identificador unico", response = TramiteCambioEstadoDTO.class, tags = "Tramite_Cambio_Estado")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {

            Optional<TramiteCambioEstado> tramiteCambioFound = tramiteCambioEstadoService.findById(id);
            if (tramiteCambioFound.isPresent()) {
                TramiteCambioEstadoDTO tramiteCambioEstadoDTO= MapperUtils.DtoFromEntity(tramiteCambioFound.get(), TramiteCambioEstadoDTO.class);
                return new ResponseEntity<>(tramiteCambioEstadoDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody TramiteCambioEstado tramiteModified) {
        try {
            Optional<TramiteCambioEstado> usuarioUpdated = tramiteCambioEstadoService.update(tramiteModified, id);
            if (usuarioUpdated.isPresent()) {
                TramiteCambioEstadoDTO tramiteCambioEstadoDTO = MapperUtils.DtoFromEntity(usuarioUpdated.get(), TramiteCambioEstadoDTO.class);
                return new ResponseEntity<>(tramiteCambioEstadoDTO, HttpStatus.OK);

            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        try {
            tramiteCambioEstadoService.delete(id);
            if (findById(id).getStatusCode() == HttpStatus.NO_CONTENT) {
                return new ResponseEntity<>(HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/")
    public ResponseEntity<?> deleteAll() {
        try {
            tramiteCambioEstadoService.deleteAll();
            if (findAll().getStatusCode() == HttpStatus.NO_CONTENT) {
                return new ResponseEntity<>(HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
