package br.edu.unicesumar.folia.domain.teste;

import br.edu.unicesumar.folia.domain.Entidade;
import br.edu.unicesumar.folia.domain.endereco.Endereco;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;


@Table(name = "USUARIO")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
class ModelEntidadeTeste extends Entidade {

    @Column
    private String nome;


}