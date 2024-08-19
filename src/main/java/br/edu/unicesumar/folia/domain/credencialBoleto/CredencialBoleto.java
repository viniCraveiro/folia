package br.edu.unicesumar.folia.domain.credencialBoleto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.util.UUID;

@Table(name = "CREDENCIALBOLETO")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CredencialBoleto {

    @Id
    @Setter(AccessLevel.NONE)
    private UUID uuid;
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
