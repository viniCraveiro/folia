package br.edu.unicesumar.folia.domain.usuarioboleto;

import br.edu.unicesumar.folia.controller.usuarioboleto.UsuarioBoletoFiltroDTO;
import br.edu.unicesumar.folia.domain.boleto.Boleto;
import br.edu.unicesumar.folia.domain.boleto.Status;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class UsuarioBoletoFiltragem {
    public static Specification<Boleto> comFiltros(UsuarioBoletoFiltroDTO filtro) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            // Filtro por identificação do usuário
            if (filtro.getIdentificacao() != null) {
                predicates.add(criteriaBuilder.like(
                        root.get("usuario").get("identificacao"), "%" + filtro.getIdentificacao() + "%"));
            }

            // Filtro por nome do usuário
            if (filtro.getNome() != null) {
                predicates.add(criteriaBuilder.like(
                        root.get("usuario").get("nome"), "%" + filtro.getNome() + "%"));
            }

            // Filtro por status
            if (filtro.getStatus() != null) {
                predicates.add(criteriaBuilder.equal(
                        root.get("status"), filtro.getStatus()));
            }

            // Filtros por data de emissão
            if (filtro.getDataInicialEmissao() != null && filtro.getDataFinalEmissao() != null) {
                predicates.add(criteriaBuilder.between(
                        root.get("dataEmissao"), filtro.getDataInicialEmissao(), filtro.getDataFinalEmissao()));
            } else if (filtro.getDataInicialEmissao() != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(
                        root.get("dataEmissao"), filtro.getDataInicialEmissao()));
            } else if (filtro.getDataFinalEmissao() != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(
                        root.get("dataEmissao"), filtro.getDataFinalEmissao()));
            }

            // Filtros por data de vencimento
            if (filtro.getDataInicialVencimento() != null && filtro.getDataFinalVencimento() != null) {
                predicates.add(criteriaBuilder.between(
                        root.get("dataVencimento"), filtro.getDataInicialVencimento(), filtro.getDataFinalVencimento()));
            } else if (filtro.getDataInicialVencimento() != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(
                        root.get("dataVencimento"), filtro.getDataInicialVencimento()));
            } else if (filtro.getDataFinalVencimento() != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(
                        root.get("dataVencimento"), filtro.getDataFinalVencimento()));
            }

            // Combinação de todos os filtros com AND
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
