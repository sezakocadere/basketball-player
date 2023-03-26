package com.basketball.basketball;

import com.basketball.basketball.dto.PlayerRequest;
import com.basketball.basketball.enums.Position;
import com.basketball.basketball.enums.Status;
import com.basketball.basketball.error.NotFoundObjectException;
import com.basketball.basketball.error.TooManyPlayersException;
import com.basketball.basketball.model.Player;
import com.basketball.basketball.model.Team;
import com.basketball.basketball.repository.PlayerRepository;
import com.basketball.basketball.repository.TeamRepository;
import com.basketball.basketball.service.history.HistoryService;
import com.basketball.basketball.service.player.PlayerService;
import com.basketball.basketball.service.player.PlayerServiceImpl;
import org.assertj.core.api.JUnitSoftAssertions;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.Silent.class)
public class PlayerServiceImplTest {
    @Rule
    public final JUnitSoftAssertions softly = new JUnitSoftAssertions();
    @Rule
    public final ExpectedException exp = ExpectedException.none();
    @Mock
    private PlayerService playerService;
    @Mock
    private PlayerRepository playerRepository;
    @Mock
    private TeamRepository teamRepository;
    @Mock
    private HistoryService historyService;


    @Before
    public void setup() {
        playerService = new PlayerServiceImpl(playerRepository, teamRepository, historyService);
    }

    @Test
    public void getPlayers() {
        //Arrange
        Player player = composePlayer();
        Page<Player> players = new PageImpl<>(List.of(player));
        when(playerRepository.findAll(PageRequest.of(0, 1))).thenReturn(players);

        //Act
        Page<Player> allPlayers = playerService.getAllPlayers(0, 1);

        //Assert
        softly.assertThat(allPlayers.getSize()).isEqualTo(players.getSize());
        softly.assertThat(allPlayers.getContent().get(0).getId()).isEqualTo(player.getId());
    }

    @Test
    public void createPlayer() {
        //Arrange
        PlayerRequest playerRequest = new PlayerRequest();
        playerRequest.setName("Seza");
        playerRequest.setSurname("Kocadere");
        playerRequest.setPosition(Position.PF);
        playerRequest.setTeamId(1L);
        Team team = composeTeam();
        when(teamRepository.findById(1L)).thenReturn(Optional.ofNullable(team));

        //Act
        Player player = playerService.createPlayer(playerRequest);

        //Assert
        softly.assertThat(playerRequest.getName()).isEqualTo(player.getName());
    }

    @Test
    public void createPlayerByInvalidValue() {
        //Arrange
        PlayerRequest playerRequest = new PlayerRequest();
        playerRequest.setName("Seza");
        playerRequest.setSurname("Kocadere");
        playerRequest.setPosition(Position.PF);
        playerRequest.setTeamId(1L);

        Team team = composeTeam();
        when(teamRepository.findById(1L)).thenReturn(Optional.ofNullable(team));
        when(playerRepository.countByTeamId(team.getId())).thenReturn(5);
        exp.expect(TooManyPlayersException.class);

        //Act
        Player player = playerService.createPlayer(playerRequest);

    }

    @Test
    public void removePlayer() {
        //Arrange
        Player player = composePlayer();
        when(playerRepository.findByIdAndStatus(player.getId(), Status.ACTIVE)).thenReturn(Optional.of(player));

        //Act
        playerService.removePlayer(player.getId());

        //Assert
        softly.assertThat(player.getStatus()).isEqualTo(Status.PASSIVE);
    }

    @Test
    public void removePlayerByInvalidValue() {
        //Arrange
        when(playerRepository.findById(1L)).thenReturn(Optional.empty());
        exp.expect(NotFoundObjectException.class);

        //Act
        playerService.removePlayer(1L);
    }

    public Player composePlayer() {
        Player player = new Player();
        player.setId(1L);
        player.setStatus(Status.ACTIVE);
        player.setSurname("kocadere");
        player.setName("seza");
        player.setPosition(Position.C);
        player.setTeam(composeTeam());
        return player;
    }

    public Team composeTeam() {
        Team team = new Team();
        team.setName("Team1");
        team.setId(1L);
        return team;
    }
}
