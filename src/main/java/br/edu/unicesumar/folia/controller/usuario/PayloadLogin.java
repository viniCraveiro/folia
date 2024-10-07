package br.edu.unicesumar.folia.controller.usuario;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class PayloadLogin {
    private String identificacao;
    private String senha;
}