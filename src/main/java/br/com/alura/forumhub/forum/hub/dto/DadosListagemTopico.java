package br.com.alura.forumhub.forum.hub.dto;

import br.com.alura.forumhub.forum.hub.domain.topico.Topico;

public record DadosListagemTopico(String titulo, String mensagem, String autor,String curso) {


    public DadosListagemTopico(Topico topico) {
        this(topico.getTitulo(), topico.getMensagem(), topico.getAutor(), topico.getCurso());
    }
}
