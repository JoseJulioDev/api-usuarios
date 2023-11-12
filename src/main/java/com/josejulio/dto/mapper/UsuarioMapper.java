package com.josejulio.dto.mapper;

import org.springframework.stereotype.Component;

import com.josejulio.dto.UsuarioDTO;
import com.josejulio.model.Usuario;

@Component
public class UsuarioMapper {

    public UsuarioDTO toDTO(Usuario usuario) {
        if (usuario == null) {
            return null;
        }

        return new UsuarioDTO(usuario.getId(), usuario.getNome(), usuario.getEmail(), usuario.getSenha(),
                usuario.getConfirmacaoSenha());
    }

    public Usuario toEntity(UsuarioDTO usuarioDTO) {
        if (usuarioDTO == null) {
            return null;
        }

        return new Usuario(usuarioDTO.getId(), usuarioDTO.getNome(), usuarioDTO.getEmail(), usuarioDTO.getSenha(),
                usuarioDTO.getConfirmacaoSenha());
    }
}
