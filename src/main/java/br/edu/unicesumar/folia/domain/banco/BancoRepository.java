package br.edu.unicesumar.folia.domain.banco;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface BancoRepository extends JpaRepository<Banco, UUID> {
    Optional<Banco> findByAgenciaAndConta(String agencia, String conta);
}
