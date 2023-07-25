package com.dkey.graphql.interpolator;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.dkey.graphql.exceptions.CustomValidationException;

import graphql.GraphQLError;
import graphql.validation.interpolation.MessageInterpolator;
import graphql.validation.rules.ValidationEnvironment;
import lombok.Data;

@Component
public class CustomValidationInterpolator implements MessageInterpolator {

	@Override
	public GraphQLError interpolate(String messageTemplate, Map<String, Object> messageParams,
			ValidationEnvironment validationEnvironment) {
		
		String argumentName = validationEnvironment.getArgument().getName();
		String message = String.format(messageTemplate, argumentName);
		return new CustomValidationException(message);
	}

}
