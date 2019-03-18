package tech.bts.cardgame.repository;

import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import tech.bts.cardgame.model.Game;
import tech.bts.cardgame.model.GameSearch;

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

    public void update(Game game) {

        String names = null;

        if (game.getPlayerNames() != null && !game.getPlayerNames().isEmpty()) {
            names = "'" + StringUtils.join(game.getPlayerNames(), ",") + "'";
        }

        String sql = "update games set " +
                "state = '" + game.getState() + "', " +
                "players = " + names + " " +
                "where id = " + game.getId();

        //System.out.println("SQL: " + sql);
        jdbcTemplate.update(sql);
    }

    public void createOrUpdate(Game game) {

        if (game.getId() != null) {
            update(game);
        } else {
            create(game);
        }
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

        long id = rs.getLong("id");
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

    public Collection<Game> find(GameSearch gameSearch) {

        String sql = "select * from games";

        // TODO: we have to improve this
        //       because we don't want to put all conditions always
        //       and sometimes we don't want to add conditions at all (so there would be no 'where')

        sql += " where state = '" + gameSearch.state + "'";

        // sql += " and players like '%" + gameSearch.playerName + "%'";

        return jdbcTemplate.query(sql, (rs1, rowNum) -> getGame(rs1));
    }
}
