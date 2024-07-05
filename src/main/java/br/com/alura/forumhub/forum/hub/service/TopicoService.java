package br.com.alura.forumhub.forum.hub.service;

import br.com.alura.forumhub.forum.hub.domain.topico.Topico;
import br.com.alura.forumhub.forum.hub.domain.topico.TopicoStatus;
import br.com.alura.forumhub.forum.hub.dto.DadosCadastroTopico;
import br.com.alura.forumhub.forum.hub.repository.CursoRepository;
import br.com.alura.forumhub.forum.hub.repository.TopicoRepository;
import br.com.alura.forumhub.forum.hub.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class TopicoService {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CursoRepository cursoRepository;

    public Topico createTopico(DadosCadastroTopico dados) {
        Topico topico = new Topico();
        topico.setAtivo(true);
        topico.setTitulo(dados.titulo());
        topico.setMensagem(dados.mensagem());
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        topico.setData_postagem(now.format(formatter));

        topico.setStatus(TopicoStatus.NAO_RESPONDIDO);

        if(usuarioRepository.findById(dados.usuarioid()).isPresent()){
            topico.setAutor(usuarioRepository.findById(dados.usuarioid()).get().getNome());
        }else{
            throw new IllegalArgumentException("Autor não encontrado");
        }

        if(cursoRepository.findById(dados.cursoid()).isPresent()){
            topico.setCurso(cursoRepository.findById(dados.cursoid()).get().getNome());
        }else{
            throw new IllegalArgumentException("Curso não encontrado");
        }

        return topicoRepository.save(topico);
    }

}