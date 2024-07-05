package br.com.alura.forumhub.forum.hub.dto;

import br.com.alura.forumhub.forum.hub.domain.topico.Topico;

public record DadosDetalhamentoTopico(Long id, String titulo, String mensagem, String curso, String autor, String data_postagem) {

    public DadosDetalhamentoTopico(Topico topico) {
        this(topico.getId(), topico.getTitulo(), topico.getMensagem(), topico.getCurso(), topico.getAutor(),topico.getData_postagem());
    }
}
