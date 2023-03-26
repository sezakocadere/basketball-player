package com.basketball.basketball.service.player;

import com.basketball.basketball.dto.PlayerRequest;
import com.basketball.basketball.enums.Operation;
import com.basketball.basketball.enums.Status;
import com.basketball.basketball.error.NotFoundObjectException;
import com.basketball.basketball.error.TooManyPlayersException;
import com.basketball.basketball.model.Player;
import com.basketball.basketball.model.Team;
import com.basketball.basketball.repository.PlayerRepository;
import com.basketball.basketball.repository.TeamRepository;
import com.basketball.basketball.service.history.HistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PlayerServiceImpl implements PlayerService {
    private final PlayerRepository playerRepository;
    private final TeamRepository teamRepository;
    private final HistoryService historyService;

    @Override
    public Page<Player> getAllPlayers(int page, int size) {
        return playerRepository.findAll(PageRequest.of(page, size));
    }

    @Transactional
    @Override
    public Player createPlayer(PlayerRequest playerRequest) {
        Team team = getTeam(playerRequest.getTeamId());
        int playerNumberOfTeam = playerRepository.countByTeamId(team.getId());
        if (playerNumberOfTeam > 4) {
            throw new TooManyPlayersException("A basketball team cannot have more than 5 members");
        }
        Player player = new Player();
        player.setName(playerRequest.getName());
        player.setSurname(playerRequest.getSurname());
        player.setPosition(playerRequest.getPosition());
        player.setStatus(Status.ACTIVE);
        player.setTeam(team);
        playerRepository.save(player);
        historyService.save(Operation.CREATE);
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
    public void removePlayer(Long id) {
        Player player = getPlayer(id);
        player.setStatus(Status.PASSIVE);
        playerRepository.save(player);
        historyService.save(Operation.DELETE);
    }
}