package com.example.vehicule.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Copi Application")
                        .description("Gestion des cartes de poker")
                        .contact(new Contact()
                                .name("GhDev")
                                .email("*************@esprit.tn")
                                .url("https://www.linkedin.com/in//")));
    }


    @Bean
    public GroupedOpenApi clientApi() {
        return GroupedOpenApi.builder()
                .group("client API")
                .pathsToMatch( "/clients/**") // Assurez-vous que le chemin correspond à votre contrôleur
                .build();
    }

    @Bean
    public GroupedOpenApi vehiculeApi() {
        return GroupedOpenApi.builder()
                .group("vehicule API")
                .pathsToMatch( "/vehicles/**") // Assurez-vous que le chemin correspond à votre contrôleur
                .build();
    }
    @Bean
    public GroupedOpenApi HISTORIQUEApi() {
        return GroupedOpenApi.builder()
                .group("HISTORIQUE API")
                .pathsToMatch( "/historique/**") // Assurez-vous que le chemin correspond à votre contrôleur
                .build();
    }
}
