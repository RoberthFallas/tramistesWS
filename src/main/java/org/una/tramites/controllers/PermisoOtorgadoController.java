/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tramites.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.una.tramites.dto.PermisoOtorgadoDTO;
import org.una.tramites.entities.PermisoOtorgado;
import org.una.tramites.services.IPermisoOtorgadoService;
import org.una.tramites.utils.MapperUtils;



/**
 *
 * @author LordLalo
 */
@RestController
@RequestMapping("/permisoOtorgado")
@Api(tags = {"PermisoOtorgado"})
public class PermisoOtorgadoController {
    @Autowired
    private IPermisoOtorgadoService iPermisoOtorgadoService;
   
   @Autowired
    private IPermisoOtorgadoService permisoOtorgadoService;

    @GetMapping("/{id}")
    @ApiOperation(value = "Obtiene un permiso otorgado mediante id", response = PermisoOtorgadoDTO.class, tags = "PermisoOtorgado")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {
            Optional<PermisoOtorgado> permisoOtorgadoFound = permisoOtorgadoService.findById(id);
            if (permisoOtorgadoFound.isPresent()) {
                PermisoOtorgadoDTO permisoOtorgadoDTO = MapperUtils.DtoFromEntity(permisoOtorgadoFound.get(), PermisoOtorgadoDTO.class);
                return new ResponseEntity<>(permisoOtorgadoDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/permisosUsuario")
    @ResponseBody
    @ApiOperation(value = "Obtiene una lista de los estados", response = PermisoOtorgadoDTO.class, responseContainer = "List", tags = "PermisoOtorgado")
    public ResponseEntity<?> findByUsuarioId(@PathVariable(value = "usuarioId") Long usuarioId) {
        try {
            Optional<List<PermisoOtorgado>> result = permisoOtorgadoService.findByUsuarioId(usuarioId);
            if (result.isPresent()) {
                List<PermisoOtorgadoDTO> usuariosOtorgadoDTOs = MapperUtils.DtoListFromEntityList(result.get(), PermisoOtorgadoDTO.class);
                return new ResponseEntity<>(usuariosOtorgadoDTOs, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/permisosOtorgadoid")
    @ResponseBody
    @ApiOperation(value = "Obtiene una lista de los permios otorgados", response = PermisoOtorgadoDTO.class, responseContainer = "List", tags = "PermisoOtorgado")
    public ResponseEntity<?> findByPermisoId(@PathVariable(value = "permisoId") Long permisoId) {
        try {
            Optional<List<PermisoOtorgado>> result = permisoOtorgadoService.findByPermisoId(permisoId);
            if (result.isPresent()) {
                List<PermisoOtorgadoDTO> usuariosOtorgadoDTOs = MapperUtils.DtoListFromEntityList(result.get(), PermisoOtorgadoDTO.class);
                return new ResponseEntity<>(usuariosOtorgadoDTOs, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/findByUsuarioIdAndEstado")
    @ResponseBody
    @ApiOperation(value = "Obtiene una lista de permisos ortogados mediante estado y usuario", response = PermisoOtorgadoDTO.class, responseContainer = "List", tags = "PermisoOtorgado")
    public ResponseEntity<?> findByUsuarioIdAndEstado(@PathVariable(value = "usuarioId") Long usuarioId, @PathVariable(value = "estado") boolean estado) {
        try {
            Optional<List<PermisoOtorgado>> result = permisoOtorgadoService.findByUsuarioIdAndEstado(usuarioId, estado);
            if (result.isPresent()) {
                List<PermisoOtorgadoDTO> usuariosOtorgadoDTOs = MapperUtils.DtoListFromEntityList(result.get(), PermisoOtorgadoDTO.class);
                return new ResponseEntity<>(usuariosOtorgadoDTOs, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/findByPermisoIdAndEstado")
    @ResponseBody
    @ApiOperation(value = "Obtiene una lista de permisos ortogados mediante estado y usuario", response = PermisoOtorgadoDTO.class, responseContainer = "List", tags = "PermisoOtorgado")
    public ResponseEntity<?> findByPermisoIdAndEstado(@PathVariable(value = "permisoId") Long permisoId, @PathVariable(value = "estado") boolean estado) {
        try {
            Optional<List<PermisoOtorgado>> result = permisoOtorgadoService.findByPermisoIdAndEstado(permisoId, estado);
            if (result.isPresent()) {
                List<PermisoOtorgadoDTO> usuariosOtorgadoDTOs = MapperUtils.DtoListFromEntityList(result.get(), PermisoOtorgadoDTO.class);
                return new ResponseEntity<>(usuariosOtorgadoDTOs, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
     @GetMapping("/RangoFecha")
    @ResponseBody
    @ApiOperation(value = "Obtiene un rango de fechas", response = PermisoOtorgadoDTO.class, responseContainer = "List", tags = "PermisoOtorgado")
    public ResponseEntity<?> findByFechaRegistroBetween(@PathVariable(value = "Date") Date strDate, @PathVariable(value = "endDate") Date endDate) {
        try {
            Optional<List<PermisoOtorgado>> result = permisoOtorgadoService.findByFechaRegistroBetween(strDate, endDate);
            if (result.isPresent()) {
                List<PermisoOtorgadoDTO> usuariosDTO = MapperUtils.DtoListFromEntityList(result.get(), PermisoOtorgadoDTO.class);
                return new ResponseEntity<>(usuariosDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/crear")
    @ResponseBody
    public ResponseEntity<?> create(@RequestBody PermisoOtorgado permisoOtorgado) {
        try {
            PermisoOtorgado permisoOtorgadoCreated = permisoOtorgadoService.create(permisoOtorgado);
            PermisoOtorgadoDTO permisoDTO = MapperUtils.DtoFromEntity(permisoOtorgadoCreated, PermisoOtorgadoDTO.class);
            return new ResponseEntity<>(permisoDTO, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/actualizar")
    @ResponseBody
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody PermisoOtorgado permisoOtorgadoModified) {
        try {
            Optional<PermisoOtorgado> permisoOtorgadoUpdated = permisoOtorgadoService.update(permisoOtorgadoModified, id);
            if (permisoOtorgadoUpdated.isPresent()) {
                PermisoOtorgadoDTO permisoDto = MapperUtils.DtoFromEntity(permisoOtorgadoUpdated.get(), PermisoOtorgadoDTO.class);
                return new ResponseEntity<>(permisoDto, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    }

