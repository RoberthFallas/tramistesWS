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
import javax.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;

@RestController
@RequestMapping("/requisito_presentado")
@Api(tags = {"Requisitos Presentados"})
public class RequisitoPresentadoController {

    @Autowired
    private IRequisitoPresentadoService requisitospresentadosService;

    @GetMapping()
    @ApiOperation(value = "Obtiene una lista de todos los requisitos presentados", response = RequisitoPresentadoDTO.class, responseContainer = "List", tags = "Requisitos Presentados")
    @ResponseBody
    @PreAuthorize("hasAuthority('REQUISITO_PRESENTADO_CONSULTAR_TODO')")
    public ResponseEntity<?> findAll() {
        try {
            return new ResponseEntity(requisitospresentadosService.findAll(),HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Obtiene la lista de requisitos presentados a travez de su identificador unico", response = RequisitoPresentadoDTO.class, tags = "Requisitos Presentados")
    @PreAuthorize("hasAuthority('REQUISITO_PRESENTADO_CONSULTAR')")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {
return new ResponseEntity(requisitospresentadosService.findById(id),HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/")
    @ResponseBody
    @PreAuthorize("hasAuthority('USUARIO_CREAR')")
    public ResponseEntity<?> create(@RequestBody RequisitoPresentadoDTO requisitoPresentadoDTO,BindingResult bindingResult) {
             if (!bindingResult.hasErrors()) {
            try {
                return new ResponseEntity(requisitospresentadosService.create(requisitoPresentadoDTO), HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity("MENSAJE_VERIFICAR_INFORMACION", HttpStatus.BAD_REQUEST);
        }

    }
    @PutMapping("/{id}")
    @ResponseBody
        public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @Valid @RequestBody RequisitoPresentadoDTO requisitoPresentadoDTO, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            try {
                Optional<RequisitoPresentadoDTO> requisitoUpdated = requisitospresentadosService.update(requisitoPresentadoDTO, id);
                if (requisitoUpdated.isPresent()) {
                    return new ResponseEntity(requisitoUpdated, HttpStatus.OK);
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

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('REQUISITO_PRESENTADO_ELIMINAR')")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        try {
            requisitospresentadosService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/")
    @PreAuthorize("hasAuthority('REQUISITO_PRESENTADO_ELIMINAR_TODO')")
    public ResponseEntity<?> deleteAll() {
        try {
            requisitospresentadosService.deleteAll();
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
