package com.br.pironi.configuration;

import java.util.ArrayList;
import java.util.List;

import org.springdoc.core.models.GroupedOpenApi;
import org.springdoc.core.properties.SwaggerUiConfigParameters;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionLocator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;



@Configuration
public class OpenApiConfiguration {

	@Bean
	@Lazy(false)
	public List<GroupedOpenApi> apis(SwaggerUiConfigParameters config, RouteDefinitionLocator locator) {
		
		List<RouteDefinition> definitions = locator.getRouteDefinitions().collectList().block();
		definitions.stream().filter(r -> r
				.getId()
				.matches(".*-service")).forEach(r -> {
					String name = r.getId();
					config.addGroup(name);
					GroupedOpenApi.builder()
					.pathsToMatch("/"+ name +"/**")
					.group(name)
					.build();
				}
				);
		return new ArrayList<>();
	}
}
