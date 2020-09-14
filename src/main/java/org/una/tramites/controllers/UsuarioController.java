
package org.una.tramites.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
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
import org.una.tramites.dto.UsuarioDTO;
import org.una.tramites.dto.AuthenticationRequest;
import org.una.tramites.dto.AuthenticationResponse;
import org.una.tramites.entities.Usuario;
import org.una.tramites.services.IUsuarioService;
import org.una.tramites.utils.MapperUtils;


/**
 *
 * @author Roberth
 */
@RestController
@RequestMapping("/usuarios")
@Api(tags = {"Usuarios"})
public class UsuarioController {
//
//    @Autowired
//    private IUsuarioService usuarioService;
//
//    @GetMapping()
//    @ResponseBody
//    @ApiOperation(value = "Obtiene una lista de todos los Usuarios", response = UsuarioDTO.class, responseContainer = "List", tags = "Usuarios")
//    public ResponseEntity<?> findAll() {
//        try {
//            Optional<List<Usuario>> result = usuarioService.findAll();
//            if (result.isPresent()) {
//                List<UsuarioDTO> usuariosDTO = MapperUtils.DtoListFromEntityList(result.get(), UsuarioDTO.class);
//                return new ResponseEntity<>(usuariosDTO, HttpStatus.OK);
//            } else {
//                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//            }
//        } catch (Exception e) {
//            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//    @GetMapping("/{id}")
//    @ApiOperation(value = "Obtiene a un usuario por medio de su Id de base de datos", response = UsuarioDTO.class, tags = "Usuarios")
//    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
//        try {
//            Optional<Usuario> usuarioFound = usuarioService.findById(id);
//            if (usuarioFound.isPresent()) {
//                UsuarioDTO usuarioDto = MapperUtils.DtoFromEntity(usuarioFound.get(), UsuarioDTO.class);
//                return new ResponseEntity<>(usuarioDto, HttpStatus.OK);
//            } else {
//                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//            }
//        } catch (Exception e) {
//            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
////    @PutMapping("/login")
////    @ResponseBody
////    @ApiOperation(value = "Inicio de sesión para conseguir un token de acceso", response = UsuarioDTO.class, tags = "Seguridad")
////    public ResponseEntity<?> login(@PathVariable(value = "cedula") String cedula, @PathVariable(value = "password") String password) {
////        try {
////            Usuario usuario = new Usuario();
////            usuario.setCedula(cedula);
////            usuario.setPasswordEncriptado(password);
////            Optional<Usuario> usuarioFound = usuarioService.login(usuario);
////            if (usuarioFound.isPresent()) {
////                UsuarioDTO usuarioDto = MapperUtils.DtoFromEntity(usuarioFound.get(), UsuarioDTO.class);
////                return new ResponseEntity<>(usuarioDto, HttpStatus.OK);
////            } else {
////                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
////            }
////        } catch (Exception e) {
////            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
////        }
////
////    }
//    @PostMapping("/login")
//    @ResponseBody
//    @ApiOperation(value = "Inicio de sesión para conseguir un token de acceso", response = UsuarioDTO.class, tags = "Seguridad")
//    public ResponseEntity<?> login(@Valid @RequestBody AuthenticationRequest authenticationRequest, BindingResult bindingResult) {
//
//        if (bindingResult.hasErrors()) {
//            return new ResponseEntity("La información no esta bien formada o no coincide con el formato esperado", HttpStatus.BAD_REQUEST);
//        }
//        try {
//            AuthenticationResponse authenticationResponse = new AuthenticationResponse();
//            String token = usuarioService.login(authenticationRequest);
//            if (!token.isBlank()) {
//                authenticationResponse.setJwt(token);
//                //TODO: Complete this   authenticationResponse.setUsuario(usuario);
//                //TODO: Complete this    authenticationResponse.setPermisos(permisosOtorgados);
//                return new ResponseEntity(authenticationResponse, HttpStatus.OK);
//            } else {
//                return new ResponseEntity<>("Credenciales invalidos", HttpStatus.UNAUTHORIZED);
//            }
//        } catch (Exception e) {
//            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//    
//    @GetMapping("/cedula/{term}")
//    @ApiOperation(value = "Obtiene lista de usuarios cuya cedula coinsida parcialmente con el parámetro recibido.", response = UsuarioDTO.class, tags = "Usuarios")
//    public ResponseEntity<?> findByCedulaAproximate(@PathVariable(value = "term") String term) {
//        try {
//            Optional<List<Usuario>> result = usuarioService.findByCedulaAproximate(term);
//            if (result.isPresent()) {
//                List<UsuarioDTO> usuariosDTO = MapperUtils.DtoListFromEntityList(result.get(), UsuarioDTO.class);
//                return new ResponseEntity<>(usuariosDTO, HttpStatus.OK);
//            } else {
//                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//            }
//        } catch (Exception e) {
//            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//    @GetMapping("/nombre/{term}")
//    public ResponseEntity<?> findByNombreCompletoAproximateIgnoreCase(@PathVariable(value = "term") String term) {
//        try {
//            Optional<List<Usuario>> result = usuarioService.findByNombreCompletoAproximateIgnoreCase(term);
//            if (result.isPresent()) {
//                List<UsuarioDTO> usuariosDTO = MapperUtils.DtoListFromEntityList(result.get(), UsuarioDTO.class);
//                return new ResponseEntity<>(usuariosDTO, HttpStatus.OK);
//            } else {
//                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//            }
//        } catch (Exception e) {
//            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//    @ResponseStatus(HttpStatus.OK)
//    @PostMapping("/")
//    @ResponseBody
//    public ResponseEntity<?> create(@RequestBody Usuario usuario) {
//        try {
//            Usuario usuarioCreated = usuarioService.create(usuario);
//            UsuarioDTO usuarioDto = MapperUtils.DtoFromEntity(usuarioCreated, UsuarioDTO.class);
//            return new ResponseEntity<>(usuarioDto, HttpStatus.CREATED);
//        } catch (Exception e) {
//            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//    @PutMapping("/{id}")
//    @ResponseBody
//    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody Usuario usuarioModified) {
//        try {
//            Optional<Usuario> usuarioUpdated = usuarioService.update(usuarioModified, id);
//            if (usuarioUpdated.isPresent()) {
//                UsuarioDTO usuarioDto = MapperUtils.DtoFromEntity(usuarioUpdated.get(), UsuarioDTO.class);
//                return new ResponseEntity<>(usuarioDto, HttpStatus.OK);
//            } else {
//                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//            }
//        } catch (Exception e) {
//            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//    @GetMapping("/findByDepartamentoId/{id}")
//   // @ResponseBody
//    @ApiOperation(value = "Obtiene una lista de Usuarios que pertenezcan al departamento especificado", response = UsuarioDTO.class, responseContainer = "List", tags = "Usuarios")
//    public ResponseEntity<?> findByDepartamentoId(@PathVariable(value = "id") Long id) {
//        try {
//            Optional<List<UsuarioDTO>> result = usuarioService.findByDepartamentoId(id);
//            if (result.isPresent()) {
//                List<UsuarioDTO> usuariosDto = result.get();
//                return new ResponseEntity<>(usuariosDto, HttpStatus.OK);
//            } else {
//                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//            }
//        } catch (Exception e) {
//            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//    @GetMapping("/findJefeByDepartamento/{id}")
//  //  @ResponseBody
//    @ApiOperation(value = "Obtiene a jefe del departamento especificado", response = UsuarioDTO.class, tags = "Usuarios")
//    public ResponseEntity<?> findJefeByDepartamento(@PathVariable(value = "id") Long id) {
//        try {
//            Optional<Usuario> usuarioUpdated = usuarioService.findJefeByDepartamento(id);
//            if (usuarioUpdated.isPresent()) {
//                UsuarioDTO usuarioDto = MapperUtils.DtoFromEntity(usuarioUpdated.get(), UsuarioDTO.class);
//                return new ResponseEntity<>(usuarioDto, HttpStatus.OK);
//            } else {
//                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//            }
//        } catch (Exception e) {
//            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//        @DeleteMapping("/{id}")
//        public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
// try {
//            usuarioService.delete(id);
//            if (findById(id).getStatusCode() == HttpStatus.NO_CONTENT) {
//                return new ResponseEntity<>(HttpStatus.OK);
//            }
//            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
//        } catch (Exception ex) {
//            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//        }
//
//        @DeleteMapping("/")
//        public ResponseEntity<?> deleteAll() {
//          try {
//            usuarioService.deleteAll();
//            if (findAll().getStatusCode() == HttpStatus.NO_CONTENT) {
//                return new ResponseEntity<>(HttpStatus.OK);
//            }
//            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
//        } catch (Exception ex) {
//            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//        }
    
    
    @Autowired
    private IUsuarioService usuarioService;

