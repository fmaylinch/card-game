package tech.bts.cardgame.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.bts.cardgame.model.*;
import tech.bts.cardgame.repository.GameRepository;

import java.util.Collection;

@Service
public class GameService {

    private GameRepository gameRepo;

    @Autowired
    public GameService(GameRepository gameRepo) {
        this.gameRepo = gameRepo;
    }

    public Game createGame() {

        Deck deck = new Deck();
        deck.generate();
        deck.shuffle();
        Game game = new Game(deck);

        gameRepo.create(game);

        return game;
    }

    public void joinGame(GameUser gameUser) {

        Game game = gameRepo.getById(gameUser.getGameId());
        game.join(gameUser.getUsername());
    }

    public Card pickCard(GameUser gameUser) {

        Game game = gameRepo.getById(gameUser.getGameId());
        return game.pickCard(gameUser.getUsername());
    }

    public Collection<Game> getAllGames() {

        return gameRepo.getAll();
    }

    public Game getGameById(long id) {

        return gameRepo.getById(id);
    }

    public Collection<Game> find(GameSearch gameSearch) {

        return gameRepo.find(gameSearch);
    }
}
