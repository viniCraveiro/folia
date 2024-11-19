package br.edu.unicesumar.folia.domain.boleto;


import br.edu.unicesumar.folia.controller.boleto.BoletoNapiDTO;
import br.edu.unicesumar.folia.controller.boleto.NapiResponseWrapper;
import br.edu.unicesumar.folia.domain.usuario.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class BoletosNapiService {

    private final BoletoRepository boletoRepository ;

    public BoletosNapiService(BoletoRepository boletoRepository, UsuarioRepository usuarioRepository){
        this.boletoRepository = boletoRepository;
    }

    public List<BoletoNapiDTO> consultarNapiEProcessarDados() {
        /// ADICIONAR URL DO NAPI AQUI, REMOVER ANTES DE COMITAR
        String url = "";
        RestTemplate restTemplate = new RestTemplate();

        try {
            NapiResponseWrapper response = restTemplate.getForObject(url, NapiResponseWrapper.class);

            if (response  != null && response.getValues() != null) {
                    List<BoletoNapiDTO> dto = response.getValues();
                    return dto;
            }
        } catch (Exception e) {
            System.err.println("Erro ao consultar API: " + e.getMessage());
        }
        return null;
    }

}
