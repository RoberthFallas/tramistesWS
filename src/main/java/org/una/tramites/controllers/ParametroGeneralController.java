/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tramites.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.una.tramites.dto.ParametroGeneralDTO;
import org.una.tramites.services.IParametroGeneralService;

/**
 *
 * @author LordLalo
 */
@RestController
@RequestMapping("/parametrosGenerales")
@Api(tags = {"ParametrosGenerales"})
public class ParametroGeneralController {

    @Autowired
    private IParametroGeneralService parametroGeneralService;

    @GetMapping()
    @ApiOperation(value = "Obtiene una lista de todos los parametros", response = ParametroGeneralDTO.class, responseContainer = "List", tags = "ParametrosGenerales")
    @ResponseBody
    @PreAuthorize("hasAuthority('PARAMETRO_GENERAL_CONSULTAR_TODO')")
    public ResponseEntity<?> findAll() {
        try {
            return new ResponseEntity(parametroGeneralService.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Obtiene un departamento a travez de su identificador unico", response = ParametroGeneralDTO.class, tags = "ParametrosGenerales")
    @PreAuthorize("hasAuthority('PARAMETRO_GENERAL_CONSULTAR')")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {
            return new ResponseEntity(parametroGeneralService.findById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/")
    @ResponseBody
    @PreAuthorize("hasAuthority('PARAMETRO_GENERAL_CREAR')")
      public ResponseEntity<?> create(@Valid @RequestBody ParametroGeneralDTO parametroGeneralDTO, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            try {
                return new ResponseEntity(parametroGeneralService.create(parametroGeneralDTO), HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity("MENSAJE_VERIFICAR_INFORMACION", HttpStatus.BAD_REQUEST);
        }
    }


    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('PARAMETRO_GENERAL_ELIMINAR')")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        try {
            parametroGeneralService.delete(id);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/")
    @PreAuthorize("hasAuthority('PARAMETRO_GENERAL_ELIMINAR_TODO')")
    public ResponseEntity<?> deleteAll() {
        try {
            parametroGeneralService.deleteAll();
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
