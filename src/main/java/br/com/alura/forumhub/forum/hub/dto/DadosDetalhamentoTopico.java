package br.com.alura.forumhub.forum.hub.dto;

import br.com.alura.forumhub.forum.hub.domain.topico.Topico;

public record DadosDetalhamentoTopico( String titulo, String mensagem, String autor, String data_postagem, String status) {

    public DadosDetalhamentoTopico(Topico topico) {
        this(topico.getTitulo(), topico.getMensagem(), topico.getAutor(),topico.getData_postagem(), String.valueOf(topico.getStatus()));
    }
}
