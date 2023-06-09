package com.basketball.basketball.controller;

import com.basketball.basketball.dto.PlayerRequest;
import com.basketball.basketball.dto.TeamRequest;
import com.basketball.basketball.dto.UserRequest;
import com.basketball.basketball.model.History;
import com.basketball.basketball.model.LoginUser;
import com.basketball.basketball.model.Player;
import com.basketball.basketball.model.Team;
import com.basketball.basketball.service.history.HistoryService;
import com.basketball.basketball.service.player.PlayerService;
import com.basketball.basketball.service.team.TeamService;
import com.basketball.basketball.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class PlayerController {
    private final PlayerService playerService;
    private final TeamService teamService;
    private final UserService userService;
    private final HistoryService historyService;

    @PreAuthorize("!isAnonymous()")
    @QueryMapping
    public Page<Player> getAllPlayers(@Argument int page, @Argument int size) {
        return playerService.getAllPlayers(page, size);
    }

    @PreAuthorize("!isAnonymous()")
    @MutationMapping
    public Player createPlayer(@Valid @Argument PlayerRequest player) {
        return playerService.createPlayer(player);
    }

    @PreAuthorize("!isAnonymous()")
    @MutationMapping
    public Player removePlayer(@Argument Long id) {
        return playerService.removePlayer(id);
    }

    @PreAuthorize("!isAnonymous()")
    @MutationMapping
    public Team createTeam(@Valid @Argument TeamRequest team) {
        return teamService.createTeam(team);
    }

    @PreAuthorize("isAnonymous()")
    @MutationMapping
    public LoginUser createUser(@Valid @Argument UserRequest user) {
        return userService.createUser(user);
    }

    @PreAuthorize("isAnonymous()")
    @QueryMapping
    public String login(@Argument UserRequest userRequest) throws Exception {
        return userService.login(userRequest);
    }

    @PreAuthorize("!isAnonymous()")
    @QueryMapping
    public List<History> getHistory() {
        return historyService.getHistory();
    }
}