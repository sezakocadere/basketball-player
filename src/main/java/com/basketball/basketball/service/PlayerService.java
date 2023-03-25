package com.basketball.basketball.service;

import com.basketball.basketball.dto.PlayerRequest;
import com.basketball.basketball.model.Player;

import java.util.List;

public interface PlayerService {

    List<Player> getAllPlayers();

    Player createPlayer(PlayerRequest player);

    void removePlayer(Long id);
}
