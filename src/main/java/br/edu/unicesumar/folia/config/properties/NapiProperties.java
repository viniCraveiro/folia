package br.edu.unicesumar.folia.config.properties;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ToString
@Setter
@Getter
@Configuration
@ConfigurationProperties(prefix = "napi")
public class NapiProperties {

        private String url;

}
