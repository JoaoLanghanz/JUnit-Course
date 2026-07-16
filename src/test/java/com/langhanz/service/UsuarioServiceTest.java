package com.langhanz.service;

import com.langhanz.domain.Usuario;
import com.langhanz.domain.builders.UsuarioBuilder;
import com.langhanz.infra.UsuarioDammyRepository;
import com.langhanz.service.repositories.UsuarioRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

public class UsuarioServiceTest {

    private UsuarioService service;

    @Test
    public void deveSalvarUsuarioComSucesso(){
        service = new UsuarioService(new UsuarioDammyRepository());
        Usuario user = UsuarioBuilder.umUsuario().comId(null).comEmail("outro@email.com").agora();
        Usuario saverUser = service.salvar(user);
        Assertions.assertNotNull(saverUser.getId());
    }

    @Test
    public void deveRetornarUsuarioPorEmail(){
        UsuarioRepository repository = Mockito.mock(UsuarioRepository.class);
        service = new UsuarioService(repository);

        Optional<Usuario> user = service.getUserByEmail("mail@mail.com");
        Assertions.assertTrue(user.isEmpty());
    }
}
