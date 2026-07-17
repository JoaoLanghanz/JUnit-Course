package com.langhanz.service.repositories;

import com.langhanz.domain.Conta;
import com.langhanz.domain.Usuario;

import java.util.List;
import java.util.Optional;

public interface ContaRepository {

    public Conta salvar(Conta c);

    List<Conta> obterCotnasPorUsuario(Long usuarioId);
}
