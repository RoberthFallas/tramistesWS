package org.una.tramites.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.una.tramites.dto.ArchivoRelacionadoDTO;
import org.una.tramites.services.IArchivoRelacionadoService;
import java.util.Optional;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;

@RestController
@RequestMapping("/archivos_relacionados")
@Api(tags = {"Archivos Relacionados"})
public class ArchivoRelacionadoController {

    @Autowired
    private IArchivoRelacionadoService archivosrelacionadosService;

    @GetMapping()
    @ApiOperation(value = "Obtiene una lista de todos las notas", response = ArchivoRelacionadoDTO.class, responseContainer = "List", tags = "Archivos Relacionados")
    public @ResponseBody
    @PreAuthorize("hasAuthority('ARCHIVO_RELACIONADO_CONSULTAR_TODO')")
    ResponseEntity<?> findAll() {
        try {
            return new ResponseEntity(archivosrelacionadosService.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getClass(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Obtiene la lista de archivos relacionados a travez de su identificador unico", response = ArchivoRelacionadoDTO.class, tags = "Archivos Relacionados")
    @PreAuthorize("hasAuthority('ARCHIVO_RELACIONADO_CONSULTAR')")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {
            return new ResponseEntity(archivosrelacionadosService.findById(id), HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ARCHIVO_RELACIONADO_ELIMINAR')")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        try {
            archivosrelacionadosService.delete(id);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/")
    @PreAuthorize("hasAuthority('ARCHIVO_RELACIONADO_ELIMINAR_TODO')")
    public ResponseEntity<?> deleteAll() {
        try {
            archivosrelacionadosService.deleteAll();
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/")
    @ResponseBody
    @PreAuthorize("hasAuthority('ARCHIVO_RELACIONADO_CREAR')")
    public ResponseEntity<?> create(@RequestBody ArchivoRelacionadoDTO archivoRelacionadoDTO, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            try {
                return new ResponseEntity(archivosrelacionadosService.create(archivoRelacionadoDTO), HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity("MENSAJE_VERIFICAR_INFORMACION", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    @ResponseBody
    @PreAuthorize("hasAuthority('ARCHIVO_RELACIONADO_MODIFICAR')")
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id,
            @RequestBody ArchivoRelacionadoDTO archivoRelacionadoDTO, BindingResult bindingResult
    ) {
        if (!bindingResult.hasErrors()) {
            try {
                Optional<ArchivoRelacionadoDTO> usuarioUpdated = archivosrelacionadosService.update(archivoRelacionadoDTO, id);
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
}
