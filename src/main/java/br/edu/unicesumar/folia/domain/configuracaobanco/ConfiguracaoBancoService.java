package br.edu.unicesumar.folia.domain.configuracaobanco;

import org.springframework.stereotype.Service;

@Service
public class ConfiguracaoBancoService {

    private final ConfiguracaoBancoRepository configuracaoBancoRepository ;

    public ConfiguracaoBancoService(ConfiguracaoBancoRepository configuracaoBancoRepository){this.configuracaoBancoRepository = configuracaoBancoRepository;}

}
