package com.langhanz.domain;

import java.time.LocalDate;
import java.util.Objects;

public class Transacao {

    private Long id;
    private String descricao;
    private double valor;
    private Conta conta;
    private LocalDate data;
    private boolean status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Conta getConta() {
        return conta;
    }

    public void setConta(Conta conta) {
        this.conta = conta;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Transacao transacao = (Transacao) o;
        return Double.compare(valor, transacao.valor) == 0 && status == transacao.status && Objects.equals(descricao, transacao.descricao) && Objects.equals(conta, transacao.conta) && Objects.equals(data, transacao.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(descricao, valor, conta, data, status);
    }
}
