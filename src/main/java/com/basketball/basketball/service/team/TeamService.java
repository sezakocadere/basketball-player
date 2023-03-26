package com.basketball.basketball.service.team;

import com.basketball.basketball.dto.TeamRequest;
import com.basketball.basketball.model.Team;

public interface TeamService {
    Team createTeam(TeamRequest team);
}
