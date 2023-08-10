package com.basketball.basketball.dto;

import graphql.GraphQLError;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@Builder
public class LogResponse {
    private List<GraphQLError> errors;
    private String data;
    private Map<Object, Object> extensions;
    private Map<String, Object> specification;
}
