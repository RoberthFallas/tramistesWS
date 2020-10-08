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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.una.tramites.dto.TramiteCambioEstadoDTO;
import org.una.tramites.services.ITramiteCambioEstadoService;

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
    @PreAuthorize("hasAuthority('TRAMITE_CAMBIO_ESTADO_CONSULTAR_TODO')")
    @ApiOperation(value = "Obtiene una lista de todos los Usuarios", response = TramiteCambioEstadoDTO.class, responseContainer = "List", tags = "Tramite_Cambio_Estado")
    public @ResponseBody
    ResponseEntity<?> findAll() {
        try {
            return new ResponseEntity(tramiteCambioEstadoService.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Obtiene el tramite a travez de su identificador unico", response = TramiteCambioEstadoDTO.class, tags = "Tramite_Cambio_Estado")
    @PreAuthorize("hasAuthority('TRAMITE_CAMBIO_ESTADO_CONSULTAR')")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {
            return new ResponseEntity(tramiteCambioEstadoService.findById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    @ResponseBody
    @PreAuthorize("hasAuthority('TRAMITE_CAMBIO_ESTADO_MODIFICAR')")
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @Valid @RequestBody TramiteCambioEstadoDTO tramiteCambioEstadoDTO, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            try {
                Optional<TramiteCambioEstadoDTO> tramiteUpdated = tramiteCambioEstadoService.update(tramiteCambioEstadoDTO, id);
                if (tramiteUpdated.isPresent()) {
                    return new ResponseEntity(tramiteUpdated, HttpStatus.OK);
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

    @PutMapping("/modificarEstado/{idTramite}/{idTramiteEstado}")
    @PreAuthorize("hasAuthority('TRAMITE_ESTADO_MODIFICAR_ESTADO')")
    @ResponseBody
    public ResponseEntity<?> modificarEstado(@PathVariable(value = "idTramite") Long idTramite, @PathVariable(value = "idTramiteEstado") Long idTramiteEstado) {

        try {
            Optional<TramiteCambioEstadoDTO> tOptional = tramiteCambioEstadoService.modificarEstado(idTramite, idTramiteEstado);
            if (tOptional.isPresent()) {
                return new ResponseEntity(tOptional, HttpStatus.OK);
            } else {
                return new ResponseEntity(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PutMapping("/actulizarTramiteNuevo/{idTramite}/{idTramiteEstado}")
    @PreAuthorize("hasAuthority('TRAMITE_ESTADO_MODIFICAR_ESTADO')")
    @ResponseBody
    public ResponseEntity<?> actulizarTramiteNuevo(@PathVariable(value = "idTramite") Long idTramite, @PathVariable(value = "idTramiteEstado") Long idTramiteEstado) {

        try {
            Optional<TramiteCambioEstadoDTO> tOptional = tramiteCambioEstadoService.actualizarTramiteNuevo(idTramite, idTramiteEstado);
            if (tOptional.isPresent()) {
                return new ResponseEntity(tOptional, HttpStatus.OK);
            } else {
                return new ResponseEntity(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('TRAMITE_CAMBIO_ESTADO_ELIMINAR')")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        try {
            tramiteCambioEstadoService.delete(id);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/")
    @PreAuthorize("hasAuthority('TRAMITE_CAMBIO_ESTADO_ELIMINAR_TODO')")
    public ResponseEntity<?> deleteAll() {
        try {
            tramiteCambioEstadoService.deleteAll();
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
