package org.una.tramites.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.una.tramites.dto.UsuarioDTO;
import org.una.tramites.entities.Usuario;
import org.una.tramites.repositories.IUsuarioRepository;
import org.una.tramites.utils.MapperUtils;
import org.una.tramites.entities.PermisoOtorgado;
import org.una.tramites.jwt.JwtProvider;

@Service
public class UsuarioServiceImplementation implements UserDetailsService, IUsuarioService {

    @Autowired
    private IUsuarioRepository usuarioRepository;
    @Autowired
    private JwtProvider jwtProvider;
    @Autowired
    private AuthenticationManager authenticationManager;

//    @Override
//    @Transactional(readOnly = true)
//    public Optional<List<Usuario>> findAll() {
//        return Optional.ofNullable(usuarioRepository.findAll());
//    }
    private Optional<List<UsuarioDTO>> findList(List<Usuario> list) {
        if (list != null) {
            List<UsuarioDTO> usuariosDTO = MapperUtils.DtoListFromEntityList(list, UsuarioDTO.class);
            return Optional.ofNullable(usuariosDTO);
        } else {
            return null;
        }
    }

    private Optional<List<UsuarioDTO>> findList(Optional<List<Usuario>> list) {
        if (list.isPresent()) {
            return findList(list.get());
        } else {
            return null;
        }
    }

