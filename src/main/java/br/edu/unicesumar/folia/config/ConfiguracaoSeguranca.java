package br.edu.unicesumar.folia.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
public class ConfiguracaoSeguranca {

    @Autowired
    private AutenticacaoJwt autenticacaoJwt;

    @Bean
    public SecurityFilterChain cadeiaDeSeguranca(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Desabilita CSRF (não recomendado para produção)
                .authorizeRequests()
                .requestMatchers("/auth/login").permitAll()  // Permite acesso à rota de login
                .anyRequest().authenticated()  // Todas as outras rotas requerem autenticação
                .and()
                .addFilterBefore(autenticacaoJwt, UsernamePasswordAuthenticationFilter.class); // Adiciona o filtro de JWT antes do filtro de autenticação padrão

        return http.build();
    }


}
