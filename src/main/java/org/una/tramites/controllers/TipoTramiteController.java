/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tramites.controllers;

import io.swagger.annotations.Api;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.una.tramites.dto.TramiteTipoDTO;
import org.una.tramites.services.ITipoTramiteService;

/**
 *
 * @author Roberth :)
 */
@RestController
@RequestMapping("/tipo_tramite")
@Api(tags = {"Tipos_Tramites"})
public class TipoTramiteController {

    @Autowired
    private ITipoTramiteService tipoTramiteService;

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/create")
    @ResponseBody
    @PreAuthorize("hasAuthority('TRAMITE_TIPO_CREAR')")
    public ResponseEntity<?> create(@RequestBody TramiteTipoDTO tipoTramite) {
        try {
            Optional result = tipoTramiteService.create(tipoTramite);
            if (result.isPresent()) {
                return new ResponseEntity<>(result.get(), HttpStatus.CREATED);
            }
            return new ResponseEntity<>("No ha sido posible realizar el cambio solicitado", HttpStatus.NOT_MODIFIED);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/update")
    @ResponseBody
    @PreAuthorize("hasAuthority('TRAMITE_TIPO_MODIFICAR')")
    public ResponseEntity<?> update(@RequestBody TramiteTipoDTO tipoTramite) {
        try {
            Optional result = tipoTramiteService.update(tipoTramite);
            if (result.isPresent()) {
                return new ResponseEntity<>(result.get(), HttpStatus.CREATED);
            }
            return new ResponseEntity<>("No ha sido posible realizar el cambio solicitado", HttpStatus.NOT_MODIFIED);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("findByEstado/{estado}")
    @ResponseBody
    @PreAuthorize("hasAuthority('TRAMITE_TIPO_CONSULTAR_ESTADO')")
    public ResponseEntity<?> findByEstado(@PathVariable(value = "estado") boolean estado) {
        try {
            Optional result = tipoTramiteService.getByEstado(estado);
            return new ResponseEntity<>(result.get(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/findByDescripcion/{descripcion}")
    @ResponseBody
    @PreAuthorize("hasAuthority('TRAMITE_TIPO_CONSULTAR_DESCRIPCION')")
    public ResponseEntity<?> findByDescripcio(@PathVariable(value = "descripcion") String descripcion) {
        try {
            Optional result = tipoTramiteService.getByDescripcion(descripcion);
            return new ResponseEntity<>(result.get(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping("/getAll")
    @ResponseBody
    @PreAuthorize("hasAuthority('TRAMITE_TIPO_CONSULTAR_TODO')")
    public ResponseEntity<?> getAll() {
        try {
            Optional result = tipoTramiteService.getAll();
            return new ResponseEntity<>(result.get(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
