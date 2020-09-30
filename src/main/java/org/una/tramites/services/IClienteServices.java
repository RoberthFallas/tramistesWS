/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tramites.services;

import java.util.List;
import java.util.Optional;
import org.una.tramites.dto.ClienteDTO;
import org.una.tramites.entities.Cliente;

/**
 *
 * @author LordLalo
 */
public interface IClienteServices {

    public Optional<List<ClienteDTO>> findAll();

    public Optional<ClienteDTO> findById(Long id);

    public Optional<List<ClienteDTO>> findByCedulaAproximate(String cedula);

    public Optional<List<ClienteDTO>> findByNombreCompletoAproximateIgnoreCase(String nombreCompleto);

    public ClienteDTO create(ClienteDTO clienteDTO);

    public Optional<ClienteDTO> update(ClienteDTO cliente, Long id);

    public void delete(Long id);

    public void deleteAll();

//    public Optional<ClienteDTO> findByCedula(String cedula);
    
}
