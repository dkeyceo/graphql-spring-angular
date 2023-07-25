package com.dkey.graphql.config;

import org.springframework.context.annotation.Bean;
import org.springframework.graphql.execution.RuntimeWiringConfigurer;
import org.springframework.stereotype.Component;

import com.dkey.graphql.interpolator.CustomValidationInterpolator;

import graphql.validation.rules.OnValidationErrorStrategy;
import graphql.validation.rules.ValidationRules;
import graphql.validation.schemawiring.ValidationSchemaWiring;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class GraphQLConfig {
	
	private final CustomValidationInterpolator customValidationInterpolator;
	
	@Bean
	public RuntimeWiringConfigurer runtimeWiringConfigurer() {
		ValidationRules validationRules = ValidationRules.newValidationRules()
				.onValidationErrorStrategy(OnValidationErrorStrategy.RETURN_NULL)
				.messageInterpolator(customValidationInterpolator)
				.build();
		ValidationSchemaWiring schemaWiring = new ValidationSchemaWiring(validationRules);
		
		return builder -> builder.directiveWiring(schemaWiring);
	}
}
