package tech.bts.cardgame.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import tech.bts.cardgame.model.Game;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * Stores games in a database
 */
@Repository
public class GameRepositoryJdbc {

    private JdbcTemplate jdbcTemplate;

    public GameRepositoryJdbc() {
        DataSource dataSource = DataSourceUtil.getDataSourceInPath();
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void create(Game game) {

        jdbcTemplate.update("insert into games (state, players)" +
                " values ('" + game.getState() + "', NULL)");
    }

    public Game getById(long id) {

        return jdbcTemplate.queryForObject(
                "select * from games where id = " + id,
                (rs1, rowNum) -> getGame(rs1));
    }

    public Collection<Game> getAll() {

        return jdbcTemplate.query(
                "select * from games",
                (rs1, rowNum) -> getGame(rs1));
    }

    private Game getGame(ResultSet rs) throws SQLException {

        int id = rs.getInt("id");
        String players = rs.getString("players");

        Game game = new Game(null);
        game.setId(id);

        if (players != null) {
            String[] names = players.split(",");
            for (String name : names) {
                game.join(name);
            }
        }

        // String state = rs.getString("state");
        // The join() method already updates the game state so we don't need to update it

        return game;
    }
}
