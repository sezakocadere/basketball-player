package com.basketball.basketball.service;

import com.basketball.basketball.dto.LogDto;
import com.basketball.basketball.dto.LogRequest;
import com.basketball.basketball.dto.LogResponse;
import com.basketball.basketball.repository.LogRepository;
import graphql.ExecutionResult;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.server.WebGraphQlInterceptor;
import org.springframework.graphql.server.WebGraphQlRequest;
import org.springframework.graphql.server.WebGraphQlResponse;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class GraphQLLoggingInterceptor implements WebGraphQlInterceptor {
    private final LogRepository logRepository;

    @Override
    public Mono<WebGraphQlResponse> intercept(WebGraphQlRequest request, Chain chain) {
        LogDto logDto = new LogDto();

        LogRequest logRequest = LogRequest.builder().id(request.getId()).headers(request.getHeaders())
                .variables(request.getVariables()).locale(request.getLocale())
                .uri(request.getUri()).operationName(request.getOperationName()).documents(request.getDocument()).build();
        logDto.setLogRequest(logRequest);

        return chain.next(request).doOnNext(response -> {
            ExecutionResult result = response.getExecutionResult();
            LogResponse logResponse = LogResponse.builder().errors(result.getErrors()).data(result.getData() != null ? result.getData().toString() : null)
                    .extensions(response.getExtensions()).build();
            logDto.setLogResponse(logResponse);
            logDto.setLocalDateTime(LocalDateTime.now());
            logRepository.save(logDto);
        });
    }
}