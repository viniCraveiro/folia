package br.edu.unicesumar.folia.domain.boleto;


import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class BoletoService {
    private final BoletoRepository boletoRepository ;

    public BoletoService(BoletoRepository boletoRepository){this.boletoRepository = boletoRepository;}

    public Boleto criarBolete(Boleto boleto){
        return boletoRepository.save(boleto);
    }

    public Boleto findById(UUID uuid) {
        return boletoRepository.findById(uuid).orElseThrow(EntityNotFoundException::new);
    }

    public void deletarBoleto(UUID uuid){
        Boleto boleto = boletoRepository.findById(uuid).orElseThrow(EntityNotFoundException::new);
        boletoRepository.delete(boleto);
    }

    public Boleto atualizarBolete(UUID uuid, Boleto boletoAtualizado){
       throw new RuntimeException("Não Implementado");

    }

}
