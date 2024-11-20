package br.edu.unicesumar.folia.domain.boleto;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum Status {
    ABERTO,
    ABERTA,
    PAGO,
    VENCIDO;

    @JsonCreator
    public static Status fromString(String value) {
        if (value != null) {
            switch (value.toUpperCase()) {
                case "ABERTA":
                    return ABERTO;
                case "PAGO":
                    return PAGO;
                case "VENCIDO":
                    return VENCIDO;
                default:
                    throw new IllegalArgumentException("Status desconhecido: " + value);
            }
        }
        return null; // ou ABERTO, caso queira retornar um valor padrão
    }
}
