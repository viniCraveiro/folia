package br.edu.unicesumar.folia.controller.usuario;

import br.edu.unicesumar.folia.domain.usuario.Usuario;
import br.edu.unicesumar.folia.domain.usuario.UsuarioRepository;
import br.edu.unicesumar.folia.domain.usuario.UsuarioService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Tag(
        name = "Usuario",

        description = "CRUD REST - create Usuario, Update Usuario, Delete Usuario"

)
@RestController
@RequestMapping("api/usuario")
public class UsuarioRestController {

    private final UsuarioService usuarioService;

    private final UsuarioRepository repository;

    public UsuarioRestController(UsuarioService usuarioService, UsuarioRepository repository) {
        this.usuarioService = usuarioService;
        this.repository = repository;
    }

    @PostMapping
    public ResponseEntity cadastrar(@RequestBody @Valid Usuario usuario){
        usuarioService.salvaUsuario(usuario);

        return new ResponseEntity<>(null, HttpStatus.CREATED);
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity deletar(@PathVariable UUID uuid){
        usuarioService.deletaUsuario(uuid);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{uuid}")
    public ResponseEntity atualizar(@PathVariable UUID uuid, @RequestBody Usuario usuario){
        usuarioService.atualizaUsuario(uuid, usuario);
        return ResponseEntity.ok(usuario);
    }

    @GetMapping
    public ResponseEntity<Page<Usuario>> listar(Pageable paginacao){
        var page = repository.findAll(paginacao).map(Usuario::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/buscarPorId/{id}")
    public ResponseEntity<Usuario> buscarPorId(@PathVariable UUID id) {
        Optional<Usuario> usuario = usuarioService.buscarPorId(id);
        return usuario.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/buscarPorNome") //Utilizar ?nome={nome}&page=0&size=5
    public ResponseEntity<Page<Usuario>> buscarPorNome(String nome, @PageableDefault(size = 10) Pageable pageable) {
        Page<Usuario> usuarios = usuarioService.buscarPorNome(nome, pageable);
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/buscarPorIdentificacao")//Utilizar ?identificacao={identificacao}
    public ResponseEntity<Usuario> buscarPorIdentificacao(@RequestParam String identificacao) {
        Optional<Usuario> usuario = usuarioService.buscarPorIdentificacao(identificacao);
        return usuario.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }



    @PostMapping("/validarLogin")
    public ResponseEntity<UsuarioResponseDTO> validarAcesso(@RequestBody UsuarioLoginDTO usuarioLogin) {
        UsuarioResponseDTO response = usuarioService.validaAcesso(usuarioLogin.getIdentificacao(), usuarioLogin.getSenha());

        if (response.isValid()) {
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
    }


}
