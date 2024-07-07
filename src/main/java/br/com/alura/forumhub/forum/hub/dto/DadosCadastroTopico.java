package br.com.alura.forumhub.forum.hub.dto;

import jakarta.validation.constraints.NotBlank;

public record DadosCadastroTopico(
        @NotBlank String titulo,
        @NotBlank String mensagem,
        @NotBlank Long usuarioid,
        @NotBlank Long cursoid
        ){}
//ahaa