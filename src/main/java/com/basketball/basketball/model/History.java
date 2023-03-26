package com.basketball.basketball.model;

import com.basketball.basketball.enums.Operation;
import lombok.Data;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.time.OffsetDateTime;

@Entity
@Data
@Audited
public class History {
    @Id
    @GeneratedValue
    private Long id;
    @Enumerated(EnumType.STRING)
    private Operation operationType;
    private OffsetDateTime createTime;
    private String changedBy;
}
