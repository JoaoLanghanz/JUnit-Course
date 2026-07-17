package com.langhanz.service;

import com.langhanz.domain.Conta;
import com.langhanz.domain.builders.ContaBuilder;
import com.langhanz.domain.builders.UsuarioBuilder;
import com.langhanz.domain.exception.ValidationException;
import com.langhanz.service.repositories.ContaRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class ContaServiceTest {

    @Mock private ContaRepository repository;
    @InjectMocks private ContaService service;

    @Test
    public void deveSalvarPrimeiraContaComSucesso(){

        Conta contaToSave = ContaBuilder.umaConta().comId(null).agora();

        Mockito.when(repository.salvar(contaToSave))
                .thenReturn(ContaBuilder.umaConta().agora());

        Conta savedConta = service.salvar(contaToSave);
        Assertions.assertNotNull(savedConta.getId());

//        Mockito.verify(repository, Mockito.atLeastOnce()).salvar(contaToSave);

    }

    @Test
    public void deveSalvarContaSegundaContaComSucesso(){

        Conta contaToSave = ContaBuilder.umaConta().comId(null).agora();

        Mockito.when(repository.obterCotnasPorUsuario(contaToSave.getUsuario().getId()))
                .thenReturn(Arrays.asList(ContaBuilder.umaConta().comNome("Outra conta").agora()));

        Mockito.when(repository.salvar(contaToSave))
                .thenReturn(ContaBuilder.umaConta().agora());

        Conta savedConta = service.salvar(contaToSave);
        Assertions.assertNotNull(savedConta.getId());

    }

    @Test
    public void deveRejeitarContaComNomeExistente(){

        Conta contaToSave = ContaBuilder.umaConta().comId(null).agora();

        Mockito.when(repository.obterCotnasPorUsuario(contaToSave.getUsuario().getId()))
                .thenReturn(Arrays.asList(ContaBuilder.umaConta().agora()));

        String message = Assertions.assertThrows(ValidationException.class, () ->
                service.salvar(contaToSave)).getMessage();

        Assertions.assertEquals("Usuário já possui conta cadastrada.", message);

    }


}
