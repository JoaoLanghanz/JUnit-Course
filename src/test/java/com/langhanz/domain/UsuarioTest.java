package com.langhanz.domain;

import com.langhanz.domain.builders.UsuarioBuilder;
import com.langhanz.domain.exception.ValidationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

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

    @Test
    @DisplayName("Deve rejeitar usuário com email nulo")
    public void deveRejeitarUsuarioComEmailNulo(){
        ValidationException ex = Assertions.assertThrows(ValidationException.class, () ->
                UsuarioBuilder.umUsuario().comEmail(null).agora()
        );
        assertEquals("email é obrigatório.", ex.getMessage());
    }

    @Test
    @DisplayName("Deve rejeitar usuário com senha nulo")
    public void deveRejeitarUsuarioComSenhaNulo(){
        ValidationException ex = Assertions.assertThrows(ValidationException.class, () ->
                UsuarioBuilder.umUsuario().comSenha(null).agora()
        );
        assertEquals("senha é obrigatório.", ex.getMessage());
    }

    @ParameterizedTest(name = "[{index}] - {4}")
    @DisplayName("Deve validar campos obrigatórios")
    @CsvSource(value ={
            "1, NULL,email@email.com,123123,Nome é obrigatório.",
            "1,joao,NULL,123123,email é obrigatório.",
            "1,joao,email@email,NULL,senha é obrigatório."
    }, nullValues = "NULL")
    public void deveValidarCamposObrigatorios(Long id, String nome, String email, String senha, String mensagem){
        ValidationException ex = Assertions.assertThrows(ValidationException.class, () ->
                UsuarioBuilder.umUsuario().comId(id).comNome(nome).comEmail(email).comSenha(senha).agora());
        assertEquals(mensagem, ex.getMessage());
    }
}








