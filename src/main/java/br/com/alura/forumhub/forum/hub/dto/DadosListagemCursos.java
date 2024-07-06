package br.com.alura.forumhub.forum.hub.dto;

import br.com.alura.forumhub.forum.hub.domain.curso.Curso;

public record DadosListagemCursos(String nome, String categoria, Long id) {

    public DadosListagemCursos(Curso curso) {
        this(curso.getNome(), String.valueOf(curso.getCategoria()), curso.getId());
    }
}
//ahaa