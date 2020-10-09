/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tramites.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.una.tramites.dto.ClienteDTO;
import org.una.tramites.entities.Cliente;
import org.una.tramites.repositories.IClienteRepository;
import org.una.tramites.utils.MapperUtils;

/**
 *
 * @author LordLalo
 */
@Service
public class ClienteServiceImplementation implements IClienteServices {

    @Autowired
    private IClienteRepository clienteRepository;

    private Optional<List<ClienteDTO>> findList(List<Cliente> list) {
        if (list != null) {
            List<ClienteDTO> clienteDTO = MapperUtils.DtoListFromEntityList(list, ClienteDTO.class);
            return Optional.ofNullable(clienteDTO);
        } else {
            return null;
        }
    }

    private Optional<List<ClienteDTO>> findList(Optional<List<Cliente>> list) {
        if (list.isPresent()) {
            return findList(list.get());
        } else {
            return null;
        }
    }

    private Optional<ClienteDTO> oneToDto(Optional<Cliente> one) {
        if (one.isPresent()) {
            ClienteDTO clienteDTO = MapperUtils.DtoFromEntity(one.get(), ClienteDTO.class);
            return Optional.ofNullable(clienteDTO);
        } else {
            return null;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<ClienteDTO>> findAll() {
        return findList(clienteRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ClienteDTO> findById(Long id) {
        return oneToDto(clienteRepository.findById(id));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<ClienteDTO>> findByCedulaAproximate(String cedula) {
        return findList(clienteRepository.findByCedulaContaining(cedula));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<ClienteDTO>> findByNombreCompletoAproximateIgnoreCase(String nombreCompleto) {
        return findList(clienteRepository.findByNombreCompletoContainingIgnoreCase(nombreCompleto));
    }

    @Override
    @Transactional
    public ClienteDTO create(ClienteDTO clienteDTO) {
        Cliente cliente = MapperUtils.EntityFromDto(clienteDTO, Cliente.class);
        cliente = clienteRepository.save(cliente);
        return MapperUtils.DtoFromEntity(cliente, ClienteDTO.class);
    }

    @Override
    @Transactional
    public Optional<ClienteDTO> update(ClienteDTO clienteDTO, Long id) {
        if (clienteRepository.findById(id).isPresent()) {
            Cliente cliente = MapperUtils.EntityFromDto(clienteDTO, Cliente.class);
            cliente = clienteRepository.save(cliente);
            return Optional.ofNullable(MapperUtils.DtoFromEntity(cliente, ClienteDTO.class));
        } else {
            return null;
        }

    }

    @Override
    @Transactional
    public void delete(Long id) {
        clienteRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteAll() {
        clienteRepository.deleteAll();
    }

}
