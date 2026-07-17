package com.langhanz.service;

import com.langhanz.domain.Conta;
import com.langhanz.domain.Usuario;
import com.langhanz.domain.exception.ValidationException;
import com.langhanz.service.repositories.ContaRepository;

import java.util.List;
import java.util.Optional;

public class ContaService {

    private ContaRepository repository;

    public ContaService(ContaRepository repository){
        this.repository = repository;
    }

    public Conta salvar(Conta conta){

        List<Conta> contas = repository.obterCotnasPorUsuario(conta.getUsuario().getId());

        contas.stream().forEach(cnt -> {
            if(conta.getNome().equalsIgnoreCase(cnt.getNome()))
                throw new ValidationException("Usuário já possui conta cadastrada.");
        });

        return repository.salvar(conta);
    }

//    public Optional<Conta> getContaByNome(String nome){
//        return repository.getContaByNome(nome);
//    }

}
