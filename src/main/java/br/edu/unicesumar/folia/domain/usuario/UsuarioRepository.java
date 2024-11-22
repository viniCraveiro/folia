package br.edu.unicesumar.folia.domain.usuario;

import br.edu.unicesumar.folia.controller.usuario.UsuarioDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, UUID>, JpaSpecificationExecutor<Usuario> {

    Optional<Usuario> findByIdentificacao(String identificacao);

    Page<Usuario> findByNomeContaining(String nome, Pageable pageable);

    List<Usuario> findByEmpresaId(UUID empresaId);

    @Query("SELECT new br.edu.unicesumar.folia.controller.usuario.UsuarioDTO( " +
            "b.usuario.id, " +
            "b.usuario.identificacao, " +
            "b.usuario.nome, " +
            "COUNT(b.id) AS boletosTotal, " +
            "SUM(CASE WHEN b.status = 'PAGO' THEN 1 ELSE 0 END) AS boletosPagos) " +
            "FROM Boleto b " +
            "WHERE b.empresa.id = :uuid " +
            "AND (:nome IS NULL OR LOWER(b.usuario.nome) LIKE LOWER(CONCAT('%', :nome, '%'))) " +
            "AND (:identificacao IS NULL OR LOWER(b.usuario.identificacao) LIKE LOWER(CONCAT('%', :identificacao, '%'))) " +
            "AND (:tipoUsuario IS NULL OR b.usuario.tipoUsuario = :tipoUsuario) " +
            "GROUP BY b.usuario.identificacao, b.usuario.nome " +
            "ORDER BY b.usuario.nome")
    List<UsuarioDTO> buscarUsuariosComResumoBoletos(
            @Param("uuid") UUID uuid,
            @Param("nome") String nome,
            @Param("identificacao") String identificacao,
            @Param("tipoUsuario") TipoUsuario tipoUsuario);
}
