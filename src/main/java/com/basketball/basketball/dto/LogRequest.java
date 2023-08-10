package com.basketball.basketball.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpHeaders;
import org.springframework.web.util.UriComponents;

import java.util.Locale;
import java.util.Map;

@Builder
@Data
public class LogRequest {
    private String id;
    private String operationName;
    private HttpHeaders headers;
    private UriComponents uri;
    private Map<String, Object> variables;
    private Locale locale;
    private String documents;
}
