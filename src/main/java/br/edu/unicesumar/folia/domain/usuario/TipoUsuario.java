package br.edu.unicesumar.folia.domain.usuario;

public enum TipoUsuario {
    DEFAULT("DEFAULT"),
    EMPRESA("EMPRESA"),
    ADMIN("ADMIN"),
    USER("USER");


    private final String value;

    TipoUsuario(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
