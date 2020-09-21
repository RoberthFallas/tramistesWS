package org.una.tramites.controllers;

import io.swagger.annotations.Api;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.una.tramites.dto.TransaccionDTO;
import org.una.tramites.entities.Transaccion;
import org.una.tramites.services.ITransaccionService;
import org.una.tramites.utils.MapperUtils;

/**
 *
 * @author LordLalo
 */
@RestController
@RequestMapping("/transacciones")
@Api(tags = {"Transacciones"})
public class TransaccionController {

    @Autowired
    private ITransaccionService transaccionService;

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('TRANSACCION_CONSULTAR')")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {
            Optional<Transaccion> transaccionFound = transaccionService.findById(id);
            if (transaccionFound.isPresent()) {
                TransaccionDTO tranDto = MapperUtils.DtoFromEntity(transaccionFound.get(), TransaccionDTO.class);
                return new ResponseEntity<>(tranDto, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/")
    @ResponseBody
    @PreAuthorize("hasAuthority('TRANSACCION_CREAR')")
    public ResponseEntity<?> create(@RequestBody Transaccion transaccion) {
        try {
            Transaccion tranCreated = transaccionService.create(transaccion);
            TransaccionDTO tranDto = MapperUtils.DtoFromEntity(tranCreated, TransaccionDTO.class);
            return new ResponseEntity<>(tranDto, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
