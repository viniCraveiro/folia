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
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class UsuarioBoletoService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private BoletoRepository boletoRepository;

    public List<UsuarioBoletoListaDTO> obterDadosUsuariosPorEmpresa(UUID empresaId) {
        List<Usuario> usuarios = usuarioRepository.findByEmpresaId(empresaId);

        return usuarios.stream().map(usuario -> {
            List<Boleto> boletos = boletoRepository.findByUsuarioId(usuario.getId(), Pageable.unpaged()).getContent();

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
        }).collect(Collectors.toList());
    }

    public List<UsuarioBoletoListaDTO> obterUltimosCincoUsuariosPorBoletos(UUID empresaId) {
        List<Usuario> usuarios = usuarioRepository.findByEmpresaId(empresaId);

        return usuarios.stream().map(usuario -> {
            Pageable pageable = PageRequest.of(0, 5, Sort.by(Sort.Order.desc("dataVencimento"))); // Ordenando por data de vencimento, do mais recente ao mais antigo
            List<Boleto> boletos = boletoRepository.findByUsuarioId(usuario.getId(), pageable).getContent();

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
        }).collect(Collectors.toList());
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
                boleto.getId(),
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
