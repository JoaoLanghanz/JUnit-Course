package com.langhanz.infra;

import com.langhanz.domain.Usuario;
import com.langhanz.service.repositories.UsuarioRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UsuarioMemoryRepository implements UsuarioRepository {

    private List<Usuario> users;
    private Long currentId;

    public UsuarioMemoryRepository(){
        currentId = 0L;
        users = new ArrayList<>();
        salvar(new Usuario(null, "User #1", "user1@email.com", "123456"));
    }

    @Override
    public Usuario salvar(Usuario usuario) {
        Usuario usr = new Usuario(nextId(), usuario.getNome(), usuario.getEmail(), usuario.getSenha());
        users.add(usr);
        return usr;
    }

    @Override
    public Optional<Usuario> getUserByEmail(String email) {
        return users.stream()
                .filter(user -> user.getEmail().equalsIgnoreCase(email))
                .findFirst();

    }

    private Long nextId(){
        return ++currentId;
    }
}
