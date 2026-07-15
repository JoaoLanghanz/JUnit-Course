package com.langhanz.domain.builders;

import com.langhanz.domain.Usuario;

public class UsuarioBuilder {

    private Long id;
    private String nome;
    private String email;
    private String senha;

    private UsuarioBuilder(){}

    public static UsuarioBuilder umUsuario(){
        UsuarioBuilder builder = new UsuarioBuilder();
        inicializaDados(builder);
        return builder;
    }

    private static void inicializaDados(UsuarioBuilder builder){
        builder.id = 1L;
        builder.nome = "Usuário válido";
        builder.email = "email@email.com";
        builder.senha = "123123";
    }

    public UsuarioBuilder comId(Long param){
        id = param;
        return this;
    }

    public UsuarioBuilder comNome(String param){
        nome = param;
        return this;
    }

    public UsuarioBuilder comEmail(String param){
        email = param;
        return this;
    }

    public UsuarioBuilder comSenha(String param){
        senha = param;
        return this;
    }

    public Usuario agora(){
        return new Usuario(id, nome, email, senha);
    }
}
