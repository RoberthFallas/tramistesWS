package org.una.tramites.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.Date;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.una.tramites.dto.PermisoOtorgadoDTO;
import org.una.tramites.services.IPermisoOtorgadoService;

/**
 *
 * @author LordLalo
 */
@RestController
@RequestMapping("/permisoOtorgado")
@Api(tags = {"PermisoOtorgado"})
public class PermisoOtorgadoController {

    @Autowired
    private IPermisoOtorgadoService permisoOtorgadoService;

    @GetMapping("/{id}")
    @ApiOperation(value = "Obtiene un permiso otorgado mediante id", response = PermisoOtorgadoDTO.class, tags = "PermisoOtorgado")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {
            return new ResponseEntity(permisoOtorgadoService.findById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/estados/{usuarioId}")
    @ResponseBody
    @ApiOperation(value = "Obtiene una lista de los estados", response = PermisoOtorgadoDTO.class, responseContainer = "List", tags = "PermisoOtorgado")
    public ResponseEntity<?> findByUsuarioId(@PathVariable(value = "usuarioId") Long usuarioId) {
        try {
            return new ResponseEntity(permisoOtorgadoService.findByUsuarioId(usuarioId), HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/permisosOtorgadoid/{permisoId}")
    @ResponseBody
    @ApiOperation(value = "Obtiene una lista de los permios otorgados", response = PermisoOtorgadoDTO.class, responseContainer = "List", tags = "PermisoOtorgado")
    public ResponseEntity<?> findByPermisoId(@PathVariable(value = "permisoId") Long permisoId) {
        try {
            return new ResponseEntity(permisoOtorgadoService.findById(permisoId), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/findByUsuarioIdAndEstado/{usuarioId}")
    @ResponseBody
    @ApiOperation(value = "Obtiene una lista de permisos ortogados mediante estado y usuario", response = PermisoOtorgadoDTO.class, responseContainer = "List", tags = "PermisoOtorgado")
    public ResponseEntity<?> findByUsuarioIdAndEstado(@PathVariable(value = "usuarioId") Long usuarioId, @PathVariable(value = "estado") boolean estado) {
        try {
            return new ResponseEntity(permisoOtorgadoService.findByPermisoIdAndEstado(usuarioId, estado), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/findByPermisoIdAndEstado/{permisoId}")
    @ResponseBody
    @ApiOperation(value = "Obtiene una lista de permisos ortogados mediante estado y usuario", response = PermisoOtorgadoDTO.class, responseContainer = "List", tags = "PermisoOtorgado")
    public ResponseEntity<?> findByPermisoIdAndEstado(@PathVariable(value = "permisoId") Long permisoId, @PathVariable(value = "estado") boolean estado) {
        try {
            return new ResponseEntity(permisoOtorgadoService.findByPermisoIdAndEstado(permisoId, estado), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/RangoFecha/{Date}/{endDate}")
    @ResponseBody
    @ApiOperation(value = "Obtiene un rango de fechas", response = PermisoOtorgadoDTO.class, responseContainer = "List", tags = "PermisoOtorgado")
    public ResponseEntity<?> findByFechaRegistroBetween(@PathVariable(value = "Date") Date strDate, @PathVariable(value = "endDate") Date endDate) {
        try {
            return new ResponseEntity(permisoOtorgadoService.findByFechaRegistroBetween(strDate, endDate), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/crear")
    @ResponseBody
    public ResponseEntity<?> create(@Valid @RequestBody PermisoOtorgadoDTO permisoOtorgadoDTO, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            try {
                return new ResponseEntity(permisoOtorgadoService.create(permisoOtorgadoDTO), HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity("MENSAJE_VERIFICAR_INFORMACION", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/actualizar/{id}")
    @ResponseBody
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @Valid @RequestBody PermisoOtorgadoDTO permisoOtorgadoDTO, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            try {
                Optional<PermisoOtorgadoDTO> usuarioUpdated = permisoOtorgadoService.update(permisoOtorgadoDTO, id);
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
