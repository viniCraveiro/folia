package br.edu.unicesumar.folia.domain.teste;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TesteEntidade {

    @Test
    void deveTestarGerarUuid() throws IOException {
        ModelEntidadeTeste entidade = new ModelEntidadeTeste();
        UUID uuid = (UUID) entidade.getId();
        String nome;
        Assertions.assertThat(uuid).isNotNull();

        assertNotNull(uuid, "UUID não deve ser nulo");
    }

    @Test
    void deveGerarDezUuid()  throws IOException{
        ArrayList<ModelEntidadeTeste> entidades = new ArrayList<ModelEntidadeTeste>();

        for (int i = 0; i < 10; i++) {
            ModelEntidadeTeste entidade = new ModelEntidadeTeste();
            entidade.setNome("Nome"+1);

            entidades.add(entidade);
        }

        Assertions.assertThat(entidades.size() > 10);

    }
}
