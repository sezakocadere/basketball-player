package com.basketball.basketball.service;

import com.basketball.basketball.dto.TeamRequest;
import com.basketball.basketball.model.Team;
import com.basketball.basketball.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TeamServiceImpl implements TeamService {
    private final TeamRepository teamRepository;

    @Override
    public Team createTeam(TeamRequest teamRequest) {
        Team team = new Team();
        team.setName(teamRequest.getName());
        teamRepository.save(team);
        return team;
    }
}
