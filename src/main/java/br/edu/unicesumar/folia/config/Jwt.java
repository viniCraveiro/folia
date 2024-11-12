package br.edu.unicesumar.folia.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class Jwt {

    private String usuarioValido = "usuario";
    private String senhaValida = "senha123";
    private String chave = "ChaveSecreta";  // Chave para assinar o JWT

    // Gera o token para o usuário, se as credenciais estiverem corretas
    public String gerarToken(String username, String senha) {
        // Verifica se o usuário e a senha correspondem às credenciais válidas
        if (validarCredenciais(username, senha)) {
            return Jwts.builder()
                    .setSubject(username)
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // Expira em 1 hora
                    .signWith(SignatureAlgorithm.HS256, chave)
                    .compact();
        } else {
            return null;  // Retorna null se as credenciais forem inválidas
        }
    }

    // Metodo para validar as credenciais
    private boolean validarCredenciais(String username, String senha) {
        return usuarioValido.equals(username) && senhaValida.equals(senha);
    }

    // Extrai o nome de usuário do token
    public String extrairUser(String token) {
        return extrairClaims(token).getSubject();
    }

    // Extrai as claims do token
    private Claims extrairClaims(String token) {
        return Jwts.parser()
                .setSigningKey(chave)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // Verifica se o token está expirado
    public boolean isTokenExpired(String token) {
        return extrairClaims(token).getExpiration().before(new Date());
    }

    // Valida o token
    public boolean validarToken(String token, String username) {
        return (username.equals(extrairUser(token)) && !isTokenExpired(token));
    }






    /*
    // Chave secreta
    private String chave = "Chave";

    // Metodo para gerar o token JWT
    public String gerarToken(String user){
        return Jwts.builder()
                .setSubject(user)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
                .signWith(SignatureAlgorithm.HS256, chave)
                .compact();
    }

    // Metodo para extrair o nome de usuário do token
    public String extrairUser(String token){
        return extrairClaims(token).getSubject();
    }

    // Metodo para extrair as claims do token
    private Claims extrairClaims(String token) {
        return Jwts.parser()
                .setSigningKey(chave)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // Metodo para verificar se o token é válido
    public boolean tokenExpirou(String token) {
        return extrairClaims(token).getExpiration().before(new Date());
    }

    // Metodo para validar o token
    public boolean validarToken(String token, String username) {
        return (username.equals(extrairUser(token)) && !tokenExpirou(token));
    }

     */

}
