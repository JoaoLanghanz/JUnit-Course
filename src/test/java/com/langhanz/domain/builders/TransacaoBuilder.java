package com.langhanz.domain.builders;

import java.lang.Long;
import java.util.Arrays;
import java.time.LocalDate;
import com.langhanz.domain.Conta;
import java.lang.String;
import com.langhanz.domain.Transacao;


public class TransacaoBuilder {
    private Transacao elemento;
    private TransacaoBuilder(){}

    public static TransacaoBuilder umaTransacao() {
        TransacaoBuilder builder = new TransacaoBuilder();
        inicializarDadosPadroes(builder);
        return builder;
    }

    public static void inicializarDadosPadroes(TransacaoBuilder builder) {
        builder.elemento = new Transacao();
        Transacao elemento = builder.elemento;


        elemento.setId(0L);
        elemento.setDescricao("transacao valida");
        elemento.setValor(0.0);
        elemento.setConta(ContaBuilder.umaConta().agora());
        elemento.setData(LocalDate.now());
        elemento.setStatus(false);
    }

    public TransacaoBuilder comId(Long param) {
        elemento.setId(param);
        return this;
    }

    public TransacaoBuilder comDescricao(String param) {
        elemento.setDescricao(param);
        return this;
    }

    public TransacaoBuilder comValor(double param) {
        elemento.setValor(param);
        return this;
    }

    public TransacaoBuilder comConta(Conta param) {
        elemento.setConta(param);
        return this;
    }

    public TransacaoBuilder comData(LocalDate param) {
        elemento.setData(param);
        return this;
    }

    public TransacaoBuilder comStatus(boolean param) {
        elemento.setStatus(param);
        return this;
    }

    public Transacao agora() {
        return elemento;
    }
}
