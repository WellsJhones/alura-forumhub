package br.com.alura.forumhub.forum.hub.domain.curso;

import br.com.alura.forumhub.forum.hub.dto.DadosAtualizacaoCursos;
import br.com.alura.forumhub.forum.hub.dto.DadosCadastroCurso;
import jakarta.persistence.*;
import lombok.*;

@Table(name = "cursos")
@Entity(name = "Curso")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    @Enumerated(EnumType.STRING)
    private Categoria categoria;
    private boolean ativo = true;

    public Curso(DadosCadastroCurso dados) {
        this.ativo = true;
        this.nome = dados.nome();
        this.categoria = dados.categoria();
    }

//ahaa

    public void atualizarInformacoes(DadosAtualizacaoCursos dados){
        if(dados.nome() != null){
            this.nome = dados.nome();
        }

    }

    @Override
    public String toString() {
        return "Curso{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", categoria=" + categoria +
                '}';
    }

    public void excluir() {
        this.ativo = false;
    }
}
