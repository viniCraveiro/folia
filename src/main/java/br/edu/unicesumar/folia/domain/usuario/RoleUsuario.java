package br.edu.unicesumar.folia.domain.usuario;

public enum RoleUsuario {
    DEFAULT("0", "DEFAULT"),
    USER("1", "USER"),
    EMPRESA("2", "EMPRESA"),
    ADMIN("3", "ADMIN");


    private final String id;
    private final String value;

    RoleUsuario(String id, String value) {
        this.id = id;
        this.value = value;
    }

    public String getId() {
        return id;
    }

    public String getValue() {
        return value;
    }

}
