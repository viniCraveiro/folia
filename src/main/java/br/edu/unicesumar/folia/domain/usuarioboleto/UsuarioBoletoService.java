package br.edu.unicesumar.folia.domain.usuarioboleto;

import br.edu.unicesumar.folia.controller.usuarioboleto.UsuarioBoletoFiltroDTO;
import br.edu.unicesumar.folia.controller.usuarioboleto.UsuarioBoletoListaDTO;
import br.edu.unicesumar.folia.domain.boleto.Boleto;
import br.edu.unicesumar.folia.domain.boleto.BoletoRepository;
import br.edu.unicesumar.folia.domain.boleto.Status;
import br.edu.unicesumar.folia.domain.usuario.Usuario;
import br.edu.unicesumar.folia.domain.usuario.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class UsuarioBoletoService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private BoletoRepository boletoRepository;

    public UsuarioBoletoListaDTO obterDadosUsuarioBoletos(UUID usuarioId) {
        Usuario usuario = usuarioRepository.findById(usuarioId).orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));

        List<Boleto> boletos = boletoRepository.findByUsuarioId(usuarioId, Pageable.unpaged()).getContent();

        Long quantidadeBoletos = (long) boletos.size();
        Long quantidadeBoletosAbertos = boletos.stream().filter(boleto -> boleto.getStatus() == Status.ABERTO).count();
        Long quantidadeBoletosVencidos = boletos.stream().filter(boleto -> boleto.getDataVencimento().isBefore(LocalDate.now())).count();

        UsuarioBoletoListaDTO dto = new UsuarioBoletoListaDTO();
        dto.setIdentificacao(usuario.getIdentificacao());
        dto.setNome(usuario.getNome());
        dto.setUsuario(usuario.getUsuario());
        dto.setQuantidadeBoletos(quantidadeBoletos);
        dto.setQuantidadeBoletosAbertos(quantidadeBoletosAbertos);
        dto.setQuantidadeBoletosVencidos(quantidadeBoletosVencidos);

        return dto;
    }

    public List<Boleto> filtrarBoletos(UsuarioBoletoFiltroDTO filtro) {
        List<Boleto> boletos = boletoRepository.findAll(); // Obtendo todos os boletos

        if (filtro.getUsuarioId() != null) {
            boletos = boletos.stream()
                    .filter(b -> b.getUsuario().getId().equals(filtro.getUsuarioId()))
                    .collect(Collectors.toList());
        }

        if (filtro.getIdentificacao() != null && !filtro.getIdentificacao().isEmpty()) {
            boletos = boletos.stream()
                    .filter(b -> b.getUsuario().getIdentificacao().equals(filtro.getIdentificacao()))
                    .collect(Collectors.toList());
        }

        if (filtro.getNome() != null && !filtro.getNome().isEmpty()) {
            boletos = boletos.stream()
                    .filter(b -> b.getUsuario().getNome().equalsIgnoreCase(filtro.getNome()))
                    .collect(Collectors.toList());
        }

        if (filtro.getDataInicialEmissao() != null) {
            boletos = boletos.stream()
                    .filter(b -> !b.getDataEmissao().isBefore(filtro.getDataInicialEmissao()))
                    .collect(Collectors.toList());
        }

        if (filtro.getDataFinalEmissao() != null) {
            boletos = boletos.stream()
                    .filter(b -> !b.getDataEmissao().isAfter(filtro.getDataFinalEmissao()))
                    .collect(Collectors.toList());
        }

        if (filtro.getDataInicialVencimento() != null) {
            boletos = boletos.stream()
                    .filter(b -> !b.getDataVencimento().isBefore(filtro.getDataInicialVencimento()))
                    .collect(Collectors.toList());
        }

        if (filtro.getDataFinalVencimento() != null) {
            boletos = boletos.stream()
                    .filter(b -> !b.getDataVencimento().isAfter(filtro.getDataFinalVencimento()))
                    .collect(Collectors.toList());
        }

        if (filtro.getStatus() != null) {
            boletos = boletos.stream()
                    .filter(b -> b.getStatus().equals(filtro.getStatus()))
                    .collect(Collectors.toList());
        }

        return boletos;
    }
}
