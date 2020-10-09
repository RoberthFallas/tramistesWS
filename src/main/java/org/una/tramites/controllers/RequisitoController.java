package org.una.tramites.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.una.tramites.dto.RequisitoDTO;
import org.una.tramites.services.IRequisitoService;
import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequestMapping("/requisito")
@Api(tags = {"Requisitos"})
public class RequisitoController {

    @Autowired
    private IRequisitoService requisitosService;

    @GetMapping()
    @ApiOperation(value = "Obtiene una lista de todos los requisitos", response = RequisitoDTO.class, responseContainer = "List", tags = "Requisitos")
    public @ResponseBody
    @PreAuthorize("hasAuthority('REQUISITO_CONSULTAR_TODO')")
    ResponseEntity<?> findAll() {
        try {
            return new ResponseEntity(requisitosService.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Obtiene la lista de requisitos  a travez de su identificador unico", response = RequisitoDTO.class, tags = "Requisitos")
    @PreAuthorize("hasAuthority('REQUISITO_CONSULTAR')")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {
            return new ResponseEntity(requisitosService.findById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('REQUISITO_ELIMINAR')")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        try {
            requisitosService.delete(id);

            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/")
    @PreAuthorize("hasAuthority('REQUISITO_ELIMINAR_TODO')")
    public ResponseEntity<?> deleteAll() {
        try {
            requisitosService.deleteAll();
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
