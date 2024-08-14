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
        return usuarioRepository.save(usuario);
    };
    public void deletaUsuario(UUID uuid){
        Usuario usuario = usuarioRepository.findById(uuid).orElseThrow(EntityNotFoundException::new);
        usuarioRepository.delete(usuario);

    }
}
