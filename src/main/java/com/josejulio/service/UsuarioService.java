package com.josejulio.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;

import com.josejulio.dto.UsuarioDTO;
import com.josejulio.dto.mapper.UsuarioMapper;
import com.josejulio.exception.UsuarioNotFoundException;
import com.josejulio.repository.UsuarioRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Validated
@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioMapper usuarioMapper;

    public List<UsuarioDTO> findAll() {
        return usuarioRepository.findAll()
                .stream()
                .map(usuarioMapper::toDTO)
                .collect(Collectors.toList());
    }

    public UsuarioDTO findById(@PathVariable @NotNull @Positive Long id) {
        return usuarioRepository.findById(id).map(usuarioMapper::toDTO)
                .orElseThrow(() -> new UsuarioNotFoundException(id));
    }

    public UsuarioDTO save(@Valid UsuarioDTO usuarioDTO) {
        return usuarioMapper.toDTO(usuarioRepository.save(usuarioMapper.toEntity(usuarioDTO)));
    }

    public UsuarioDTO update(@NotNull @Positive Long id, UsuarioDTO usuarioDTO) {
        return usuarioRepository.findById(id)
                .map(result -> {
                    result.setNome(usuarioDTO.getNome());
                    result.setEmail(usuarioDTO.getEmail());
                    return usuarioMapper.toDTO(usuarioRepository.save(result));
                }).orElseThrow(() -> new UsuarioNotFoundException(id));
    }

    public void delete(@NotNull @Positive Long id) {
        usuarioRepository.delete(usuarioRepository.findById(id)
                .orElseThrow(() -> new UsuarioNotFoundException(id)));
    }

}
