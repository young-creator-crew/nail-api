
package sptech.school.nail_api.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Nail API",
                version = "1.0.0",
                description = "API para gerenciamento de usuários, serviços e agendamentos de manicure."
        )
)
public class OpenApiConfig {
}