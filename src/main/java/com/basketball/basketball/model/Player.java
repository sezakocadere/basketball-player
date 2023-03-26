package com.basketball.basketball.model;

import com.basketball.basketball.enums.Position;
import com.basketball.basketball.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(uniqueConstraints = { @UniqueConstraint(name = "UNIQUE_POSITION_AND_TEAM", columnNames = { "position", "team_id" }) })
public class Player {
    @Id
    @GeneratedValue
    private Long id;
    @NotNull
    private String name;
    @NotNull
    private String surname;
    @NotNull
    @Enumerated(EnumType.STRING)
    private Position position;
    @ManyToOne
    private Team team;
    @Enumerated(EnumType.STRING)
    @NotNull
    private Status status;
}
