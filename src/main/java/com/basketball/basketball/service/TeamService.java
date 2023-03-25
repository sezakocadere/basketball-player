package com.basketball.basketball.service;

import com.basketball.basketball.dto.TeamRequest;
import com.basketball.basketball.model.Team;

public interface TeamService {
    Team createTeam(TeamRequest team);
}
