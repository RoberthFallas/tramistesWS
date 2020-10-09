/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tramites.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.una.tramites.dto.UsuarioDTO;
import org.una.tramites.entities.Usuario;

/**
 *
 * @author Roberth
 */
public interface IUsuarioRepository extends JpaRepository<Usuario, Long> {

    public Usuario findByCedulaAndPasswordEncriptado(String cedula, String passwordEncriptado);

    public List<Usuario> findByCedulaContaining(String cedula);

    public List<Usuario> findByNombreCompletoContainingIgnoreCase(String nombreCompleto);
    
    public Optional<Usuario> findByCedula(String Cedula);
    
    @Query("select u from Usuario u where UPPER(u.nombreCompleto) like CONCAT('%',UPPER(:nombreCompleto),'%')")  //OJO con slash extra -_-
    public Usuario findNombreCompletoWithLikeSQL(@Param("nombreCompleto") String nombreCompleto);

    @Query("select u from Usuario u where UPPER(u.nombreCompleto) like CONCAT('%',UPPER(:nombreCompleto),'%') and u.cedula like CONCAT(UPPER(:nombreCompleto),'%')")
    public Usuario findNombreCompletoYCedula(@Param("nombreCompleto") String nombreCompleto);

    public List<Usuario> findByDepartamentoId(Long id);                          //Optional????????????????

    @Query("SELECT u FROM Usuario u LEFT JOIN u.departamento d WHERE u.esJefe = 1 AND d.id=:id")
    public Optional<Usuario> findJefeByDepartamento(Long id);

}
