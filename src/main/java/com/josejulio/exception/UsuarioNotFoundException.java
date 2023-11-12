package com.josejulio.exception;

public class UsuarioNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1l;

    public UsuarioNotFoundException(Long id) {
        super("Usuário não encontrado com o id: " + id);
    }

}
