package com.langhanz.domain;

import com.langhanz.domain.builders.ContaBuilder;
import com.langhanz.domain.builders.UsuarioBuilder;
import com.langhanz.domain.exception.ValidationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class ContaTest {

    @Test
    public void deveCriarContaValida(){
        // cria conta
        Conta conta = ContaBuilder.umaConta().agora();
        //Assertivas em cima da conta
        Assertions.assertAll("Conta",
                () -> Assertions.assertEquals(1L, conta.getId()),
                () -> Assertions.assertEquals("Conta válida", conta.getNome()),
                () -> Assertions.assertEquals(UsuarioBuilder.umUsuario().agora(), conta.getUsuario())

        );
    }

    @ParameterizedTest
    @MethodSource(value = "dataProvider")
    public void deveRejeitarContaInvalida(Long id, String nome, Usuario usuario, String mensagem){
        String errMessage = Assertions.assertThrows(ValidationException.class, () ->
                ContaBuilder.umaConta().comId(id).comNome(nome).comUsuario(usuario).agora()).getMessage();
        Assertions.assertEquals(mensagem, errMessage);
    }

    private static Stream<Arguments> dataProvider(){
        return Stream.of(
                Arguments.of(1L, null, UsuarioBuilder.umUsuario().agora(), "Nome é obrigatório"),
                Arguments.of(1L, "Conta válida", null, "Usuário é obrigatório")
        );
    }






}
