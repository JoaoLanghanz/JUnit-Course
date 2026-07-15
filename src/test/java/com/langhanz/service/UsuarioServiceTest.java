package com.langhanz.service;

import com.langhanz.domain.Usuario;
import com.langhanz.domain.builders.UsuarioBuilder;
import com.langhanz.infra.UsuarioDammyRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UsuarioServiceTest {

    private UsuarioService service;

    @Test
    public void deveSalvarUsuarioComSucesso(){
        service = new UsuarioService(new UsuarioDammyRepository());
        Usuario user = UsuarioBuilder.umUsuario().comId(null).comEmail("outro@email.com").agora();
        Usuario saverUser = service.salvar(user);
        Assertions.assertNotNull(saverUser.getId());
    }
}
