package com.josejulio.service;

import com.josejulio.dto.UsuarioDTO;
import com.josejulio.exception.UsuarioNotFoundException;
import com.josejulio.model.Usuario;
import com.josejulio.repository.UsuarioRepository;
import com.josejulio.dto.mapper.UsuarioMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private UsuarioMapper usuarioMapper;

    @InjectMocks
    private UsuarioService usuarioService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAll() {
        when(usuarioRepository.findAll()).thenReturn(Arrays.asList(new Usuario(), new Usuario()));
        when(usuarioMapper.toDTO(any())).thenReturn(new UsuarioDTO());

        assertEquals(2, usuarioService.findAll().size());
    }

    @Test
    void testFindById() {
        Long userId = 1L;
        Usuario usuario = new Usuario();
        usuario.setId(userId);

        when(usuarioRepository.findById(userId)).thenReturn(Optional.of(usuario));
        when(usuarioMapper.toDTO(usuario))
                .thenReturn(new UsuarioDTO(userId, "Teste", "teste@teste.com", "senha", "senha"));

        UsuarioDTO result = usuarioService.findById(userId);

        assertEquals(userId, result.getId());
    }

    @Test
    void testFindByIdNotFound() {
        Long userId = 1L;

        when(usuarioRepository.findById(userId)).thenReturn(Optional.empty());

        assertThrows(UsuarioNotFoundException.class, () -> usuarioService.findById(userId));
    }

    @Test
    void testSave() {
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        Usuario usuario = new Usuario();

        when(usuarioMapper.toEntity(usuarioDTO)).thenReturn(usuario);
        when(usuarioRepository.save(usuario)).thenReturn(usuario);
        when(usuarioMapper.toDTO(usuario)).thenReturn(usuarioDTO);

        UsuarioDTO result = usuarioService.save(usuarioDTO);

        assertNotNull(result);
    }

    @Test
    void testUpdate() {
        Long userId = 1L;
        UsuarioDTO usuarioDTO = new UsuarioDTO(userId, "Teste", "teste@teste.com", "senha", "senha");
        Usuario existingUsuario = new Usuario();

        when(usuarioRepository.findById(userId)).thenReturn(Optional.of(existingUsuario));
        when(usuarioRepository.save(existingUsuario)).thenReturn(existingUsuario);
        when(usuarioMapper.toDTO(existingUsuario)).thenReturn(usuarioDTO);

        UsuarioDTO result = usuarioService.update(userId, usuarioDTO);

        assertNotNull(result);
        assertEquals("Teste", result.getNome());
        assertEquals("teste@teste.com", result.getEmail());
    }

    @Test
    void testUpdateNotFound() {
        Long userId = 1L;
        UsuarioDTO usuarioDTO = new UsuarioDTO();

        when(usuarioRepository.findById(userId)).thenReturn(Optional.empty());

        assertThrows(UsuarioNotFoundException.class, () -> usuarioService.update(userId, usuarioDTO));
    }

    @Test
    void testDelete() {
        Long userId = 1L;

        when(usuarioRepository.findById(userId)).thenReturn(Optional.of(new Usuario()));

        assertDoesNotThrow(() -> usuarioService.delete(userId));
        verify(usuarioRepository, times(1)).delete(any());
    }

    @Test
    void testDeleteNotFound() {
        Long userId = 1L;

        when(usuarioRepository.findById(userId)).thenReturn(Optional.empty());

        assertThrows(UsuarioNotFoundException.class, () -> usuarioService.delete(userId));
    }
}