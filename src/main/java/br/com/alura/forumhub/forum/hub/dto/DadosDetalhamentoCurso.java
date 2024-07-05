package br.com.alura.forumhub.forum.hub.dto;

import br.com.alura.forumhub.forum.hub.domain.curso.Curso;

public record DadosDetalhamentoCurso(Long id, String nome, String categoria) {
    public DadosDetalhamentoCurso(Curso curso) {
        this(curso.getId(), curso.getNome(), curso.getCategoria().name());
    }
}
