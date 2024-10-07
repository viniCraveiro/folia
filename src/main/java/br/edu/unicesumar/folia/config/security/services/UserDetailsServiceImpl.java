package br.edu.unicesumar.folia.config.security.services;

import br.edu.unicesumar.folia.domain.usuario.Usuario;
import br.edu.unicesumar.folia.domain.usuario.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
     UsuarioRepository usuarioRepository;


    @Override
    @Transactional
    public UserDetails loadUserByUsername(String idendificacao) throws UsernameNotFoundException {
        Usuario user = usuarioRepository.findByIdentificacao(idendificacao)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario não encontrado com indentificação: " + idendificacao));

        return UserDetailsImpl.build(user);
    }
}
