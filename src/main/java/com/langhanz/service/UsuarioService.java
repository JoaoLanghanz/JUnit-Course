package com.langhanz.service;

import com.langhanz.domain.Usuario;
import com.langhanz.domain.exception.ValidationException;
import com.langhanz.service.repositories.UsuarioRepository;

public class UsuarioService {

    private UsuarioRepository repository;

    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    public Usuario salvar(Usuario usuario){
        repository.getUserByEmail(usuario.getEmail()).ifPresent(user -> {
            throw new ValidationException(String.format("Usuário %s já cadastrado!", usuario.getEmail()));
        });
        return repository.salvar(usuario);
    }
}
