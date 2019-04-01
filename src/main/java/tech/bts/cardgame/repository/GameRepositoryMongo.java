package tech.bts.cardgame.repository;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.springframework.stereotype.Repository;
import tech.bts.cardgame.model.Game;
import tech.bts.cardgame.model.GameSearch;
import tech.bts.cardgame.util.database.MongoUtil;

import java.util.Collection;

@Repository
public class GameRepositoryMongo implements GameRepository {

    private MongoCollection<Document> gamesCol;

    public GameRepositoryMongo() {

        MongoClient mongoClient = MongoClients.create();
        MongoDatabase database = mongoClient.getDatabase("cardgame");
        this.gamesCol = database.getCollection("games");
    }

    @Override
    public void create(Game game) {

        Document gameDoc = MongoUtil.doc()
                .append("state", game.getState().toString())
                .append("players", game.getPlayerNames());

        gamesCol.insertOne(gameDoc);
    }

    @Override
    public void update(Game game) {

    }

    @Override
    public Game getById(long id) {
        return null;
    }

    @Override
    public Collection<Game> getAll() {
        return null;
    }

    @Override
    public Collection<Game> find(GameSearch gameSearch) {
        return null;
    }
}
