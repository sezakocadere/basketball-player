package com.basketball.basketball.dto;

import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;

import java.time.LocalDateTime;

@Data
@Document(indexName = "basketball-player")
public class LogDto {
    private String id;
    private LogRequest logRequest;
    private LogResponse logResponse;
    private LocalDateTime localDateTime;
}
