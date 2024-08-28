package br.edu.unicesumar.folia.domain.usuario;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario salvaUsuario(Usuario usuario){
        if(ValidarIdentificacao.validarCPF(usuario.getIdentificacao()) || ValidarIdentificacao.validarCNPJ(usuario.getIdentificacao())){
            return usuarioRepository.save(usuario);
        }
        throw new RuntimeException("Identificação nõ é valida!");
    };

    public void deletaUsuario(UUID uuid){
        Usuario usuario = usuarioRepository.findById(uuid).orElseThrow(EntityNotFoundException::new);
        usuarioRepository.delete(usuario);

    }

    public Usuario atualizaUsuario(UUID uuid, Usuario usuarioAtualizado) {
        Usuario usuarioExistente = usuarioRepository.findById(uuid).orElseThrow(EntityNotFoundException::new);
        usuarioExistente.setIdentificacao((usuarioAtualizado.getIdentificacao()));
        usuarioExistente.setNome(usuarioAtualizado.getNome());
        usuarioExistente.setEmail(usuarioAtualizado.getEmail());
        return usuarioRepository.save(usuarioExistente);
    }
}
