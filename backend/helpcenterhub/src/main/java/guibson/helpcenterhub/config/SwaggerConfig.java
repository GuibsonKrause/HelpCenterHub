/*package guibson.helpcenterhub.config;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi ticketApi() {
        return GroupedOpenApi.builder()
                .group("Tickets")
                .packagesToScan("guibson.helpcenterhub.controller")
                .pathsToMatch("/tickets/**")
                .build();
    }

    @Bean
    public GroupedOpenApi userApi() {
        return GroupedOpenApi.builder()
                .group("Users")
                .packagesToScan("guibson.helpcenterhub.controller")
                .pathsToMatch("/users/**")
                .build();
    }

    @Bean
    public GroupedOpenApi feedbackApi() {
        return GroupedOpenApi.builder()
                .group("Feedback")
                .packagesToScan("guibson.helpcenterhub.controller")
                .pathsToMatch("/feedback/**")
                .build();
    }

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                .title("Help Center Hub API")
                .version("v1")
                .description("This is a sample Spring Boot RESTful service using springdoc-openapi and OpenAPI 3."));
    }
}
*/