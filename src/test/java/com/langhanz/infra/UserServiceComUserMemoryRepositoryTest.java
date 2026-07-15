package com.langhanz.infra;

import com.langhanz.domain.Usuario;
import com.langhanz.domain.builders.UsuarioBuilder;
import com.langhanz.domain.exception.ValidationException;
import com.langhanz.service.UsuarioService;
import org.junit.jupiter.api.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserServiceComUserMemoryRepositoryTest {

    private UsuarioService service = new UsuarioService(new UsuarioMemoryRepository());

    @Test
    @Order(1)
    public void deveSalvarUsuarioValido(){
        Usuario user = service.salvar(UsuarioBuilder.umUsuario().comId(null).agora());
        Assertions.assertNotNull(user.getId());
    }

    @Test
    @Order(2)
    public void deveRejeitarUsuarioExistente(){
        ValidationException ex = Assertions.assertThrows(ValidationException.class, () ->
                service.salvar(UsuarioBuilder.umUsuario().comId(null).agora()));
        Assertions.assertEquals("Usuário %s já cadastrado!", ex.getMessage());
    }

}
