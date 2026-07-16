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
    public void deveRetornarEmptyQuandoUsuarioInexistente(){
        UsuarioRepository repository = Mockito.mock(UsuarioRepository.class);
        service = new UsuarioService(repository);

        Optional<Usuario> user = service.getUserByEmail("mail@mail.com");
        Assertions.assertTrue(user.isEmpty());
    }

    @Test
    public void deveRetornarUsuarioPorEmail(){
        UsuarioRepository repository = Mockito.mock(UsuarioRepository.class);
        service = new UsuarioService(repository);

        Mockito.when(repository.getUserByEmail("email@email.com"))
                .thenReturn(Optional.of(UsuarioBuilder.umUsuario().agora()))
                .thenReturn(null);

        Optional<Usuario> user = service.getUserByEmail("email@email.com");
        System.out.println(user);
        user = service.getUserByEmail("email@email.com");
        System.out.println(user);
        Assertions.assertTrue(user.isPresent());

        Mockito.verify(repository, Mockito.atLeastOnce()).getUserByEmail("email@email.com");
    }


    @Test
    public void deveSalvarUsuarioComSucessoMock(){
        UsuarioRepository repository = Mockito.mock(UsuarioRepository.class);
        service = new UsuarioService(repository);

        Usuario userToSave = UsuarioBuilder.umUsuario().comId(null).agora();

         Mockito.when(repository.getUserByEmail(userToSave.getEmail()))
                 .thenReturn(Optional.empty());

         Mockito.when(repository.salvar(userToSave))
                 .thenReturn(UsuarioBuilder.umUsuario().agora());

         Usuario savedUser = service.salvar(userToSave);
         Assertions.assertNotNull(savedUser.getId());

         Mockito.verify(repository).getUserByEmail(userToSave.getEmail());
//         Mockito.verify(repository).salvar(userToSave); // Não é necessario pois o assert já garante isso


    }



}
