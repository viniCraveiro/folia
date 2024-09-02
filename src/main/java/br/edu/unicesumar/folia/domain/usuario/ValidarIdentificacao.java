package br.edu.unicesumar.folia.domain.usuario;

public class ValidarIdentificacao {
    public static boolean validarCPF(String cpf) {
        if (cpf == null || cpf.length() != 11 || !cpf.matches("\\d+")) {
            return false;
        }

        // Validação básica de CPF
        int[] pesos = {10, 9, 8, 7, 6, 5, 4, 3, 2}; // Correção aqui: pesos com 9 elementos
        int soma = 0;
        for (int i = 0; i < 9; i++) {
            soma += Character.getNumericValue(cpf.charAt(i)) * pesos[i];
        }
        int digito1 = 11 - (soma % 11);
        digito1 = (digito1 >= 10) ? 0 : digito1;

        if (digito1 != Character.getNumericValue(cpf.charAt(9))) {
            return false;
        }

        soma = 0;
        int[] pesos2 = {11, 10, 9, 8, 7, 6, 5, 4, 3, 2}; // Pesos para o segundo dígito
        for (int i = 0; i < 10; i++) {
            soma += Character.getNumericValue(cpf.charAt(i)) * pesos2[i];
        }
        int digito2 = 11 - (soma % 11);
        digito2 = (digito2 >= 10) ? 0 : digito2;

        return digito2 == Character.getNumericValue(cpf.charAt(10));
    }

    public static boolean validarCNPJ(String cnpj) {
        if (cnpj == null || cnpj.length() != 14 || !cnpj.matches("\\d+")) {
            return false;
        }

        // Validação básica de CNPJ
        int[] pesos1 = {5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
        int[] pesos2 = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
        int soma = 0;
        for (int i = 0; i < 12; i++) {
            soma += Character.getNumericValue(cnpj.charAt(i)) * pesos1[i];
        }
        int digito1 = 11 - (soma % 11);
        digito1 = (digito1 >= 10) ? 0 : digito1;

        if (digito1 != Character.getNumericValue(cnpj.charAt(12))) {
            return false;
        }

        soma = 0;
        for (int i = 0; i < 13; i++) {
            soma += Character.getNumericValue(cnpj.charAt(i)) * pesos2[i];
        }
        int digito2 = 11 - (soma % 11);
        digito2 = (digito2 >= 10) ? 0 : digito2;

        return digito2 == Character.getNumericValue(cnpj.charAt(13));
    }
}

