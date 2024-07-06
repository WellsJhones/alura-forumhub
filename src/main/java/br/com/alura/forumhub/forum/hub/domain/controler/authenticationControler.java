package br.com.alura.forumhub.forum.hub.domain.controler;

import br.com.alura.forumhub.forum.hub.domain.usuario.Usuario;
import br.com.alura.forumhub.forum.hub.dto.DadosAutenticacao;
import br.com.alura.forumhub.forum.hub.infra.security.DadosTokenJWT;
import br.com.alura.forumhub.forum.hub.infra.security.TokenService;
import jakarta.annotation.PostConstruct;
import jakarta.validation.Valid;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authorization.method.AuthorizeReturnObject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class authenticationControler {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;
    @PostConstruct
    public void init() {
        assert manager != null : "AuthenticationManager is not initialized";
    }

    @PostMapping
    public ResponseEntity login(@RequestBody @Valid DadosAutenticacao dados){
        assert dados != null : "DadosAutenticacao is null";
        assert dados.email() != null : "Email is null";
        assert dados.senha() != null : "Senha is null";
        var tokenJwt = new UsernamePasswordAuthenticationToken(dados.email(), dados.senha());
        assert tokenJwt != null : "UsernamePasswordAuthenticationToken is null";
        var authentication = manager.authenticate(tokenJwt);
        var token =tokenService.gerarToken((Usuario)authentication.getPrincipal());
        return ResponseEntity.ok(new DadosTokenJWT(token));
    }
}
//ahaa