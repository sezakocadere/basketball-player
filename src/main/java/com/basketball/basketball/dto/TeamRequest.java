package com.basketball.basketball.dto;

import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Validated
@Data
public class TeamRequest {
    @NotEmpty
    @NotNull
    private String name;
}
