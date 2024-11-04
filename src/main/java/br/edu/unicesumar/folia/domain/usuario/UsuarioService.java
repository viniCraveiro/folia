package br.edu.unicesumar.folia.domain.usuario;


import br.edu.unicesumar.folia.controller.usuario.UsuarioResponseDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario salvaUsuario(Usuario usuario) {
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

    public void deletaUsuario(UUID uuid) {
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

    public Optional<Usuario> buscarPorId(UUID id) {
        return usuarioRepository.findById(id);
    }

    public Page<Usuario> buscarPorNome(String nome, Pageable pageable) {
        return usuarioRepository.findByNomeContaining(nome, pageable);
    }

    public Optional<Usuario> buscarPorIdentificacao(String identificacao) {
        return usuarioRepository.findByIdentificacao(identificacao);
    }

    public UsuarioResponseDTO validaAcesso(String identificacao, String senha) {
        UsuarioResponseDTO response = new UsuarioResponseDTO();

        Optional<Usuario> optionalUsuario = usuarioRepository.findByIdentificacao(identificacao);

        if (optionalUsuario.isPresent() && optionalUsuario.get().getSenha().equals(senha)) {
            Usuario usuario = optionalUsuario.get();
            response.setUuid(usuario.getId().toString());
            response.setNome(usuario.getNome());
            response.setValid(true);
            response.setTipoUsuario(usuario.getTipoUsuario());
        } else {
            response.setValid(false);
        }
        return response;
    }




}