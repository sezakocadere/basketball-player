package com.basketball.basketball.service.player;

import com.basketball.basketball.dto.PlayerRequest;
import com.basketball.basketball.enums.Status;
import com.basketball.basketball.error.NotFoundObjectException;
import com.basketball.basketball.error.TooManyPlayersException;
import com.basketball.basketball.model.Player;
import com.basketball.basketball.model.Team;
import com.basketball.basketball.repository.PlayerRepository;
import com.basketball.basketball.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PlayerServiceImpl implements PlayerService {
    private static final int MAX_TEAM_SIZE = 5;
    private final PlayerRepository playerRepository;
    private final TeamRepository teamRepository;

    @Override
    public Page<Player> getAllPlayers(int page, int size) {
        return playerRepository.findAllByStatus(Status.ACTIVE, PageRequest.of(page, size));
    }

    @Transactional
    @Override
    public Player createPlayer(PlayerRequest playerRequest) {
        Team team = getTeam(playerRequest.getTeamId());
        checkTeamMaxCount(team);
        checkTeamPosition(playerRequest);
        Player player = preparePlayer(playerRequest, team);
        playerRepository.save(player);
        return player;
    }

    private void checkTeamPosition(PlayerRequest playerRequest) {
        boolean isSamePositionOfTeam = playerRepository.existsByPositionAndTeamIdAndStatus(playerRequest.getPosition(), playerRequest.getTeamId(), Status.ACTIVE);
        if (isSamePositionOfTeam) {
            throw new TooManyPlayersException("Other player has same position in team");
        }
    }

    private void checkTeamMaxCount(Team team) {
        int playerNumberOfTeam = playerRepository.countByTeamId(team.getId());
        if (playerNumberOfTeam > MAX_TEAM_SIZE - 1) {
            throw new TooManyPlayersException("A basketball team cannot have more than 5 members");
        }
    }

    private Player preparePlayer(PlayerRequest playerRequest, Team team) {
        Player player = new Player();
        player.setName(playerRequest.getName());
        player.setSurname(playerRequest.getSurname());
        player.setPosition(playerRequest.getPosition());
        player.setStatus(Status.ACTIVE);
        player.setTeam(team);
        return player;
    }

    private Team getTeam(Long id) {
        return teamRepository.findById(id).orElseThrow(() -> new NotFoundObjectException("Not Found Team"));
    }

    private Player getPlayer(Long id) {
        return playerRepository.findByIdAndStatus(id, Status.ACTIVE).orElseThrow(() -> new NotFoundObjectException("Not Found Player"));
    }

    @Transactional
    @Override
    public Player removePlayer(Long id) {
        Player player = getPlayer(id);
        player.setStatus(Status.PASSIVE);
        playerRepository.save(player);
        return player;
    }
}