package br.com.fiap.techchallenge;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI openAPIDocumentation() {
        return new OpenAPI()
                .info(new Info()
                        .title("TechChallenge - Grupo 10")
                        .description("API de monitoramento de consumo de energia")
                        .version("v1.0")
                        .contact(new Contact()
                                .name("Grupo 10 - POSTECH-FIAP")
                                .email("grupo10.postech.fiap@gmail.com")
                        )
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0")
                        )
                );
    }
}