    @GetMapping()
    @ApiOperation(value = "Obtiene una lista de todos los Usuarios", response = UsuarioDTO.class, responseContainer = "List", tags = "Usuarios")
    public @ResponseBody
    ResponseEntity<?> findAll() {
        try {
            Optional<List<Usuario>> result = usuarioService.findAll();
            if (result.isPresent()) {
                List<UsuarioDTO> usuariosDTO = MapperUtils.DtoListFromEntityList(result.get(), UsuarioDTO.class);
                return new ResponseEntity<>(usuariosDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Obtiene un usuario a travez de su identificador unico", response = UsuarioDTO.class, tags = "Usuarios")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {

            Optional<Usuario> usuarioFound = usuarioService.findById(id);
            if (usuarioFound.isPresent()) {
                UsuarioDTO usuarioDto = MapperUtils.DtoFromEntity(usuarioFound.get(), UsuarioDTO.class);
                return new ResponseEntity<>(usuarioDto, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
/*
    @PutMapping("/login")
    @ResponseBody
    @ApiOperation(value = "Inicio de sesión para conseguir un token de acceso", response = UsuarioDTO.class, tags = "Seguridad")
    public ResponseEntity<?> login(@PathVariable(value = "cedula") String cedula, @PathVariable(value = "password") String password) {
        try {
            Usuario usuario = new Usuario();
            usuario.setCedula(cedula);
            usuario.setPasswordEncriptado(password);
            Optional<Usuario> usuarioFound = usuarioService.login(usuario);
            if (usuarioFound.isPresent()) {
                UsuarioDTO usuarioDto = MapperUtils.DtoFromEntity(usuarioFound.get(), UsuarioDTO.class);
                return new ResponseEntity<>(usuarioDto, HttpStatus.OK);

            } else {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
*/
    @PostMapping("/login")
    @ResponseBody
    @ApiOperation(value = "Inicio de sesión para conseguir un token de acceso", response = UsuarioDTO.class, tags = "Seguridad")
    public ResponseEntity<?> login(@Valid @RequestBody AuthenticationRequest authenticationRequest, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return new ResponseEntity("La información no esta bien formada o no coincide con el formato esperado", HttpStatus.BAD_REQUEST);
        }
        try {
            AuthenticationResponse authenticationResponse = new AuthenticationResponse();
            String token = usuarioService.login(authenticationRequest);
            if (!token.isBlank()) {
                authenticationResponse.setJwt(token);
                //TODO: Complete this   authenticationResponse.setUsuario(usuario);
                //TODO: Complete this    authenticationResponse.setPermisos(permisosOtorgados);
                return new ResponseEntity(authenticationResponse, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Credenciales invalidos", HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/cedula/{term}")
    public ResponseEntity<?> findByCedulaAproximate(@PathVariable(value = "term") String term) {
        try {
            Optional<List<Usuario>> result = usuarioService.findByCedulaAproximate(term);
            if (result.isPresent()) {
                List<UsuarioDTO> usuariosDTO = MapperUtils.DtoListFromEntityList(result.get(), UsuarioDTO.class);
                return new ResponseEntity<>(usuariosDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/nombre/{term}")
    public ResponseEntity<?> findByNombreCompletoAproximateIgnoreCase(@PathVariable(value = "term") String term) {
        try {
            Optional<List<Usuario>> result = usuarioService.findByNombreCompletoAproximateIgnoreCase(term);
            if (result.isPresent()) {
                List<UsuarioDTO> usuariosDTO = MapperUtils.DtoListFromEntityList(result.get(), UsuarioDTO.class);
                return new ResponseEntity<>(usuariosDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/")
    @ResponseBody
    public ResponseEntity<?> create(@RequestBody Usuario usuario) {
        try {
            Usuario usuarioCreated = usuarioService.create(usuario);
            UsuarioDTO usuarioDto = MapperUtils.DtoFromEntity(usuarioCreated, UsuarioDTO.class);
            return new ResponseEntity<>(usuarioDto, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody Usuario usuarioModified) {
        try {
            Optional<Usuario> usuarioUpdated = usuarioService.update(usuarioModified, id);
            if (usuarioUpdated.isPresent()) {
                UsuarioDTO usuarioDto = MapperUtils.DtoFromEntity(usuarioUpdated.get(), UsuarioDTO.class);
                return new ResponseEntity<>(usuarioDto, HttpStatus.OK);

            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        try {
            usuarioService.delete(id);
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
            usuarioService.deleteAll();
            if (findAll().getStatusCode() == HttpStatus.NO_CONTENT) {
                return new ResponseEntity<>(HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    @GetMapping("/usuarios_en_departamento/{id}")
//    public ResponseEntity<?> findByDepartamentoId(@PathVariable(value = "id") Long id) {
//        try {
//            Optional<List<Usuario>> result = usuarioService.findUsersByDepartamentoId(id);
//            if (result.isPresent()) {
//                List<UsuarioDTO> usuariosDTO = MapperUtils.DtoListFromEntityList(result.get(), UsuarioDTO.class);
//                return new ResponseEntity<>(usuariosDTO, HttpStatus.OK);
//            }
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        } catch (Exception ex) {
//            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//    @GetMapping("/jefe/{id}")
//    public ResponseEntity<?> findJefeByDepartemento(@PathVariable(value = "id") Long id) {
//        try {
//            Optional<Usuario> result = usuarioService.findJefesDepartemento(id);
//            if (result.isPresent()) {
//                UsuarioDTO userDto = MapperUtils.DtoFromEntity(result.get(), UsuarioDTO.class);
//                return new ResponseEntity<>(userDto, HttpStatus.OK);
//            }
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        } catch (Exception ex) {
//            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
    
}
