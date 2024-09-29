package br.edu.unicesumar.folia.domain.boleto;


import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class BoletoService {
    private final BoletoRepository boletoRepository ;

    public BoletoService(BoletoRepository boletoRepository){this.boletoRepository = boletoRepository;}

    public Boleto salvarBoleto(Boleto boleto){
        return boletoRepository.save(boleto);
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
        boleto.setStatus(novoStatus);  // Atualiza o status do boleto
        boletoRepository.save(boleto);  // Salva a alteração
    }

}
