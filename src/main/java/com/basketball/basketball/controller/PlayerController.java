package com.basketball.basketball.controller;

import com.basketball.basketball.dto.PlayerRequest;
import com.basketball.basketball.dto.TeamRequest;
import com.basketball.basketball.model.Player;
import com.basketball.basketball.model.Team;
import com.basketball.basketball.service.PlayerService;
import com.basketball.basketball.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class PlayerController {
    private final PlayerService playerService;
    private final TeamService teamService;

    @QueryMapping
    public List<Player> getAllPlayers() {
        return playerService.getAllPlayers();
    }

    @MutationMapping
    public Player createPlayer(@Valid @Argument PlayerRequest player) {
        return playerService.createPlayer(player);
    }

    @MutationMapping
    public void removePlayer(@Argument Long id) {
        playerService.removePlayer(id);
    }

    @MutationMapping
    public Team createTeam(@Valid @Argument TeamRequest team) {
        return teamService.createTeam(team);
    }

}
