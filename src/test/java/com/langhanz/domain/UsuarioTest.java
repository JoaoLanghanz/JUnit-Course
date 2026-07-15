package com.langhanz.domain;

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
        Usuario usr = new Usuario(1L, "Usuario valido", "user@email.com", "123456");

        Assertions.assertAll(
            () -> assertEquals(1L, usr.getId()),
            () -> assertEquals("Usuario valido", usr.getNome()),
            () -> assertEquals("user@email.com", usr.getEmail()),
            () -> assertEquals("123456", usr.getSenha())
        );
    }

    @Test
    @DisplayName("Deve rejeitar usuário com nome nulo")
    public void deveRejeitarUsuarioComNomeNulo(){
        ValidationException ex = Assertions.assertThrows(ValidationException.class, () ->
                new Usuario(1L, null, "email@email.com", "123123")
        );
        assertEquals("Nome é obrigatório.", ex.getMessage());
    }
}
