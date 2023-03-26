package com.basketball.basketball.service.team;

import com.basketball.basketball.dto.TeamRequest;
import com.basketball.basketball.enums.Operation;
import com.basketball.basketball.model.Team;
import com.basketball.basketball.repository.TeamRepository;
import com.basketball.basketball.service.history.HistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class TeamServiceImpl implements TeamService {
    private final TeamRepository teamRepository;
    private final HistoryService historyService;

    @Transactional
    @Override
    public Team createTeam(TeamRequest teamRequest) {
        Team team = new Team();
        team.setName(teamRequest.getName());
        teamRepository.save(team);
        historyService.save(Operation.CREATE);
        return team;
    }
}
