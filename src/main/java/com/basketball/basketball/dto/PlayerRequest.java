package com.basketball.basketball.dto;

import com.basketball.basketball.enums.Position;
import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Validated
@Data
@Document(indexName = "player")
public class PlayerRequest {
    private int id;
    @NotEmpty
    @NotNull
    private String name;
    @NotNull
    @NotEmpty
    private String surname;
    @NotNull
    private Position position;
    private Long teamId;
}
