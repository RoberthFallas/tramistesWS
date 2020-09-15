/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tramites.controllers;

import io.swagger.annotations.Api;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.una.tramites.dto.TramiteTipoDTO;
import org.una.tramites.services.ITipoTramiteService;

/**
 *
 * @author Roberth :)
 */
@RestController
@RequestMapping("/tipo_tramite")
@Api(tags = {"Tipos_Tramites"})
public class TipoTramiteController {

    @Autowired
    private ITipoTramiteService tipoTramiteService;

    @PostMapping("/create")
    @ResponseBody
    public ResponseEntity<?> create(@RequestBody TramiteTipoDTO tipoTramite) {
        try {
            Optional result = tipoTramiteService.create(tipoTramite);
            if (result.isPresent()) {
                return new ResponseEntity<>(result.get(), HttpStatus.CREATED);
            }
            return new ResponseEntity<>("No ha sido posible realizar el cambio solicitado", HttpStatus.NOT_MODIFIED);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
