package br.edu.unicesumar.folia.domain.usuarioboleto;

import br.edu.unicesumar.folia.controller.usuarioboleto.UsuarioBoletoListaDTO;
import br.edu.unicesumar.folia.domain.boleto.Boleto;
import br.edu.unicesumar.folia.domain.boleto.BoletoRepository;
import br.edu.unicesumar.folia.domain.boleto.Status;
import br.edu.unicesumar.folia.domain.usuario.Usuario;
import br.edu.unicesumar.folia.domain.usuario.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class UsuarioBoletoService {

    @Autowired
    private UsuarioRepository usuarioRepository; // Você precisa criar esse repositório
    @Autowired
    private BoletoRepository boletoRepository;

    public UsuarioBoletoListaDTO obterDadosUsuarioBoletos(UUID usuarioId) {
        // 1. Buscar o usuário pelo ID
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));

        // 2. Buscar os boletos do usuário
        List<Boleto> boletos = boletoRepository.findByUsuarioId(usuarioId, Pageable.unpaged()).getContent();

        // 3. Calcular as quantidades de boletos
        Long quantidadeBoletos = (long) boletos.size();
        Long quantidadeBoletosAbertos = boletos.stream()
                .filter(boleto -> boleto.getStatus() == Status.ABERTO) // Substitua pelo seu status
                .count();
        Long quantidadeBoletosVencidos = boletos.stream()
                .filter(boleto -> boleto.getDataVencimento().isBefore(LocalDate.now())) // Verifica vencimento
                .count();

        // 4. Criar e retornar o DTO
        UsuarioBoletoListaDTO dto = new UsuarioBoletoListaDTO();
        dto.setIdentificacao(usuario.getIdentificacao());
        dto.setNome(usuario.getNome());
        dto.setUsuario(usuario.getUsuario());
        dto.setQuantidadeBoletos(quantidadeBoletos);
        dto.setQuantidadeBoletosAbertos(quantidadeBoletosAbertos);
        dto.setQuantidadeBoletosVencidos(quantidadeBoletosVencidos);

        return dto;
    }
}
