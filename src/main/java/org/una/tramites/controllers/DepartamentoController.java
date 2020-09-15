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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.una.tramites.dto.DepartamentoDTO;
import org.una.tramites.dto.UsuarioDTO;
import org.una.tramites.entities.Departamento;
import org.una.tramites.services.IDepartamentoService;
import org.una.tramites.utils.MapperUtils;

/**
 *
 * @author Roberth
 */
@RestController
@RequestMapping("/departamentos")
@Api(tags = {"Departamentos"})
public class DepartamentoController {

    @Autowired
    private IDepartamentoService departamentoService;

    @GetMapping("buscarTodo")
    @ResponseBody
    @ApiOperation(value = "Obtiene una lista de todos los departamentos", response = UsuarioDTO.class, responseContainer = "List", tags = "Departamentos")
    public ResponseEntity<?> findAll() {
        try {
            Optional<List<Departamento>> result = departamentoService.findAll();
            if (result.isPresent()) {
                List<DepartamentoDTO> departDto = MapperUtils.DtoListFromEntityList(result.get(), DepartamentoDTO.class);
                return new ResponseEntity<>(departDto, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{idDepartamento}")
    @ApiOperation(value = "Obtiene un Departamento a travez de su identificador unico", response = DepartamentoDTO.class, tags = "Departamentos")
    public ResponseEntity<?> findById(@PathVariable(value = "idDepartamento") Long id) {
        try {
            Optional<Departamento> departamentoFound = departamentoService.findById(id);
            if (departamentoFound.isPresent()) {
                DepartamentoDTO departamentoDTO = MapperUtils.DtoFromEntity(departamentoFound.get(), DepartamentoDTO.class);
                return new ResponseEntity<>(departamentoDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("NO_CONTENT",HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/create")
    @ResponseBody
    public ResponseEntity<?> create(@RequestBody Departamento departamento) {
        try {
            Departamento departamentoCreated = departamentoService.create(departamento);
            DepartamentoDTO departamentoDTO = MapperUtils.DtoFromEntity(departamentoCreated, DepartamentoDTO.class);
            return new ResponseEntity<>(departamentoDTO, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        try {
            departamentoService.delete(id);
            if (findById(id).getStatusCode() == HttpStatus.NO_CONTENT) {
                return new ResponseEntity<>(HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }}
    
     @GetMapping("/findByEstado/{estado}")
    @ResponseBody
    @ApiOperation(value = "Obtiene una lista de los estados", response = DepartamentoDTO.class, responseContainer = "List", tags = "Departamentos")
    public ResponseEntity<?> findByEstado(@PathVariable(value = "estado") boolean estado) {
        try {
            Optional<List<Departamento>> result = departamentoService.findByEstado(estado);
            if (result.isPresent()) {
                List<DepartamentoDTO> departamentoDTO= MapperUtils.DtoListFromEntityList(result.get(), DepartamentoDTO.class);
                return new ResponseEntity<>(departamentoDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/buscar/{nombre}")
    public ResponseEntity<?> findByDescripcion(@PathVariable(value = "nombre")String nombre){
        try{
            Optional<Departamento> result = departamentoService.findByNombre(nombre);
            if (result.isPresent()) {
                DepartamentoDTO departamentoDTO = MapperUtils.DtoFromEntity(result.get(), DepartamentoDTO.class);
                return new ResponseEntity<>(departamentoDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
     

}
