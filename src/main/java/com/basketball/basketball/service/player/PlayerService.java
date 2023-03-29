package com.basketball.basketball.service.player;

import com.basketball.basketball.dto.PlayerRequest;
import com.basketball.basketball.model.Player;
import org.springframework.data.domain.Page;

public interface PlayerService {

    Page<Player> getAllPlayers(int page, int size);

    Player createPlayer(PlayerRequest player);

    Player removePlayer(Long id);
}
