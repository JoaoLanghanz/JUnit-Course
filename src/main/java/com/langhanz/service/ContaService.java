package com.langhanz.service;

import com.langhanz.domain.Conta;
import com.langhanz.domain.Usuario;
import com.langhanz.domain.exception.ValidationException;
import com.langhanz.service.external.ContaEvent;
import com.langhanz.service.repositories.ContaRepository;

import java.util.List;
import java.util.Optional;

public class ContaService {

    private ContaRepository repository;
    private ContaEvent event;

    public ContaService(ContaRepository repository, ContaEvent event){
        this.repository = repository;
        this.event = event;
    }

    public Conta salvar(Conta conta) throws Exception{

        List<Conta> contas = repository.obterCotnasPorUsuario(conta.getUsuario().getId());

        contas.stream().forEach(cnt -> {
            if(conta.getNome().equalsIgnoreCase(cnt.getNome()))
                throw new ValidationException("Usuário já possui conta cadastrada.");
        });

        Conta contaPersistida = repository.salvar(conta);
        try {
            event.dispatch(contaPersistida, ContaEvent.EventType.CREATED);
        }catch (Exception e){
            repository.delete(contaPersistida);
            throw new RuntimeException("Falha na criacao da conta.");
        }

        return contaPersistida;
    }

//    public Optional<Conta> getContaByNome(String nome){
//        return repository.getContaByNome(nome);
//    }

}
