package br.com.alura.forumhub.forum.hub.dto;

import br.com.alura.forumhub.forum.hub.domain.curso.Categoria;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroCurso (
        @NotBlank String nome,
        @NotNull Categoria categoria) {
}
