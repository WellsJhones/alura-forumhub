package br.com.alura.forumhub.forum.hub.dto;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoCursos(
        @NotNull Long id, String nome
) {

}
//ahaa