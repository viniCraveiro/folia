package br.edu.unicesumar.folia.domain.usuario;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario salvaUsuario(Usuario usuario){
        usuario.setIdentificacao(ValidarIdentificacao.removeNonDigits(usuario.getIdentificacao()));
        if (ValidarIdentificacao.validarCPF(usuario.getIdentificacao()) || ValidarIdentificacao.validarCNPJ(usuario.getIdentificacao())) {
            if (ValidarIdentificacao.validarSenha(usuario.getSenha())) {
                usuarioRepository.save(usuario);
                return usuario;
            }
            throw new RuntimeException("Senha não é válida!");
        }
        throw new RuntimeException("Identificação não é válida!");
    };

    public void deletaUsuario(UUID uuid){
        Usuario usuario = usuarioRepository.findById(uuid).orElseThrow(EntityNotFoundException::new);
        usuarioRepository.delete(usuario);

    }

    public Usuario atualizaUsuario(UUID uuid, Usuario usuarioAtualizado) {
        Usuario usuarioExistente = usuarioRepository.findById(uuid).orElseThrow(EntityNotFoundException::new);
        usuarioExistente.setIdentificacao(usuarioAtualizado.getIdentificacao());
        usuarioExistente.setNome(usuarioAtualizado.getNome());
        usuarioExistente.setEmail(usuarioAtualizado.getEmail());
        usuarioExistente.setUsuario(usuarioAtualizado.getUsuario());
        usuarioExistente.setSenha(usuarioAtualizado.getSenha());
        usuarioExistente.setEndereco(usuarioAtualizado.getEndereco());
        salvaUsuario(usuarioExistente);
        return usuarioExistente;
    }

    public Usuario findUsuarioByIdentificacao(String identificacao) {
        return usuarioRepository.findByIdentificacao(identificacao);
    }
}
