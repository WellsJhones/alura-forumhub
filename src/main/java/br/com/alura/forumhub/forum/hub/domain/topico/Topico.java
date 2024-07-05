package br.com.alura.forumhub.forum.hub.domain.topico;

import br.com.alura.forumhub.forum.hub.dto.DadosAtualizacaoTopico;
import br.com.alura.forumhub.forum.hub.dto.DadosCadastroTopico;
import br.com.alura.forumhub.forum.hub.repository.CursoRepository;
import br.com.alura.forumhub.forum.hub.repository.UsuarioRepository;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "postagens")
@Entity(name = "Topico")
public class Topico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String mensagem;
    @Column(name = "curso")
    private String curso;
    private boolean ativo = true;
    @Column(name = "autor")
    private String autor;
    private String data_postagem;


    public void atualizarInformacoes(DadosAtualizacaoTopico dados) {
        if(dados.titulo() != null){
            this.titulo = dados.titulo();
        }
        if(dados.mensagem() != null){
            this.mensagem = dados.mensagem();
        }
    }

    public void excluir() {
        this.ativo = false;
    }
}
