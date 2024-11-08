package br.edu.unicesumar.folia.domain.usuarioboleto;

import br.edu.unicesumar.folia.controller.usuarioboleto.UsuarioBoletoFiltradoDTO;
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
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
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
        dto.setUsuario(usuario.getUsername());
        dto.setQuantidadeBoletos(quantidadeBoletos);
        dto.setQuantidadeBoletosAbertos(quantidadeBoletosAbertos);
        dto.setQuantidadeBoletosVencidos(quantidadeBoletosVencidos);

        return dto;
    }

    public List<UsuarioBoletoFiltradoDTO> buscarComFiltro(UsuarioBoletoFiltroDTO filtro) {
        Specification<Boleto> specification = UsuarioBoletoFiltragem.comFiltros(filtro);

        // Definir o critério de ordenação (por data de emissão, por exemplo)
        Sort sort = Sort.by(Sort.Direction.ASC, "dataEmissao");

        // Buscar boletos filtrados com a especificação e a ordenação
        List<Boleto> boletosFiltrados = boletoRepository.findAll(specification, sort);

        // Mapear os boletos filtrados para o DTO
        return boletosFiltrados.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    private UsuarioBoletoFiltradoDTO toDTO(Boleto boleto) {
        return new UsuarioBoletoFiltradoDTO(
                boleto.getUsuario().getIdentificacao(),
                boleto.getUsuario().getNome(),
                boleto.getUsuario().getUsername(),
                boleto.getBanco().getNome(),
                boleto.getValor(),
                boleto.getTotalParcelas(),
                boleto.getDataEmissao(),
                boleto.getDataVencimento(),
                boleto.getStatus()
        );

    }
}
