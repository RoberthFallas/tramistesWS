package org.una.tramites.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.una.tramites.dto.TramiteRegistradoDTO;
import org.una.tramites.entities.TramiteRegistrado;
import org.una.tramites.utils.MapperUtils;

import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.una.tramites.services.ITramiteRegistradoService;

@RestController
@RequestMapping("/tramites_registrados")
@Api(tags = {"Tramites Registrados"})
public class TramiteRegistradoController {

    @Autowired
    private ITramiteRegistradoService tramiteRegistradoService;
    
    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping()
    @ApiOperation(value = "Obtiene una lista de todos los tramites registrados", response = TramiteRegistradoDTO.class, responseContainer = "List", tags = "Tramites Registrados")
    public @ResponseBody
    @PreAuthorize("hasAuthority('TRAMITE_CONSULTAR_TODO')")
    ResponseEntity<?> findAll() {
        try {
            return new ResponseEntity(tramiteRegistradoService.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('TRAMITE_CONSULTAR')")
    @ApiOperation(value = "Obtiene un tramite registrado a travez de su identificador unico", response = TramiteRegistradoDTO.class, tags = "Tramites Registrados")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {
            return new ResponseEntity(tramiteRegistradoService.findById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/")
    @ResponseBody
    @PreAuthorize("hasAuthority('TRAMITE_REGISTRAR')")
      public ResponseEntity<?> create(@Valid @RequestBody TramiteRegistradoDTO tramiteRegistradoDTO, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            try {
                return new ResponseEntity(tramiteRegistradoService.create(tramiteRegistradoDTO), HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity("MENSAJE_VERIFICAR_INFORMACION", HttpStatus.BAD_REQUEST);
        }
    }


    @PutMapping("/{id}")
    @ResponseBody
    @PreAuthorize("hasAuthority('TRAMITE_MODIFICAR')")
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @Valid @RequestBody TramiteRegistradoDTO tramiteRegistradoDTO, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            try {
                Optional<TramiteRegistradoDTO> tramiteUpdated =tramiteRegistradoService.update(tramiteRegistradoDTO, id);
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

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('TRAMITE_ELIMINAR')")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        try {
            tramiteRegistradoService.delete(id);

            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/")
    @PreAuthorize("hasAuthority('TRAMITE_ELIMINAR_TODO')")
    public ResponseEntity<?> deleteAll() {
        try {
            tramiteRegistradoService.deleteAll();

            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
