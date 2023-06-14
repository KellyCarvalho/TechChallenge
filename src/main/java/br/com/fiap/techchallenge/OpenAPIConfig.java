package br.com.fiap.techchallenge;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.Paths;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI openAPIDocumentation() {
        return new OpenAPI()
                .info(new Info()
                        .title("Um nome legal  API")
                        .description("API do sistema . . . ")
                        .version("v1.0")
                        .contact(new Contact()
                                .name("Grupo 10 - POSTECH-FIAP")
                                .email("grupo10.postech.fiap@gmail.com")
                        )
                );
    }
}
