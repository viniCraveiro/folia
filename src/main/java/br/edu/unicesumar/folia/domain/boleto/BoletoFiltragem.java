package br.edu.unicesumar.folia.domain.boleto;

import br.edu.unicesumar.folia.controller.usuarioboleto.BoletoFiltroDTO;
import br.edu.unicesumar.folia.exception.UserUuidNotFoundException;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class BoletoFiltragem {
    public static Specification<Boleto> boletoUsuarioComFiltros(BoletoFiltroDTO filtro) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            // Filtro quando for do usuário
            if (filtro.getUsuarioUUID() != null) {
                predicates.add(criteriaBuilder.equal(
                        root.get("usuario").get("id"), UUID.fromString(filtro.getUsuarioUUID())));
            } else {
                throw new UserUuidNotFoundException("UUID do usuario não encontrando");
            }

            // Filtro por nome do banco
            if (filtro.getBanco() != null) {
                predicates.add(criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("banco").get("nome")),
                        "%" + filtro.getBanco().toLowerCase() + "%"));
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

    public static Specification<Boleto> boletoEmpresaComFiltros(BoletoFiltroDTO filtro) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            // Filtro quando for da empresa
            if (filtro.getEmpresaUUID() != null) {
                predicates.add(criteriaBuilder.equal(
                        root.get("empresa").get("id"), UUID.fromString(filtro.getEmpresaUUID())));
            } else {
                throw new UserUuidNotFoundException("UUID da empresa não encontrando");
            }

            // Filtro por nome do usuário
            if (filtro.getNome() != null) {
                predicates.add(criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("usuario").get("nome")),
                        "%" + filtro.getNome().toLowerCase() + "%"));
            }

            // Filtro por nome do identificacao
            if (filtro.getIdentificacao() != null) {
                predicates.add(criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("usuario").get("identificacao")),
                        "%" + filtro.getIdentificacao().toLowerCase() + "%"));
            }

            // Filtro por nome do banco
            if (filtro.getBanco() != null) {
                predicates.add(criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("banco").get("nome")),
                        "%" + filtro.getBanco().toLowerCase() + "%"));
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
