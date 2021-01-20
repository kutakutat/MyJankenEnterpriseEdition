package com.example.janken.application;

import com.example.janken.domain.dao.PlayerDao;
import com.example.janken.domain.model.Player;
import com.example.janken.framework.ServiceLocator;

public class PlayerService {

    private PlayerDao playerCsvDao = ServiceLocator.resolve(PlayerDao.class);

    public Player findPlayerById(long playerId) {
        return playerCsvDao.findPlayerById(playerId);
    }

}
