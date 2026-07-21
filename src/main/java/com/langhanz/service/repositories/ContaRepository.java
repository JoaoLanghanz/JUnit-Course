package com.langhanz.service.repositories;

import com.langhanz.domain.Conta;
import com.langhanz.domain.Usuario;

import java.util.List;
import java.util.Optional;

public interface ContaRepository {

    Conta salvar(Conta c) throws Exception;

    List<Conta> obterCotnasPorUsuario(Long usuarioId);

    void delete(Conta conta);
}
