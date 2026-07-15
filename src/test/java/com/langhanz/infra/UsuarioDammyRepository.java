package com.langhanz.infra;

import com.langhanz.domain.Usuario;
import com.langhanz.domain.builders.UsuarioBuilder;
import com.langhanz.service.repositories.UsuarioRepository;

import java.util.Optional;

public class UsuarioDammyRepository implements UsuarioRepository {
    @Override
    public Usuario salvar(Usuario usuario) {
        return UsuarioBuilder.umUsuario()
                .comNome(usuario.getNome())
                .comEmail(usuario.getEmail())
                .comSenha(usuario.getSenha())
            .agora();
    }

    @Override
    public Optional<Usuario> getUserByEmail(String email) {
        if("mail@email.com".equals(email))
            return Optional.of(UsuarioBuilder.umUsuario().comEmail(email).agora());
        else
            return Optional.empty();
    }
}
