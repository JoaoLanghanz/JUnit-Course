package com.langhanz.service;

import com.langhanz.domain.Usuario;
import com.langhanz.domain.builders.UsuarioBuilder;
import com.langhanz.domain.exception.ValidationException;
import com.langhanz.infra.UsuarioDammyRepository;
import com.langhanz.service.repositories.UsuarioRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class UsuarioServiceTest {

    @Mock private UsuarioRepository repository;
    @InjectMocks private UsuarioService service;


//
//    @BeforeEach
//    public void setup(){
//        MockitoAnnotations.openMocks(this);
//    }

//    @AfterEach
//    public void tearDown(){
//        Mockito.verifyNoMoreInteractions(repository);
//    }

    @Test
    public void deveSalvarUsuarioComSucesso(){
        Usuario user = UsuarioBuilder.umUsuario().comId(null).comEmail("outro@email.com").agora();
        Usuario saverUser = service.salvar(user);
        System.out.println(saverUser);
//        Assertions.assertTrue(saverUser.isPresent());
    }

    @Test
    public void deveRetornarEmptyQuandoUsuarioInexistente(){

        Optional<Usuario> user = service.getUserByEmail("mail@mail.com");
        Assertions.assertTrue(user.isEmpty());
    }

    @Test
    public void deveRetornarUsuarioPorEmail(){

        Mockito.when(repository.getUserByEmail("email@email.com"))
                .thenReturn(Optional.of(UsuarioBuilder.umUsuario().agora()))
                .thenReturn(null);

        Optional<Usuario> user = service.getUserByEmail("email@email.com");
        System.out.println(user);
        user = service.getUserByEmail("email@email.com");
        System.out.println(user);
        Assertions.assertNull(user);

        Mockito.verify(repository, Mockito.atLeastOnce()).getUserByEmail("email@email.com");
        Mockito.verify(repository, Mockito.never()).getUserByEmail("outroEmail@email.com");
    }


    @Test
    public void deveSalvarUsuarioComSucessoMock(){

        Usuario userToSave = UsuarioBuilder.umUsuario().comId(null).agora();

         Mockito.when(repository.getUserByEmail(userToSave.getEmail()))
                 .thenReturn(Optional.empty());

         Mockito.when(repository.salvar(userToSave))
                 .thenReturn(UsuarioBuilder.umUsuario().agora());

         Usuario savedUser = service.salvar(userToSave);
         Assertions.assertNotNull(savedUser.getId());

//         Mockito.verify(repository).getUserByEmail(userToSave.getEmail());
//         Mockito.verify(repository).salvar(userToSave); // Não é necessario pois o assert já garante isso


    }

    @Test
    public void deveRejeitarUsuarioExistente(){
        Usuario userToSave = UsuarioBuilder.umUsuario().comId(null).agora();

        Mockito.when(repository.getUserByEmail(userToSave.getEmail()))
                .thenReturn(Optional.of(UsuarioBuilder.umUsuario().agora()));

        ValidationException ex = Assertions.assertThrows(ValidationException.class, () ->
                service.salvar(userToSave));

        Assertions.assertTrue(ex.getMessage().endsWith("já cadastrado!"));

        Mockito.verify(repository, Mockito.never()).salvar(userToSave);

    }



}
