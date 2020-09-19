
package org.una.tramites.controllers;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.una.tramites.dto.RequisitoPresentadoDTO;
import org.una.tramites.entities.RequisitoPresentado;
import org.una.tramites.services.IRequisitoPresentadoService;
import org.una.tramites.utils.MapperUtils;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/requisito_presentado")
@Api(tags = {"Requisitos Presentados"})
public class RequisitoPresentadoController {
    @Autowired
    private IRequisitoPresentadoService requisitospresentadosService;

    @GetMapping()
    @ApiOperation(value = "Obtiene una lista de todos los requisitos presentados", response =  RequisitoPresentadoDTO.class, responseContainer = "List", tags = "Requisitos Presentados")
    public @ResponseBody
    ResponseEntity<?> findAll() {
        try {
            Optional<List<RequisitoPresentado>> result = requisitospresentadosService.findAll();
            if (result.isPresent()) {
                List< RequisitoPresentadoDTO> resultDto = MapperUtils.DtoListFromEntityList(result.get(),  RequisitoPresentadoDTO.class);
                return new ResponseEntity<>(resultDto, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Obtiene la lista de requisitos presentados a travez de su identificador unico", response =  RequisitoPresentadoDTO.class, tags = "Requisitos Presentados")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {

            Optional<RequisitoPresentado> requisitopresentadoFound = requisitospresentadosService.findById(id);
            if (requisitopresentadoFound.isPresent()) {
                RequisitoPresentadoDTO requisitopresentadoDto = MapperUtils.DtoFromEntity(requisitopresentadoFound.get(), RequisitoPresentadoDTO.class);
                return new ResponseEntity<>(requisitopresentadoDto, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        try {
            requisitospresentadosService.delete(id);
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
            requisitospresentadosService.deleteAll();
            if (findAll().getStatusCode() == HttpStatus.NO_CONTENT) {
                return new ResponseEntity<>(HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
