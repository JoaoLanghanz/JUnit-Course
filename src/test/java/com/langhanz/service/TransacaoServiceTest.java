package com.langhanz.service;

import com.langhanz.domain.Transacao;
import com.langhanz.domain.builders.TransacaoBuilder;
import com.langhanz.service.repositories.TransacaoDAO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class TransacaoServiceTest {

    @InjectMocks private TransacaoService service;
    @Mock private TransacaoDAO dao;

    @Test
    public void deveCriarUmaTrasacaoValida(){
        Transacao transacaoParaSalvar = TransacaoBuilder.umaTransacao().comId(null).agora();

        Mockito.when(dao.salvar(transacaoParaSalvar))
                .thenReturn(TransacaoBuilder.umaTransacao().agora());

        Transacao transacaoSalva = service.salvar(transacaoParaSalvar);
        Assertions.assertNotNull(transacaoSalva.getId());
        Assertions.assertEquals(TransacaoBuilder.umaTransacao().agora(), transacaoSalva);
        Assertions.assertAll("Transacao",
                () -> Assertions.assertEquals(0L, transacaoSalva.getId()),
                () -> Assertions.assertEquals("transacao valida", transacaoSalva.getDescricao()),
                () -> {
                    Assertions.assertAll("Conta",
                            () -> Assertions.assertEquals("Conta válida", transacaoSalva.getConta().getNome()),
                            () -> {
                                Assertions.assertAll("Usuário",
                                        () -> Assertions.assertEquals("Usuário válido", transacaoSalva.getConta().getUsuario().getNome())
                                        );
                            }
                            );
                }
                );
    }
}
