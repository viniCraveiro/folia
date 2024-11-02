package br.edu.unicesumar.folia.domain.boleto;

import br.edu.unicesumar.folia.domain.usuario.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface BoletoRepository extends JpaRepository<Boleto, UUID> {
    Page<Boleto> findByUsuarioId(UUID usuarioId, Pageable pageable);
    List<Boleto> findByEmpresaId(UUID empresaId);

}
