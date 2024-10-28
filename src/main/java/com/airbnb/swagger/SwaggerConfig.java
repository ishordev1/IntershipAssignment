package com.airbnb.swagger;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import io.swagger.v3.oas.models.tags.Tag;

@Configuration
public class SwaggerConfig {
@Bean
public OpenAPI myCustomConfig() {
	return new OpenAPI().info(
			new Info().title("AirBNB Project")
			.description("By Ishor")
			)
			//adding server url
			.servers(List.of(
					new Server().url("http://localhost:8080").description("localhost"),
					new Server().url("localhost://aws:90012").description("live")
					))
			//ordering the controller
			.tags(List.of(
					new Tag().name("public Api"),
					new Tag().name("Property Api")
					))
			.addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
			.components(new Components().addSecuritySchemes(
					"bearerAuth",new SecurityScheme()
					.type(SecurityScheme.Type.HTTP)
					.scheme("bearer")
					.bearerFormat("JWT")
					.in(SecurityScheme.In.HEADER)
					.name("Authorization")
					))
			;
}

}