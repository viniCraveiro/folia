package br.edu.unicesumar.folia.domain.boleto;


import br.edu.unicesumar.folia.domain.usuario.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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

    public Page<Boleto> listar(UUID usuarioId, Pageable pageable) {
        return boletoRepository.findByUsuarioId(usuarioId, pageable);
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

}
