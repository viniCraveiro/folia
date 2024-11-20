package br.edu.unicesumar.folia.domain.boleto;


import br.edu.unicesumar.folia.controller.boleto.BoletoInformacoesDTO;
import br.edu.unicesumar.folia.controller.boleto.BoletoListaDTO;
import br.edu.unicesumar.folia.controller.usuarioboleto.EmpresaBoletoFiltradoDTO;
import br.edu.unicesumar.folia.controller.usuarioboleto.UsuarioBoletoFiltradoDTO;
import br.edu.unicesumar.folia.controller.usuarioboleto.BoletoFiltroDTO;
import br.edu.unicesumar.folia.controller.usuarioboleto.UsuarioBoletoListaDTO;
import br.edu.unicesumar.folia.domain.usuario.Usuario;
import br.edu.unicesumar.folia.domain.usuario.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class BoletoService {
    private final BoletoRepository boletoRepository ;
    private final UsuarioRepository usuarioRepository;

    public BoletoService(BoletoRepository boletoRepository, UsuarioRepository usuarioRepository){this.boletoRepository = boletoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public Boleto salvarBoleto(Boleto boleto){
        return boletoRepository.save(boleto);
    }

    public Page<BoletoListaDTO> listarBoletosPorUsuario(UUID usuarioId, Pageable pageable) {
        Page<Boleto> boletosPage = boletoRepository.findByUsuarioId(usuarioId, pageable);

        // Mapeia os boletos para o DTO
        return boletosPage.map(boleto -> {
            BoletoListaDTO dto = new BoletoListaDTO();
            dto.alimentarDados(boleto); // Preenche os dados do DTO
            return dto;
        });
    }
    // todos os boletos
    public List<Boleto> listarBoletos() {
        return boletoRepository.findAll();
    }

    // Buscar um boleto por ID (UUID)
    public Boleto buscarBoleto(UUID uuid) {
        return boletoRepository.findById(uuid).orElseThrow(EntityNotFoundException::new);
    }

    // Atualizar apenas o status do boleto
    public void atualizarStatusBoleto(UUID uuid, String novoStatus) {
        Boleto boleto = boletoRepository.findById(uuid).orElseThrow(EntityNotFoundException::new);
        boleto.setStatus(Status.valueOf(novoStatus));  // Atualiza o status do boleto
        boletoRepository.save(boleto);  // Salva a alteração
    }

    public BoletoInformacoesDTO listarBoletosPorEmpresa(UUID uuid) {
        List<Boleto> boletos = boletoRepository.findByEmpresaId(uuid);

        long totalBoletos = boletos.size();
        long boletosAbertos = boletos.stream().filter(boleto -> boleto.getStatus() == Status.ABERTO).count();
        long boletosVencidos = boletos.stream().filter(boleto -> boleto.getStatus() == Status.VENCIDO).count();

        // Definir o formato da data
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        // Contagem de boletos próximos ao vencimento
        long boletosProximosVencimento = boletos.stream()
                .filter(boleto -> {
                    LocalDate dataVencimento = boleto.getDataVencimento();
                    return !dataVencimento.isBefore(LocalDate.now()) && dataVencimento.isBefore(LocalDate.now().plusDays(7));
                }).count();


        BoletoInformacoesDTO boletoDTO = new BoletoInformacoesDTO();
        boletoDTO.setQuantidadeBoletos(totalBoletos);
        boletoDTO.setQuantidadeBoletosAberto(boletosAbertos);
        boletoDTO.setQuantidadeBoletosVencido(boletosVencidos);
        boletoDTO.setQuantidadeBoletosProximosVencimento(boletosProximosVencimento);

        return boletoDTO;
    }

    public List<UsuarioBoletoListaDTO> obterDadosUsuariosPorEmpresa(UUID empresaId) {
        // Busca todos os usuários associados à empresa
        List<Usuario> usuarios = usuarioRepository.findByEmpresaId(empresaId);

        // Para cada usuário, busca os boletos e compila as informações no DTO
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

    public List<UsuarioBoletoFiltradoDTO> buscarComFiltroUsuario(BoletoFiltroDTO filtro) {
        Specification<Boleto> specification = BoletoFiltragem.boletoUsuarioComFiltros(filtro);

        // Definir o critério de ordenação (por data de emissão, por exemplo)
        Sort sort = Sort.by(Sort.Direction.ASC, "dataEmissao");

        // Buscar boletos filtrados com a especificação e a ordenação
        List<Boleto> boletosFiltrados = boletoRepository.findAll(specification, sort);

        // Mapear os boletos filtrados para o DTO
        return boletosFiltrados.stream()
                .map(this::toDTOUsuario)
                .collect(Collectors.toList());
    }

    public List<EmpresaBoletoFiltradoDTO> buscarComFiltroEmpresa(BoletoFiltroDTO filtro) {
        Specification<Boleto> specification = BoletoFiltragem.boletoEmpresaComFiltros(filtro);

        // Definir o critério de ordenação (por data de emissão, por exemplo)
        Sort sort = Sort.by(Sort.Direction.ASC, "dataEmissao");

        // Buscar boletos filtrados com a especificação e a ordenação
        List<Boleto> boletosFiltrados = boletoRepository.findAll(specification, sort);

        // Mapear os boletos filtrados para o DTO
        return boletosFiltrados.stream()
                .map(this::toDTOEmpresa)
                .collect(Collectors.toList());
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

    private UsuarioBoletoFiltradoDTO toDTOUsuario(Boleto boleto) {
        return new UsuarioBoletoFiltradoDTO(
                boleto.getId(),
                boleto.getBanco().getNome(),
                boleto.getValor(),
                boleto.getTotalParcelas(),
                boleto.getDataEmissao(),
                boleto.getDataVencimento(),
                boleto.getStatus(),
                boleto.getUrl()
        );
    }

    private EmpresaBoletoFiltradoDTO toDTOEmpresa(Boleto boleto) {
        return new EmpresaBoletoFiltradoDTO(
                boleto.getId(),
                boleto.getUsuario().getIdentificacao(),
                boleto.getUsuario().getNome(),
                boleto.getUsuario().getUsername(),
                boleto.getBanco().getNome(),
                boleto.getValor(),
                boleto.getTotalParcelas(),
                boleto.getDataEmissao(),
                boleto.getDataVencimento(),
                boleto.getStatus(),
                boleto.getUrl()
        );
    }

}

