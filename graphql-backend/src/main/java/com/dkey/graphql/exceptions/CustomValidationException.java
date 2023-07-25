package com.dkey.graphql.exceptions;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.graphql.execution.ErrorType;
import org.springframework.http.HttpStatus;

import graphql.ErrorClassification;
import graphql.GraphQLError;
import graphql.language.SourceLocation;
import lombok.Data;

@Data
public class CustomValidationException extends RuntimeException implements GraphQLError {

	private String message;
	
	public CustomValidationException(String message) {
		this.message = message;
	}

	@Override
	public List<SourceLocation> getLocations() {
		// TODO Auto-generated method stub
		return null;
	}
	
	

	@Override
	public ErrorClassification getErrorType() {
		// TODO Auto-generated method stub
		return ErrorType.BAD_REQUEST;
	}

	@Override
	public Map<String, Object> getExtensions() {
		
		Map<String, Object> attr = new LinkedHashMap<>();
		attr.put("status", HttpStatus.BAD_REQUEST.value());
		return attr;
	}

}
