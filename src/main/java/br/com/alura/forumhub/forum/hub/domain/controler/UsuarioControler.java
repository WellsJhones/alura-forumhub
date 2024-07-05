package br.com.alura.forumhub.forum.hub.domain.controler;

import br.com.alura.forumhub.forum.hub.dto.*;
import br.com.alura.forumhub.forum.hub.repository.UsuarioRepository;
import br.com.alura.forumhub.forum.hub.domain.usuario.Usuario;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/usuarios")
public class UsuarioControler {
    @Autowired
   private UsuarioRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity<Object> cadastrar(@RequestBody DadosCadastroUsuario dados, UriComponentsBuilder uriComponentsBuilder) {
        Usuario criado = new Usuario(dados);
        repository.save(criado);
        var uri = uriComponentsBuilder.path("/usuarios/{id}").buildAndExpand(criado.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoUsuario(criado));
    }
    @GetMapping
    public ResponseEntity <Page<DadosListagemUsuarios>> listar(Pageable paginacao){
        var page = repository.findAllByAtivoTrue(paginacao).map(DadosListagemUsuarios::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoUsuario dados){
        var usuario  = repository.getReferenceById(dados.id());
        usuario.atualizarInformacoes(dados);

        return ResponseEntity.ok(new DadosDetalhamentoUsuario(usuario));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deletar(@PathVariable Long id){
        var usuario = repository.getReferenceById(id);
        usuario.excluir();
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable Long id){
        var usuario = repository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoUsuario(usuario));
    }

}
