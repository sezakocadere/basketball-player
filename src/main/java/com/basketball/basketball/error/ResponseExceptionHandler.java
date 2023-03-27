package com.basketball.basketball.error;

import graphql.GraphQLError;
import graphql.GraphqlErrorBuilder;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.graphql.execution.DataFetcherExceptionResolverAdapter;
import org.springframework.graphql.execution.ErrorType;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;

@Component
public class ResponseExceptionHandler extends DataFetcherExceptionResolverAdapter {
    private final Map<Class<? extends Throwable>, ErrorType> errorTypeMap = new HashMap<>();

    public ResponseExceptionHandler() {
        errorTypeMap.put(NotFoundObjectException.class, ErrorType.NOT_FOUND);
        errorTypeMap.put(TooManyPlayersException.class, ErrorType.INTERNAL_ERROR);
        errorTypeMap.put(ConstraintViolationException.class, ErrorType.INTERNAL_ERROR);
        errorTypeMap.put(NoSuchElementException.class, ErrorType.INTERNAL_ERROR);
        errorTypeMap.put(DataIntegrityViolationException.class, ErrorType.INTERNAL_ERROR);
        errorTypeMap.put(Exception.class, ErrorType.INTERNAL_ERROR);
    }

    @Override
    protected GraphQLError resolveToSingleError(Throwable ex, DataFetchingEnvironment env) {
        ErrorType errorType = errorTypeMap.get(ex.getClass());
        if (Objects.nonNull(errorType)) {
            return GraphqlErrorBuilder.newError().errorType(errorType).message(ex.getMessage()).path(env.getExecutionStepInfo().getPath())
                    .location(env.getField().getSourceLocation())
                    .build();
        }
        return null;
    }
}
