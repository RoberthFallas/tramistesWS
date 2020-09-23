package org.una.tramites.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @Autowired
    private IUsuarioService usuarioService;

    @GetMapping()
    @ApiOperation(value = "Obtiene una lista de todos los Usuarios", response = UsuarioDTO.class, responseContainer = "List", tags = "Usuarios")
    @ResponseBody
    @PreAuthorize("hasAuthority('USUARIO_CONSULTAR_TODO')")
    public ResponseEntity<?> findAll() {
        try {
            return new ResponseEntity(usuarioService.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getClass(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

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
     
//    @GetMapping("/{id}")
//    @ApiOperation(value = "Obtiene un usuario a travez de su identificador unico", response = UsuarioDTO.class, tags = "Usuarios")
//    @PreAuthorize("hasAuthority('USUARIO_CONSULTAR')")
//    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
//        try {
//
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
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('USUARIO_CONSULTAR')")
    @ApiOperation(value = "Obtiene un Usuario por su Id", response = UsuarioDTO.class, tags = "Usuarios")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {
            return new ResponseEntity(usuarioService.findById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

////////    /*
////////    @PutMapping("/login")
////////    @ResponseBody
////////    @ApiOperation(value = "Inicio de sesión para conseguir un token de acceso", response = UsuarioDTO.class, tags = "Seguridad")
////////    public ResponseEntity<?> login(@PathVariable(value = "cedula") String cedula, @PathVariable(value = "password") String password) {
////////        try {
////////            Usuario usuario = new Usuario();
////////            usuario.setCedula(cedula);
////////            usuario.setPasswordEncriptado(password);
////////            Optional<Usuario> usuarioFound = usuarioService.login(usuario);
////////            if (usuarioFound.isPresent()) {
////////                UsuarioDTO usuarioDto = MapperUtils.DtoFromEntity(usuarioFound.get(), UsuarioDTO.class);
////////                return new ResponseEntity<>(usuarioDto, HttpStatus.OK);
////////
////////            } else {
////////                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
////////            }
////////        } catch (Exception e) {
////////            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
////////        }
////////
////////    }
////////     */
//////////    @PostMapping("/login")
//////////    @ResponseBody
//////////    @ApiOperation(value = "Inicio de sesión para conseguir un token de acceso", response = UsuarioDTO.class, tags = "Seguridad")
//////////    public ResponseEntity<?> login(@Valid @RequestBody AuthenticationRequest authenticationRequest, BindingResult bindingResult) {
//////////
//////////        if (bindingResult.hasErrors()) {
//////////            return new ResponseEntity("La información no esta bien formada o no coincide con el formato esperado", HttpStatus.BAD_REQUEST);
//////////        }
//////////        try {
//////////            AuthenticationResponse authenticationResponse = new AuthenticationResponse();
//////////            String token = usuarioService.login2(authenticationRequest);
//////////             AuthenticationResponse token= usuarioService.login(authenticationRequest);
//////////            if (!token.isBlank()) {
//////////                authenticationResponse.setJwt(token);
//////////                
//////////                TODO: Complete this   authenticationResponse.setUsuario(usuario);
//////////                TODO: Complete this    authenticationResponse.setPermisos(permisosOtorgados);
//////////                return new ResponseEntity(authenticationResponse, HttpStatus.OK);
//////////            } else {
//////////                return new ResponseEntity<>("Credenciales invalidos", HttpStatus.UNAUTHORIZED);
//////////            }
//////////        } catch (Exception e) {
//////////            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
//////////        }
//////////    }
//////////    @PostMapping("/login")
//////////    @ResponseBody
//////////    @ApiOperation(value = "Inicio de sesión para conseguir un token de acceso", response = UsuarioDTO.class, tags = "Seguridad")
//////////    public ResponseEntity<?> login(@Valid @RequestBody AuthenticationRequest authenticationRequest, BindingResult bindingResult) {
//////////
//////////        if (bindingResult.hasErrors()) {
//////////            return new ResponseEntity("La información no esta bien formada o no coincide con el formato esperado", HttpStatus.BAD_REQUEST);
//////////        }
//////////        try {
//////////            AuthenticationResponse authenticationResponse = new AuthenticationResponse();
//////////            //  String token = usuarioService.login(authenticationRequest);
//////////            AuthenticationResponse token = usuarioService.login(authenticationRequest);
//////////            if (token != null) {
//////////                authenticationResponse = token;
//////////
////////////                authenticationResponse.setUsuario(token.getUsuario());
////////////                authenticationResponse.setPermisos(token.getPermisos());
//////////                return new ResponseEntity(authenticationResponse, HttpStatus.OK);
//////////            } else {
//////////                return new ResponseEntity<>("Credenciales invalidos", HttpStatus.UNAUTHORIZED);
//////////            }
//////////        } catch (Exception e) {
//////////            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
//////////        }
//////////    }
//    @GetMapping("/cedula/{term}")
//    @PreAuthorize("hasAuthority('USUARIO_CONSULTAR_CEDULA')")
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
     @GetMapping("/cedula/{term}")
    @PreAuthorize("hasAuthority('USUARIO_CONSULTAR_CEDULA')")
    public ResponseEntity<?> findByCedulaAproximate(@PathVariable(value = "term") String term) {
       try{
        return new ResponseEntity(usuarioService.findByCedulaAproximate(term),HttpStatus.OK);
       }catch(Exception e){
        return new ResponseEntity(e,HttpStatus.INTERNAL_SERVER_ERROR);
       }
    }

//    @GetMapping("/nombre/{term}")
//    @PreAuthorize("hasAuthority('USUARIO_CONSULTAR_NOMBRE')")
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
        @GetMapping("/nombre/{term}")
    @PreAuthorize("hasAuthority('USUARIO_CONSULTAR_NOMBRE')")
    public ResponseEntity<?> findByNombreCompletoAproximateIgnoreCase(@PathVariable(value = "term") String term) {
       try{
       return new ResponseEntity(usuarioService.findByNombreCompletoAproximateIgnoreCase(term),HttpStatus.OK);
       }catch(Exception e){
           return new ResponseEntity(e,HttpStatus.INTERNAL_SERVER_ERROR);
       }
       
    }
//    @ResponseStatus(HttpStatus.OK)
//    @PostMapping("/")
//    @ResponseBody
//    @PreAuthorize("hasAuthority('USUARIO_CREAR')")
//    public ResponseEntity<?> create(@RequestBody Usuario usuario) {
//        try {
//            Usuario usuarioCreated = usuarioService.create(usuario);
//            UsuarioDTO usuarioDto = MapperUtils.DtoFromEntity(usuarioCreated, UsuarioDTO.class);
//            return new ResponseEntity<>(usuarioDto, HttpStatus.CREATED);
//        } catch (Exception e) {
//            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/")
    @ResponseBody
    @PreAuthorize("hasAuthority('USUARIO_CREAR')")
    public ResponseEntity<?> create(@RequestBody UsuarioDTO usuarioDTO,BindingResult bindingResult) {
             if (!bindingResult.hasErrors()) {
            try {
                return new ResponseEntity(usuarioService.create(usuarioDTO), HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity("MENSAJE_VERIFICAR_INFORMACION", HttpStatus.BAD_REQUEST);
        }

    }
    @PutMapping("/{id}")
    @ResponseBody
        public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @Valid @RequestBody UsuarioDTO usuarioDTO, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            try {
                Optional<UsuarioDTO> usuarioUpdated = usuarioService.update(usuarioDTO, id);
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

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('USUARIO_ELIMINAR')")
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
    @PreAuthorize("hasAuthority('USUARIO_ELIMINAR_TODO')")
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
