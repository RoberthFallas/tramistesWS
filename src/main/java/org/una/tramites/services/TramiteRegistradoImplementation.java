package org.una.tramites.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.una.tramites.entities.TramiteRegistrado;
import org.una.tramites.repositories.ITramiteRegistradoRepository;

import java.util.List;
import java.util.Optional;
import org.springframework.transaction.annotation.Transactional;
import org.una.tramites.dto.TramiteRegistradoDTO;
import org.una.tramites.utils.MapperUtils;
@Service
public class TramiteRegistradoImplementation implements  ITramiteRegistradoService {

    @Autowired
    private ITramiteRegistradoRepository tramitesRegistradosRepository;

     private Optional<List<TramiteRegistradoDTO>> findList(List<TramiteRegistrado> list) {
        if (list != null) {
            List<TramiteRegistradoDTO> usuariosDTO = MapperUtils.DtoListFromEntityList(list,TramiteRegistradoDTO.class);
            return Optional.ofNullable(usuariosDTO);
        } else {
            return null;
        }
    }

    private Optional<List<TramiteRegistradoDTO>> findList(Optional<List<TramiteRegistrado>> list) {
        if (list.isPresent()) {
            return findList(list.get());
        } else {
            return null;
        }
    }

    private Optional<TramiteRegistradoDTO> oneToDto(Optional<TramiteRegistrado> one) {
        if (one.isPresent()) {
           TramiteRegistradoDTO usuarioDTO = MapperUtils.DtoFromEntity(one.get(),TramiteRegistradoDTO.class);
            return Optional.ofNullable(usuarioDTO);
        } else {
            return null;
        }
    }


    @Override
 @Transactional(readOnly = true)
    public Optional<List<TramiteRegistradoDTO>> findAll() {
        return  findList(tramitesRegistradosRepository.findAll());
    //    return Optional.ofNullable(tramitesRegistradosRepository.findAll());
    }

     @Override
    @Transactional
    public TramiteRegistradoDTO create(TramiteRegistradoDTO tramiteRegistradoDTO) {
   TramiteRegistrado tramiteRegistrado=MapperUtils.EntityFromDto(tramiteRegistradoDTO,TramiteRegistrado.class);
    tramiteRegistrado=tramitesRegistradosRepository.save(tramiteRegistrado);
    return  MapperUtils.DtoFromEntity(tramiteRegistrado,TramiteRegistradoDTO.class);
    }

     @Override
    @Transactional
    public Optional<TramiteRegistradoDTO> update(TramiteRegistradoDTO tramiteRegistradoDTO, Long id) {

        if (tramitesRegistradosRepository.findById(id).isPresent()) {
          TramiteRegistrado tramiteRegistrado=MapperUtils.EntityFromDto(tramiteRegistradoDTO,TramiteRegistrado.class);
           tramiteRegistrado=tramitesRegistradosRepository.save(tramiteRegistrado);
           return Optional.ofNullable(MapperUtils.DtoFromEntity(tramiteRegistrado,TramiteRegistradoDTO.class));
        } else {
            return null;
        }
    }

    @Override
    public void delete(Long id) {
        tramitesRegistradosRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        tramitesRegistradosRepository.deleteAll();
    }

    @Override
    public Optional<TramiteRegistradoDTO> findById(Long id) {
        return oneToDto(tramitesRegistradosRepository.findById(id));
    }
}
