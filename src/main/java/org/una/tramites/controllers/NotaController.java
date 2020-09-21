package org.una.tramites.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.una.tramites.dto.NotaDTO;
import org.una.tramites.entities.Nota;
import org.una.tramites.services.INotaService;
import org.una.tramites.utils.MapperUtils;

import java.util.List;
import java.util.Optional;
import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequestMapping("/notas")
@Api(tags = {"Notas"})
public class NotaController {

    @Autowired
    private INotaService notaService;

    @GetMapping()
    @ApiOperation(value = "Obtiene una lista de todos las notas", response = NotaDTO.class, responseContainer = "List", tags = "Notas")
    public @ResponseBody
    @PreAuthorize("hasAuthority('NOTA_CONSULTAR_TODO')")
    ResponseEntity<?> findAll() {
        try {
            Optional<List<Nota>> result = notaService.findAll();
            if (result.isPresent()) {
                List<NotaDTO> resultDto = MapperUtils.DtoListFromEntityList(result.get(), NotaDTO.class);
                return new ResponseEntity<>(resultDto, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Obtiene la lista de notas a travez de su identificador unico", response = NotaDTO.class, tags = "Notas")
    @PreAuthorize("hasAuthority('NOTA_CONSULTAR')")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {

            Optional<Nota> notaFound = notaService.findById(id);
            if (notaFound.isPresent()) {
                NotaDTO notaDTO = MapperUtils.DtoFromEntity(notaFound.get(), NotaDTO.class);
                return new ResponseEntity<>(notaDTO, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/")
    @ResponseBody
    @PreAuthorize("hasAuthority('NOTA_CREAR')")
    public ResponseEntity<?> create(@RequestBody Nota nota) {
        try {
            Nota notaCreated = notaService.create(nota);
            NotaDTO notaDto = MapperUtils.DtoFromEntity(notaCreated, NotaDTO.class);
            return new ResponseEntity<>(notaDto, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    @ResponseBody
    @PreAuthorize("hasAuthority('NOTA_MODIFICAR')")
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody Nota notaModified) {
        try {
            Optional<Nota> notaUpdated = notaService.update(notaModified, id);
            if (notaUpdated.isPresent()) {
                NotaDTO usuarioDto = MapperUtils.DtoFromEntity(notaUpdated.get(), NotaDTO.class);
                return new ResponseEntity<>(usuarioDto, HttpStatus.OK);

            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('NOTA_ELIMINAR')")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        try {
            notaService.delete(id);
            if (findById(id).getStatusCode() == HttpStatus.NO_CONTENT) {
                return new ResponseEntity<>(HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/")
     @PreAuthorize("hasAuthority('NOTA_ELIMINAR_TODO')")
    public ResponseEntity<?> deleteAll() {
        try {
            notaService.deleteAll();
            if (findAll().getStatusCode() == HttpStatus.NO_CONTENT) {
                return new ResponseEntity<>(HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
