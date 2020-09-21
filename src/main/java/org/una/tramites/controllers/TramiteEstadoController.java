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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.una.tramites.dto.TramiteEstadoDTO;
import org.una.tramites.entities.TramiteEstado;
import org.una.tramites.services.ITramiteEstadoService;
import org.una.tramites.utils.MapperUtils;

/**
 *
 * @author LordLalo
 */
@RestController
@RequestMapping("/tramites_estados")
@Api(tags = {"Tramites_Estados"})
public class TramiteEstadoController {

    @Autowired
    private ITramiteEstadoService tramiteEstadoService;

    @GetMapping()
    @ApiOperation(value = "Obtiene una lista de todos los tramites qur poseen estados", response = TramiteEstadoDTO.class, responseContainer = "List", tags = "Tramites_Estados")
    public @ResponseBody
    @PreAuthorize("hasAuthority('TRAMITE_ESTADO_CONSULTAR_TODO')")
    ResponseEntity<?> findAll() {
        try {
            Optional<List<TramiteEstado>> result = tramiteEstadoService.findAll();
            if (result.isPresent()) {
                List<TramiteEstadoDTO> resultDto = MapperUtils.DtoListFromEntityList(result.get(), TramiteEstadoDTO.class);
                return new ResponseEntity<>(resultDto, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('TRAMITE_ESTADO_CONSULTAR')")
    @ApiOperation(value = "Obtiene un tipo de tramite a travez de su identificador unico", response = TramiteEstadoDTO.class, tags = "Tramites_Estados")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {

            Optional<TramiteEstado> tramiteFound = tramiteEstadoService.findById(id);
            if (tramiteFound.isPresent()) {
                TramiteEstadoDTO tramiteDTO = MapperUtils.DtoFromEntity(tramiteFound.get(), TramiteEstadoDTO.class);
                return new ResponseEntity<>(tramiteDTO, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/")
    @ResponseBody
    @PreAuthorize("hasAuthority('TRAMITE_ESTADO_CREAR')")
    public ResponseEntity<?> create(@RequestBody TramiteEstado tramite) {
        try {
            TramiteEstado usuarioCreated = tramiteEstadoService.create(tramite);
            TramiteEstadoDTO usuarioDto = MapperUtils.DtoFromEntity(usuarioCreated, TramiteEstadoDTO.class);
            return new ResponseEntity<>(usuarioDto, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    @ResponseBody
    @PreAuthorize("hasAuthority('TRAMITE_ESTADO_MODIFICAR')")
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody TramiteEstado traModified) {
        try {
            Optional<TramiteEstado> traUpdated = tramiteEstadoService.update(traModified, id);
            if (traUpdated.isPresent()) {
                TramiteEstadoDTO traDto = MapperUtils.DtoFromEntity(traUpdated.get(), TramiteEstadoDTO.class);
                return new ResponseEntity<>(traDto, HttpStatus.OK);

            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('TRAMITE_ESTADO_ELIMINAR')")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        try {
            tramiteEstadoService.delete(id);
            if (findById(id).getStatusCode() == HttpStatus.NO_CONTENT) {
                return new ResponseEntity<>(HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/")
     @PreAuthorize("hasAuthority('TRAMITE_ESTADO_ELIMINAR')")
    public ResponseEntity<?> deleteAll() {
        try {
            tramiteEstadoService.deleteAll();
            if (findAll().getStatusCode() == HttpStatus.NO_CONTENT) {
                return new ResponseEntity<>(HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
