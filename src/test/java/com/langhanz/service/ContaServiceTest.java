package com.langhanz.service;

import com.langhanz.domain.Conta;
import com.langhanz.domain.builders.ContaBuilder;
import com.langhanz.domain.builders.UsuarioBuilder;
import com.langhanz.domain.exception.ValidationException;
import com.langhanz.service.external.ContaEvent;
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
    @Mock private ContaEvent event;
    @InjectMocks private ContaService service;

    @Test
    public void deveSalvarPrimeiraContaComSucesso() throws Exception{

        Conta contaToSave = ContaBuilder.umaConta().comId(null).agora();
        Mockito.doNothing().when(event).dispatch(ContaBuilder.umaConta().agora(), ContaEvent.EventType.CREATED);

        Mockito.when(repository.salvar(contaToSave))
                .thenReturn(ContaBuilder.umaConta().agora());

        Conta savedConta = service.salvar(contaToSave);
        Assertions.assertNotNull(savedConta.getId());

    }

    @Test
    public void deveSalvarContaSegundaContaComSucesso() throws Exception{

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


    @Test
    public void naoDeveManterContaSemEvento() throws Exception{
        Conta contaToSave = ContaBuilder.umaConta().comId(null).agora(); // Uma conta nova
        Conta savedConta = ContaBuilder.umaConta().agora(); // Já cria a conta salva, com id
        Mockito.when(repository.salvar(contaToSave)) // simula o salvamento de uma conta nova. Quando uma conta nova, com id null, for passada
                .thenReturn(savedConta); // Então retorna uma conta criada, com id gerado

        Mockito.doThrow(new Exception("Falha catastrófica."))
                .when(event).dispatch(ContaBuilder.umaConta().agora(), ContaEvent.EventType.CREATED);

        String mensagem = Assertions.assertThrows(Exception.class, () ->
                service.salvar(contaToSave)).getMessage();
        Assertions.assertEquals("Falha na criacao da conta.", mensagem);

        Mockito.verify(repository).delete(savedConta);


    }


}
