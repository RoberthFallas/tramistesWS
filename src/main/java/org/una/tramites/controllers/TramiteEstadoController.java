/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tramites.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.una.tramites.dto.TramiteEstadoDTO;
import org.una.tramites.services.ITramiteEstadoService;

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
    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping()
    @ApiOperation(value = "Obtiene una lista de todos los tramites qur poseen estados", response = TramiteEstadoDTO.class, responseContainer = "List", tags = "Tramites_Estados")
    public @ResponseBody
    @PreAuthorize("hasAuthority('TRAMITE_ESTADO_CONSULTAR_TODO')")
    ResponseEntity<?> findAll() {
        try {
            return new ResponseEntity(tramiteEstadoService.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('TRAMITE_ESTADO_CONSULTAR')")
    @ApiOperation(value = "Obtiene un tipo de tramite a travez de su identificador unico", response = TramiteEstadoDTO.class, tags = "Tramites_Estados")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {
            return new ResponseEntity(tramiteEstadoService.findById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/")
    @ResponseBody
    @PreAuthorize("hasAuthority('TRAMITE_ESTADO_CREAR')")
    public ResponseEntity<?> create(@RequestBody TramiteEstadoDTO tramiteEstadoDTO, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            try {
                return new ResponseEntity(tramiteEstadoService.create(tramiteEstadoDTO), HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity("MENSAJE_VERIFICAR_INFORMACION", HttpStatus.BAD_REQUEST);
        }

    }

    @PutMapping("/{id}")
    @ResponseBody
    @PreAuthorize("hasAuthority('TRAMITE_ESTADO_MODIFICAR')")
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @Valid @RequestBody TramiteEstadoDTO tramiteEstadoDTO, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            try {
                Optional<TramiteEstadoDTO> usuarioUpdated = tramiteEstadoService.update(tramiteEstadoDTO, id);
                if (usuarioUpdated.isPresent()) {
                    return new ResponseEntity(usuarioUpdated, HttpStatus.OK);
                } else {
                    return new ResponseEntity(HttpStatus.NOT_FOUND);
                }
            } catch (Exception e) {
                return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity("MENSAJE_VERIFICAR_INFORMACION", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/modificarEstado/{id}/{estado}")
    @PreAuthorize("hasAuthority('TRAMITE_ESTADO_MODIFICAR_ESTADO')")
    @ResponseBody
    public ResponseEntity<?> modificarEstado(@PathVariable(value = "id") Long id, @PathVariable(value = "estado") String estado) {

        try {
            Optional<TramiteEstadoDTO> usuarioUpdated = tramiteEstadoService.modificarEstado(estado, id);
            if (usuarioUpdated.isPresent()) {
                return new ResponseEntity(usuarioUpdated, HttpStatus.OK);
            } else {
                return new ResponseEntity(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
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
