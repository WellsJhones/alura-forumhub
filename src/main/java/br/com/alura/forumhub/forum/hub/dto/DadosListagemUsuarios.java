package br.com.alura.forumhub.forum.hub.dto;

import br.com.alura.forumhub.forum.hub.domain.usuario.Usuario;

public record DadosListagemUsuarios(String nome, String Email, Long id) {



    public DadosListagemUsuarios(Usuario usuario) {
        this(usuario.getNome(), usuario.getEmail(), usuario.getId());
    }
}
//ahaa