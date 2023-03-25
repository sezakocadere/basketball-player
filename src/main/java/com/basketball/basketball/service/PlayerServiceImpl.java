package com.basketball.basketball.service;

import com.basketball.basketball.dto.PlayerRequest;
import com.basketball.basketball.enums.Status;
import com.basketball.basketball.model.Player;
import com.basketball.basketball.model.Team;
import com.basketball.basketball.repository.PlayerRepository;
import com.basketball.basketball.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PlayerServiceImpl implements PlayerService {
    private final PlayerRepository playerRepository;
    private final TeamRepository teamRepository;

    @Override
    public List<Player> getAllPlayers() {
        return playerRepository.findAll();
    }

    @Transactional
    @Override
    public Player createPlayer(PlayerRequest playerRequest) {
        Team basketTeam = getBasketTeam(playerRequest.getBasketTeamId());
        int playerNumberOfTeam = playerRepository.countByTeamId(basketTeam.getId());
        if (playerNumberOfTeam > 4) {
            throw new EntityNotFoundException("team member > 5");
        }
        Player player = new Player();
        player.setName(playerRequest.getName());
        player.setSurname(playerRequest.getSurname());
        player.setPosition(playerRequest.getPosition());
        player.setStatus(Status.ACTIVE);
        player.setTeam(basketTeam);
        playerRepository.save(player);
        return player;
    }

    private Team getBasketTeam(Long id) {
        return teamRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Not found basket team"));
    }

    private Player getPlayer(Long id) {
        return playerRepository.findByIdAndStatus(id, Status.ACTIVE).orElseThrow(() -> new EntityNotFoundException("Not found player"));
    }

    @Transactional
    @Override
    public void removePlayer(Long id) {
        Player player = getPlayer(id);
        player.setStatus(Status.PASSIVE);
        playerRepository.save(player);
    }
}