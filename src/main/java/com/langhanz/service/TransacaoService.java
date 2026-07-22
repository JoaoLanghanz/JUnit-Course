package com.langhanz.service;

import com.langhanz.domain.Transacao;
import com.langhanz.domain.exception.ValidationException;
import com.langhanz.service.repositories.TransacaoDAO;

public class TransacaoService {

    private TransacaoDAO dao;

    public Transacao salvar(Transacao transacao){
        if(transacao.getDescricao() == null) throw new ValidationException("Descricao inexistente.");
        if(transacao.getConta() == null) throw new ValidationException("Descricao inexistente.");
//        if(transacao.getValor() == null) throw new ValidationException("Descricao inexistente.");
        if(transacao.getData() == null) throw new ValidationException("Descricao inexistente.");
        if(transacao.isStatus() == false) transacao.setStatus(false);

        return dao.salvar(transacao);
    }
}
