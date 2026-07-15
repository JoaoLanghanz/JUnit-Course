package com.langhanz.domain;

import com.langhanz.domain.builders.UsuarioBuilder;
import com.langhanz.domain.exception.ValidationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Domínio usuário")
public class UsuarioTest {

    @Test
    @DisplayName("Deve criar um usuário válido")
    public void deveCriarUsuarioValido(){

        Usuario usr = UsuarioBuilder.umUsuario().agora();

        Assertions.assertAll("Usuário",
            () -> assertEquals(1L, usr.getId()),
            () -> assertEquals("Usuário válido", usr.getNome()),
            () -> assertEquals("email@email.com", usr.getEmail()),
            () -> assertEquals("123123", usr.getSenha())
        );
    }

    @Test
    @DisplayName("Deve rejeitar usuário com nome nulo")
    public void deveRejeitarUsuarioComNomeNulo(){
        ValidationException ex = Assertions.assertThrows(ValidationException.class, () ->
                UsuarioBuilder.umUsuario().comNome(null).agora()
        );
        assertEquals("Nome é obrigatório.", ex.getMessage());
    }
}
