package tech.bts.cardgame.util.database;

public class SqlBuilder {

    private String sql;
    private boolean hasWhere;

    public SqlBuilder from(String table) {
        this.sql = "select * from " + table;
        return this;
    }

    public SqlBuilder where(String condition) {

        String keywordBeforeCondition;

        if (hasWhere) {
            keywordBeforeCondition = " and ";
        } else {
            keywordBeforeCondition = " where ";
        }

        this.sql += keywordBeforeCondition + condition;

        hasWhere = true;

        return this;
    }

    public String build() {
        return this.sql;
    }

    public SqlBuilder where(String column, String operator, Object value) {

        String valueStr;

        if (value instanceof String) {
            valueStr = "'" + value + "'";
        } else {
            valueStr = value.toString();
        }

        String condition = column + " " + operator + " " + valueStr;

        return where(condition);
    }
}
