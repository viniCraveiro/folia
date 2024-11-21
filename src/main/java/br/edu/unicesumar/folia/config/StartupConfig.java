package br.edu.unicesumar.folia.config;

import br.edu.unicesumar.folia.controller.boleto.BoletoNapiDTO;
import br.edu.unicesumar.folia.domain.boleto.BoletoService;
import br.edu.unicesumar.folia.domain.boleto.BoletosNapiService;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class StartupConfig {

    private final BoletosNapiService service;

    public StartupConfig(BoletosNapiService service) {
        this.service = service;
    }

    @Bean
    public ApplicationRunner onStartup() {
        return args -> {
            List<BoletoNapiDTO> dtos = service.consultarNapiEProcessarDados();
        };
    }
}
