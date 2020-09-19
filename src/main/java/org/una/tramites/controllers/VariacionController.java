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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.una.tramites.dto.VariacionDTO;
import org.una.tramites.entities.Variacion;
import org.una.tramites.services.IVariacionService;
import org.una.tramites.utils.MapperUtils;

/**
 *
 * @author LordLalo
 */
@RestController
@RequestMapping("/variacion")
@Api(tags = {"Variacion"})
public class VariacionController {

    @Autowired
    private IVariacionService variacionServiceImplementacion;

    @GetMapping()
    @ApiOperation(value = "retorna una lista de todos las Variaciones de la entidad", response = VariacionDTO.class, responseContainer = "List", tags = "Variacion")
    public @ResponseBody
    ResponseEntity<?> findAll() {
        try {
            Optional<List<Variacion>> result = variacionServiceImplementacion.findAll();
            if (result.isPresent()) {
                List<VariacionDTO> resultDTO = MapperUtils.DtoListFromEntityList(result.get(), VariacionDTO.class);
                return new ResponseEntity<>(resultDTO, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "se obtiene un solo resultado", response = VariacionDTO.class, tags = "Variacion")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {

            Optional<Variacion> variacionFound = variacionServiceImplementacion.findById(id);
            if (variacionFound.isPresent()) {
                VariacionDTO variacionDto = MapperUtils.DtoFromEntity(variacionFound.get(), VariacionDTO.class);
                return new ResponseEntity<>(variacionDto, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    
//    @PostMapping("/create")
//    @ResponseStatus(HttpStatus.OK)
//    @ResponseBody
//    public ResponseEntity<?> create(@RequestBody VariacionDTO variacion) {
//        try {
//            Optional<VariacionDTO> result = variacionServiceImplementacion.create(variacion);
//            if (result.isPresent()) {
//                return new ResponseEntity<>(result.get(), HttpStatus.CREATED);
//            }
//            return new ResponseEntity<>("No ha sido posible realizar el cambio solicitado", HttpStatus.NOT_MODIFIED);
//        } catch (Exception e) {
//            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
    
    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody Variacion varModified) {
        try {
            Optional<Variacion> varUpdated = variacionServiceImplementacion.update(varModified, id);
            if (varUpdated.isPresent()) {
                VariacionDTO usuarioDto = MapperUtils.DtoFromEntity(varUpdated.get(), VariacionDTO.class);
                return new ResponseEntity<>(usuarioDto, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        try {
            variacionServiceImplementacion.delete(id);
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
            variacionServiceImplementacion.deleteAll();
            if (findAll().getStatusCode() == HttpStatus.NO_CONTENT) {
                return new ResponseEntity<>(HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    @GetMapping("/buscar/{grupo}")
//    public ResponseEntity<?> findByGrupo(@PathVariable(value = "grupo")boolean grupo){
//        try{
//            Optional<List<Variacion>> result = variacionServiceImplementacion.findByGrupo(grupo);
//            if(result.isPresent()){
//                List<VariacionDTO> resultDTO = MapperUtils.DtoListFromEntityList(result.get(), VariacionDTO.class);
//                return new ResponseEntity<>(resultDTO, HttpStatus.OK);
//            }
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        }catch(Exception ex){
//            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
    @GetMapping("/findByEstado/{estado}")
    @ResponseBody
    @ApiOperation(value = "Obtiene una lista de los estados", response = VariacionDTO.class, responseContainer = "List", tags = "Variacion")
    public ResponseEntity<?> findByGrupo(@PathVariable(value = "estado") boolean estado) {
        try {
            Optional<List<Variacion>> result = variacionServiceImplementacion.findByGrupo(estado);
            if (result.isPresent()) {
                List<VariacionDTO> usuariosDTO = MapperUtils.DtoListFromEntityList(result.get(), VariacionDTO.class);
                return new ResponseEntity<>(usuariosDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/buscar/{descripcion}")
    public ResponseEntity<?> findByDescripcion(@PathVariable(value = "descripcion") String descripcion) {
        try {
            Optional<List<Variacion>> result = variacionServiceImplementacion.findByDescripcion(descripcion);
            if (result.isPresent()) {
                List<VariacionDTO> resultDTO = MapperUtils.DtoListFromEntityList(result.get(), VariacionDTO.class);
                return new ResponseEntity<>(resultDTO, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
