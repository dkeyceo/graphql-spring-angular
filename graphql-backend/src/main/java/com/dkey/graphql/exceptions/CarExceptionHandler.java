package com.dkey.graphql.exceptions;

import java.util.List;

import org.springframework.graphql.execution.DataFetcherExceptionResolverAdapter;
import org.springframework.stereotype.Component;

import graphql.ErrorClassification;
import graphql.GraphQLError;
import graphql.language.SourceLocation;
import graphql.schema.DataFetchingEnvironment;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class CarExceptionHandler extends DataFetcherExceptionResolverAdapter {

	@Override
	protected GraphQLError resolveToSingleError(Throwable ex, DataFetchingEnvironment env) {
		
		return new GraphQLError() {
			
			@Override
			public String getMessage() {
				log.info("Exception: {}", ex.getMessage());
				return ex.getMessage();
			}

			@Override
			public List<SourceLocation> getLocations() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ErrorClassification getErrorType() {
				// TODO Auto-generated method stub
				return null;
			}
		};
	}
	
}
