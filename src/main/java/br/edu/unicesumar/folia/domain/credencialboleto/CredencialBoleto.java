package br.edu.unicesumar.folia.domain.credencialboleto;

import br.edu.unicesumar.folia.domain.Entidade;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Table(name = "CREDENCIALBOLETO")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CredencialBoleto extends Entidade {

    @Column
    private String clientId;
    @Column
    private String clientSecret;
    @Column
    private String keyUser;
    @Column
    private String scope;
    @Column
    private int indicadorPix;

}
