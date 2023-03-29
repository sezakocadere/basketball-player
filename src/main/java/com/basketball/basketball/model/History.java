package com.basketball.basketball.model;

import com.basketball.basketball.enums.Operation;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.OffsetDateTime;

@Entity
@Data
public class History {
    @Id
    @GeneratedValue
    private Long id;
    @Enumerated(EnumType.STRING)
    private Operation operationType;

    @NotNull
    private OffsetDateTime createTime;

    @NotNull
    private String changedBy;
    private String detail;
}
