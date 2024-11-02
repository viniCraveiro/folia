package br.edu.unicesumar.folia.domain.boleto;


import br.edu.unicesumar.folia.controller.Conversor;
import br.edu.unicesumar.folia.controller.boleto.BoletoInformacoesDTO;
import br.edu.unicesumar.folia.controller.boleto.BoletoListaDTO;
import br.edu.unicesumar.folia.domain.usuario.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
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
}

