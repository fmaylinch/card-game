package tech.bts.cardgame.util.database;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class SqlBuilderTest {

    @Test
    public void selectFromTable() {

        // 1. prepare objects
        SqlBuilder sqlBuilder = new SqlBuilder();

        // 2. call some method(s)
        sqlBuilder.from("users");
        String sql = sqlBuilder.build();

        // 3. check results
        assertThat(sql, is("select * from users"));
    }

    @Test
    public void selectFromTable_Simplified() {

        String sql = new SqlBuilder().from("users").build();

        assertThat(sql, is("select * from users"));
    }

    @Test
    public void whereCondition() {

        String sql = new SqlBuilder()
                .from("products")
                .where("type = 'FOOD'")
                .build();

        assertThat(sql, is("select * from products where type = 'FOOD'"));
    }

    @Test
    public void whereMultipleConditions() {

        String sql = new SqlBuilder()
                .from("products")
                .where("type = 'FOOD'")
                .where("available = true")
                .where("price <= 10")
                .build();

        assertThat(sql, is(
                "select * from products" +
                        " where type = 'FOOD'" +
                        " and available = true" +
                        " and price <= 10"
        ));
    }

    @Test
    public void whereEasierConditions() {

        String productType = "TOOLS";

        String sql = new SqlBuilder()
                .from("products")
                .where("type", "=", productType)
                .where("units", ">=", 100)
                .where("price", "<=", 9.95)
                .where("available", "=", true)
                .build();

        assertThat(sql, is("select * from products" +
                " where type = 'TOOLS' and units >= 100" +
                " and price <= 9.95 and available = true"));
    }
}