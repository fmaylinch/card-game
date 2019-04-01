package tech.bts.cardgame.repository;

import tech.bts.cardgame.model.Game;
import tech.bts.cardgame.model.GameSearch;

import java.util.Collection;

public interface GameRepository {

    void create(Game game);

    void update(Game game);

    Game getById(long id);

    Collection<Game> getAll();

    Collection<Game> find(GameSearch gameSearch);
}
