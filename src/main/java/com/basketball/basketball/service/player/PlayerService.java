package com.basketball.basketball.service.player;

import com.basketball.basketball.dto.PlayerRequest;
import com.basketball.basketball.model.Player;
import org.springframework.data.domain.Page;

import java.util.List;

public interface PlayerService {

    Page<Player> getAllPlayers(int page, int size);

    Player createPlayer(PlayerRequest player);

    void removePlayer(Long id);
}
