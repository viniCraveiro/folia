package br.edu.unicesumar.folia.domain.configuracaobanco;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ConfiguracaoBancoRepository extends JpaRepository<ConfiguracaoBanco, UUID> {
}
