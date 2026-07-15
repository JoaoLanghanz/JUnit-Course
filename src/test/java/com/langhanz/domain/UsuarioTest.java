package com.langhanz.domain;

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

        assertEquals(1L, usr.getId());
        assertEquals("Usuario valido", usr.getNome());
        assertEquals("user@email.com", usr.getEmail());
        assertEquals("123456", usr.getSenha());



    }
}