    private Optional<UsuarioDTO> oneToDto(Optional<Usuario> one) {
        if (one.isPresent()) {
            UsuarioDTO usuarioDTO = MapperUtils.DtoFromEntity(one.get(), UsuarioDTO.class);
            return Optional.ofNullable(usuarioDTO);
        } else {
            return null;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<UsuarioDTO>> findAll() {
        return findList(usuarioRepository.findAll());
    }

//    @Override
//    @Transactional(readOnly = true)
//    public Optional<Usuario> findById(Long id) {
//        return usuarioRepository.findById(id);
//    }
    @Override
    @Transactional(readOnly = true)
    public Optional<UsuarioDTO> findById(Long id) {
        return oneToDto(usuarioRepository.findById(id));

    }

//    @Override
//    @Transactional(readOnly = true)
//    public Optional<List<Usuario>> findByCedulaAproximate(String cedula) {
//        return Optional.ofNullable(usuarioRepository.findByCedulaContaining(cedula));
//    }
    @Override
    @Transactional(readOnly = true)
    public Optional<List<UsuarioDTO>> findByCedulaAproximate(String cedula) {
        return findList(usuarioRepository.findByCedulaContaining(cedula));

    }

//    @Override
//    @Transactional(readOnly = true)
//    public Optional<List<Usuario>> findByNombreCompletoAproximateIgnoreCase(String nombreCompleto) {
//        return Optional.ofNullable(usuarioRepository.findByNombreCompletoContainingIgnoreCase(nombreCompleto));
//    }
    @Override
    @Transactional(readOnly = true)
    public Optional<List<UsuarioDTO>> findByNombreCompletoAproximateIgnoreCase(String nombreCompleto) {
        return findList(usuarioRepository.findByNombreCompletoContainingIgnoreCase(nombreCompleto));

    }

//    @Override
//    @Transactional
//    public Usuario create(Usuario usuario) {
//        encriptarPassword(usuario);
//        return usuarioRepository.save(usuario);
//    }
    @Override
    @Transactional
    public UsuarioDTO create(UsuarioDTO usuarioDTO) {
        usuarioDTO = encriptarPassword(usuarioDTO);
        Usuario usuario = MapperUtils.EntityFromDto(usuarioDTO, Usuario.class);
        usuario = usuarioRepository.save(usuario);
        return MapperUtils.DtoFromEntity(usuario, UsuarioDTO.class);
    }

    @Override
    @Transactional
    public Optional<UsuarioDTO> update(UsuarioDTO usuarioDTO, Long id) {
        if (usuarioRepository.findById(id).isPresent()) {
            usuarioDTO = encriptarPassword(usuarioDTO);
            Usuario usuario = MapperUtils.EntityFromDto(usuarioDTO, Usuario.class);
            usuario = usuarioRepository.save(usuario);
            return Optional.ofNullable(MapperUtils.DtoFromEntity(usuario, UsuarioDTO.class));
        } else {
            return null;
        }
    }

    @Override
    @Transactional
    public void delete(Long id) {
        usuarioRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteAll() {
        usuarioRepository.deleteAll();
    }


    @Override
    public Optional<List<UsuarioDTO>> findByDepartamentoId(Long id) {
        Optional<List<Usuario>> result = Optional.ofNullable(usuarioRepository.findByDepartamentoId(id));
        if (result.isPresent()) {
            List<UsuarioDTO> usuariosDTO = MapperUtils.DtoListFromEntityList(result.get(), UsuarioDTO.class);
            return Optional.of(usuariosDTO);
        }
        return Optional.empty();
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<UsuarioDTO> findJefeByDepartamento(Long id) {
        Optional<Usuario> result = usuarioRepository.findJefeByDepartamento(id);
        if (result != null) {
            UsuarioDTO usuarioDTO = MapperUtils.DtoFromEntity(result.get(), UsuarioDTO.class);
            return Optional.ofNullable(usuarioDTO);
        } else {
            return null;
        }
    }


    @Override
    @Transactional(readOnly = true)

    public Optional<UsuarioDTO> findByCedula(String cedula) {
        return oneToDto(usuarioRepository.findByCedula(cedula));
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      
        Optional<Usuario> usuarioBuscado = (usuarioRepository.findByCedula(username));
        if (usuarioBuscado.isPresent()) {
            Usuario usuario = usuarioBuscado.get();
            List<GrantedAuthority> roles = new ArrayList<>();
            for (PermisoOtorgado p : usuario.getPermisosOtorgado()) {
                roles.add(new SimpleGrantedAuthority(p.getPermiso().getDescripcion()));
            }
            for (GrantedAuthority role : roles) {
                System.out.println("org.una.tramites.services.UsuarioServiceImplementation.loadUserByUsername()" + role);
            }
            //roles.add(new SimpleGrantedAuthority("ADMIN"));
            UserDetails userDetails = new User(usuario.getCedula(), usuario.getPasswordEncriptado(), roles);
            return userDetails;
        } else {
            return null;
        }

    }

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    
    private UsuarioDTO encriptarPassword(UsuarioDTO usuario) {
        String password = usuario.getPasswordEncriptado();
        if (!password.isBlank()) {
            usuario.setPasswordEncriptado(bCryptPasswordEncoder.encode(password));
        }
        return usuario;
    }
//
//    @Override
//    public String login2(AuthenticationRequest authenticationRequest) {
//        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getCedula(), authenticationRequest.getPassword()));
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        return jwtProvider.generateToken(authenticationRequest);
//    }
//    @Override
//    @Transactional(readOnly = true)
//    public AuthenticationResponse login(AuthenticationRequest authenticationRequest) {
//
//        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getCedula(), authenticationRequest.getPassword()));
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        AuthenticationResponse authenticationResponse = new AuthenticationResponse();
//
//        Optional<Usuario> usuario = findByCedula(authenticationRequest.getCedula());
//
//        if (usuario.isPresent()) {
//            authenticationResponse.setJwt(jwtProvider.generateToken(authenticationRequest));
//            UsuarioDTO usuarioDto = MapperUtils.DtoFromEntity(usuario.get(), UsuarioDTO.class);
//            authenticationResponse.setUsuario(usuarioDto);
//            List<PermisoOtorgadoDTO> permisosOtorgadosDto = MapperUtils.DtoListFromEntityList(usuario.get().getPermisos(), PermisoOtorgadoDTO.class);
//            authenticationResponse.setPermisos(permisosOtorgadosDto);
//
//            return authenticationResponse;
//        } else {
//           return null;
//        }
//
//    }

}
