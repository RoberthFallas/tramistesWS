package org.una.tramites.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.una.tramites.dto.RequisitoDTO;
import org.una.tramites.entities.Requisito;
import org.una.tramites.services.IRequisitoService;
import org.una.tramites.utils.MapperUtils;

import java.util.List;
import java.util.Optional;
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
            Optional<List<Requisito>> result = requisitosService.findAll();
            if (result.isPresent()) {
                List< RequisitoDTO> resultDto = MapperUtils.DtoListFromEntityList(result.get(), RequisitoDTO.class);
                return new ResponseEntity<>(resultDto, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Obtiene la lista de requisitos  a travez de su identificador unico", response = RequisitoDTO.class, tags = "Requisitos")
    @PreAuthorize("hasAuthority('REQUISITO_CONSULTAR')")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {

            Optional<Requisito> requisitoFound = requisitosService.findById(id);
            if (requisitoFound.isPresent()) {
                RequisitoDTO requisitoDto = MapperUtils.DtoFromEntity(requisitoFound.get(), RequisitoDTO.class);
                return new ResponseEntity<>(requisitoDto, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('REQUISITO_ELIMINAR')")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        try {
            requisitosService.delete(id);
            if (findById(id).getStatusCode() == HttpStatus.NO_CONTENT) {
                return new ResponseEntity<>(HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/")
    @PreAuthorize("hasAuthority('REQUISITO_ELIMINAR_TODO')")
    public ResponseEntity<?> deleteAll() {
        try {
            requisitosService.deleteAll();
            if (findAll().getStatusCode() == HttpStatus.NO_CONTENT) {
                return new ResponseEntity<>(HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
