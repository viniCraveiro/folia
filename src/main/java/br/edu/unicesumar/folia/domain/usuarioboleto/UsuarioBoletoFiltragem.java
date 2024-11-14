package br.edu.unicesumar.folia.domain.usuarioboleto;

import br.edu.unicesumar.folia.controller.usuarioboleto.UsuarioBoletoFiltroDTO;
import br.edu.unicesumar.folia.domain.boleto.Boleto;
import br.edu.unicesumar.folia.domain.boleto.Status;
import br.edu.unicesumar.folia.exception.UserUuidNotFoundException;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.util.UUID;


public class UsuarioBoletoFiltragem {
    public static Specification<Boleto> comFiltros(UsuarioBoletoFiltroDTO filtro) {
        return (root, query, criteriaBuilder) -> {
            // Especificações podem ser acumuladas para lidar com múltiplos filtros
            Specification<Boleto> specification = Specification.where(null);

            if (filtro.getUserUuid() == null) {
                throw new UserUuidNotFoundException("User UUID não encontrado!");
            }

            specification = specification.and(setUserUuid(UUID.fromString(filtro.getUserUuid())));

            if (filtro.getBanco() != null) {
                specification = specification.and(filtroPorBanco(filtro.getBanco()));
            }

            if (filtro.getStatus() != null) {
                specification = specification.and(filtroPorStatus(filtro.getStatus()));
            }

            if (filtro.getDataInicialEmissao() != null) {
                specification = specification.and(filtroPorDataEmissaoInicial(filtro.getDataInicialEmissao()));
            }

            if (filtro.getDataFinalEmissao() != null) {
                specification = specification.and(filtroPorDataEmissaoFinal(filtro.getDataFinalEmissao()));
            }

            if (filtro.getDataInicialVencimento() != null) {
                specification = specification.and(filtroPorDataVencimentoInicial(filtro.getDataInicialVencimento()));
            }

            if (filtro.getDataFinalVencimento() != null) {
                specification = specification.and(filtroPorDataVencimentoFinal(filtro.getDataFinalVencimento()));
            }

            return specification.toPredicate(root, query, criteriaBuilder);
        };
    }

    private static Specification<Boleto> setUserUuid(UUID uuid) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("usuario").get("id"),uuid);
    }

    private static Specification<Boleto> filtroPorBanco(String banco) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("banco").get("nome"), "%" + banco + "%");
    }

    private static Specification<Boleto> filtroPorStatus(Status status) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("status"), status);
    }

    private static Specification<Boleto> filtroPorDataEmissaoInicial(LocalDate dataEmissaoInicial) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("dataEmissao"), dataEmissaoInicial);
    }

    private static Specification<Boleto> filtroPorDataEmissaoFinal(LocalDate dataEmissaoFinal) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get("dataEmissao"), dataEmissaoFinal);
    }

    private static Specification<Boleto> filtroPorDataVencimentoInicial(LocalDate dataVencimentoInicial) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("dataVencimento"), dataVencimentoInicial);
    }

    private static Specification<Boleto> filtroPorDataVencimentoFinal(LocalDate dataVencimentoFinal) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get("dataVencimento"), dataVencimentoFinal);
    }
}
