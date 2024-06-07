package com.generation.blogpessoal.configuration;

import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.responses.ApiResponses;

@Configuration
public class SwaggerConfig {
	
	@Bean
	OpenAPI springBlogPessoalOpenAPI() {
		return new OpenAPI()
				.info(new Info()
						.title("Projeto Blog Pessoal da Thamires")
						.description("Projeto Blog Pessoal desenvolvido na Generation Brasil")
						.version("v0.0.1")
						.license(new License()
								.name("Thamires Martins")
								.url("www.linkedin.com/in/thamimac"))
						.contact(new Contact()
								.name("Thamires Martins")
								.url("www.linkedin.com/in/thamimac")
								.email("thamires.araujo1996@gmail.com")))
				.externalDocs(new ExternalDocumentation()
						.description("Github")
						.url("https://github.com/Thamimac/Blog-Pessoal.git"));
	}
	
	@Bean
	OpenApiCustomizer customerGlobalHeaderOpenApiCustomiser() {
		
		return openApi -> {
			openApi.getPaths().values().forEach(pathItem -> pathItem.readOperations()
					.forEach(operation -> {
						
						ApiResponses apiResponses = operation.getResponses();
						
						apiResponses.addApiResponse("200", createApiResponse("Sucesso!"));
						apiResponses.addApiResponse("201", createApiResponse("Objeto Persistido!"));
						apiResponses.addApiResponse("204", createApiResponse("Objeto Excluido!"));
						apiResponses.addApiResponse("400", createApiResponse("Erro na Requisicao!"));
						apiResponses.addApiResponse("401", createApiResponse("Acesso nao autorizado!"));
						apiResponses.addApiResponse("403", createApiResponse("Acesso Proibido!"));
						apiResponses.addApiResponse("404", createApiResponse("Objeto nao Encontrado!"));
						apiResponses.addApiResponse("500", createApiResponse("Erro na Aplicacao!"));
					}));
		};
		}
		
		private ApiResponse createApiResponse(String message) {
			return new ApiResponse().description(message);
		}
}
