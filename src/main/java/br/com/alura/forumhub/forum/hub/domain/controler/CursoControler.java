package br.com.alura.forumhub.forum.hub.domain.controler;


import br.com.alura.forumhub.forum.hub.domain.curso.Curso;
import br.com.alura.forumhub.forum.hub.dto.DadosDetalhamentoCurso;
import br.com.alura.forumhub.forum.hub.dto.DadosAtualizacaoCursos;
import br.com.alura.forumhub.forum.hub.dto.DadosCadastroCurso;
import br.com.alura.forumhub.forum.hub.dto.DadosListagemCursos;
import br.com.alura.forumhub.forum.hub.repository.CursoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/cursos")
public class CursoControler {
    @Autowired
   private CursoRepository repository;

        @PostMapping
        @Transactional
        public ResponseEntity<DadosDetalhamentoCurso> cadastrar(@RequestBody DadosCadastroCurso dados, UriComponentsBuilder uriComponentsBuilder) {
            Curso criado = new Curso(dados);
            repository.save(criado);
            var uri = uriComponentsBuilder.path("/cursos/{id}").buildAndExpand(criado.getId()).toUri();
           return ResponseEntity.created(uri).body(new DadosDetalhamentoCurso(criado));


        }
    @GetMapping
    public ResponseEntity<Page<DadosListagemCursos>> listar(Pageable pageable ){
        var page = repository.findAllByAtivoTrue(pageable).map(DadosListagemCursos::new);
    return ResponseEntity.ok(page);}

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoCursos dados){
            var curso = repository.getReferenceById(dados.id());
            curso.atualizarInformacoes(dados);

        return ResponseEntity.ok(new DadosDetalhamentoCurso(curso));
    }
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deletar(@PathVariable Long id){
        var curso = repository.getReferenceById(id);
        curso.excluir();
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable Long id){

        var curso = repository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoCurso(curso));
    }


}
