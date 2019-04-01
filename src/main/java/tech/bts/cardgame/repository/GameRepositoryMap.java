package tech.bts.cardgame.repository;

import org.springframework.stereotype.Repository;
import tech.bts.cardgame.model.Game;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Stores games in memory (in a Map)
 */
public class GameRepositoryMap {

    private Map<Long, Game> gameMap;
    private long nextId;

    public GameRepositoryMap() {
        gameMap = new HashMap<>();
        nextId = 0;
    }

    public void create(Game game) {
        game.setId(nextId);
        gameMap.put(nextId, game);
        nextId++;
    }

    public Game getById(long id) {
        return gameMap.get(id);
    }

    public Collection<Game> getAll() {

        return gameMap.values();
    }
}
