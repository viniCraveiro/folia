package br.edu.unicesumar.folia.controller.usuario;

import br.edu.unicesumar.folia.controller.empresa.EmpresaInformacaoDTO;
import br.edu.unicesumar.folia.domain.empresa.Empresa;
import br.edu.unicesumar.folia.domain.empresa.EmpresaRepository;
import br.edu.unicesumar.folia.domain.usuario.Usuario;
import br.edu.unicesumar.folia.domain.usuario.UsuarioRepository;
import br.edu.unicesumar.folia.domain.usuario.UsuarioService;
import br.edu.unicesumar.folia.exception.UuidNotFoundException;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Tag(
        name = "Usuario",

        description = "CRUD REST - create Usuario, Update Usuario, Delete Usuario"

)
@RestController
@RequestMapping("api/usuario")
public class UsuarioRestController {

    private final UsuarioService usuarioService;

    private final UsuarioRepository repository;

    private final EmpresaRepository empresaRepository;

    public UsuarioRestController(UsuarioService usuarioService, UsuarioRepository repository, EmpresaRepository empresaRepository) {
        this.usuarioService = usuarioService;
        this.repository = repository;
        this.empresaRepository = empresaRepository;
    }

    @PostMapping("/empresauuid:{empresaUuid}")
    public ResponseEntity cadastrar(@RequestBody Usuario usuario, @PathVariable UUID empresaUuid) {
        Empresa empresa = empresaRepository.getReferenceById(empresaUuid);
        usuario.setEmpresa(empresa);
        usuarioService.salvaUsuario(usuario);
        return new ResponseEntity<>(null, HttpStatus.CREATED);
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity deletar(@PathVariable UUID uuid) {
        usuarioService.deletaUsuario(uuid);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{uuid}")
    public ResponseEntity atualizar(@PathVariable UUID uuid, @RequestBody Usuario usuario) {
        UsuarioDetailDTO dto = new UsuarioDetailDTO(usuarioService.atualizaUsuario(uuid, usuario));
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<Page<Usuario>> listar(Pageable paginacao) {
        var page = repository.findAll(paginacao).map(Usuario::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/buscarPorId:{id}")
    public ResponseEntity<UsuarioDetailDTO> buscarPorId(@PathVariable UUID id) {
        Optional<Usuario> usuario = usuarioService.buscarPorId(id);
        if (usuario.isPresent()) {
            UsuarioDetailDTO dto = new UsuarioDetailDTO(usuario.get());
            return ResponseEntity.ok(dto);
        }
        return ResponseEntity.notFound().build();
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

    @PostMapping("/empresas/{usuarioUUID}")
    public ResponseEntity<EmpresaInformacaoDTO> empresaVinculada(@PathVariable UUID usuarioUUID) {
        Optional<Usuario> usuarioOptional = repository.findById(usuarioUUID);
        if (usuarioOptional.isPresent()) {
            Empresa empresa = usuarioOptional.get().getEmpresa();
            if (empresa != null) {
                return ResponseEntity.ok(new EmpresaInformacaoDTO(empresa.getNomeFantasia(), empresa.getId()));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }

    @PostMapping("/filtrar")
    public ResponseEntity<List<UsuarioDTO>> filtrarUsuario(@RequestBody UsuarioFiltroDTO filtro) {
        if (filtro.getEmpresaUUID() == null) {
            throw new UuidNotFoundException("O UUID da empresa é obrigatório.");
        }
        return ResponseEntity.ok(usuarioService.buscarComFiltro(filtro));
    }

}
