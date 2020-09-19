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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.una.tramites.dto.ParametroGeneralDto;
import org.una.tramites.entities.ParametroGeneral;
import org.una.tramites.services.IParametroGeneralService;
import org.una.tramites.utils.MapperUtils;

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
    @ApiOperation(value = "Obtiene una lista de todos los parametros", response = ParametroGeneralDto.class, responseContainer = "List", tags = "ParametrosGenerales")
    public @ResponseBody
    ResponseEntity<?> findAll() {
        try {
            Optional<List<ParametroGeneral>> result = parametroGeneralService.findAll();
            if (result.isPresent()) {
                List<ParametroGeneralDto> parametroGeneralDtos = MapperUtils.DtoListFromEntityList(result.get(), ParametroGeneralDto.class);
                return new ResponseEntity<>(parametroGeneralDtos, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Obtiene un departamento a travez de su identificador unico", response = ParametroGeneralDto.class, tags = "ParametrosGenerales")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {

            Optional<ParametroGeneral> usuarioFound = parametroGeneralService.findById(id);
            if (usuarioFound.isPresent()) {
                ParametroGeneralDto parametroGeneralDto = MapperUtils.DtoFromEntity(usuarioFound.get(), ParametroGeneralDto.class);
                return new ResponseEntity<>(parametroGeneralDto, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/")
    @ResponseBody
    public ResponseEntity<?> create(@RequestBody ParametroGeneral parametroG) {
        try {
            ParametroGeneral parametroGeneralCreated = parametroGeneralService.create(parametroG);
            ParametroGeneralDto parametroGeneralDto = MapperUtils.DtoFromEntity(parametroGeneralCreated, ParametroGeneralDto.class);
            return new ResponseEntity<>(parametroGeneralDto, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        try {
            parametroGeneralService.delete(id);
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
            parametroGeneralService.deleteAll();
            if (findAll().getStatusCode() == HttpStatus.NO_CONTENT) {
                return new ResponseEntity<>(HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
