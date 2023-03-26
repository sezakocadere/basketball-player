package com.basketball.basketball;

import com.basketball.basketball.dto.TeamRequest;
import com.basketball.basketball.model.Team;
import com.basketball.basketball.repository.TeamRepository;
import com.basketball.basketball.service.TeamService;
import com.basketball.basketball.service.TeamServiceImpl;
import org.assertj.core.api.JUnitSoftAssertions;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.Silent.class)
public class TeamServiceImplTest {
    @Rule
    public final JUnitSoftAssertions softly = new JUnitSoftAssertions();
    @Mock
    TeamRepository teamRepository;
    @Mock
    private TeamService teamService;

    @Before
    public void setup() {
        teamService = new TeamServiceImpl(teamRepository);
    }

    @Test
    public void createTeam() {
        //Arrange
        TeamRequest teamRequest = new TeamRequest();
        teamRequest.setName("Team2");

        //Act
        Team team = teamService.createTeam(teamRequest);

        //Assert
        softly.assertThat(team.getName()).isEqualTo(teamRequest.getName());
    }
}
