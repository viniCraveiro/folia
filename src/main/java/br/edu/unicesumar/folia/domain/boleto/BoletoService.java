package br.edu.unicesumar.folia.domain.boleto;


import br.edu.unicesumar.folia.domain.usuario.Usuario;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
public class BoletoService {
    private final BoletoRepository boletoRepository ;

    public BoletoService(BoletoRepository boletoRepository){this.boletoRepository = boletoRepository;}

    public void deletarBoleto(UUID uuid){
        Boleto boleto = boletoRepository.findById(uuid).orElseThrow(EntityNotFoundException::new);
        boletoRepository.delete(boleto);
    }

}
