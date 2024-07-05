package br.com.alura.forumhub.forum.hub.domain.controler;

import br.com.alura.forumhub.forum.hub.domain.topico.Topico;
import br.com.alura.forumhub.forum.hub.dto.*;
import br.com.alura.forumhub.forum.hub.repository.TopicoRepository;
import br.com.alura.forumhub.forum.hub.service.TopicoService;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/topicos")
public class TopicoControler {
    @Autowired
    private TopicoRepository repository;
    @Autowired
    private TopicoService topicoService;

    @PostMapping
    @Transactional
    public ResponseEntity<Object> cadastrar(@RequestBody DadosCadastroTopico dados, UriComponentsBuilder uriComponentsBuilder){
        Topico criado = topicoService.createTopico(dados);
        var uri = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(criado.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoTopico(criado));
    }
    @GetMapping
    public ResponseEntity <Page<DadosListagemTopico>> listar(Pageable paginacao){
        var page = repository.findAllByAtivoTrue(paginacao).map(DadosListagemTopico::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<Object> atualizar(@RequestBody @Valid DadosAtualizacaoTopico dados){
        var topico  = repository.getReferenceById(dados.id());
        topico.atualizarInformacoes(dados);
        return ResponseEntity.ok(new DadosDetalhamentoTopico(topico));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deletar(@PathVariable Long id){
        var topico = repository.getReferenceById(id);
        topico.excluir();
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable Long id){
        var topico = repository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoTopico(topico));
    }
}
