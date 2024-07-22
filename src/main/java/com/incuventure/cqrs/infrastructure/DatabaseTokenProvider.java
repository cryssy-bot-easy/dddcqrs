package com.incuventure.cqrs.infrastructure;

import com.incuventure.cqrs.token.TokenProvider;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 */
public class DatabaseTokenProvider implements TokenProvider{

    private static final String QUERY_SQL = "SELECT ID FROM TOKENS WHERE TOKEN = ?";
    private static final String DELETE_SQL = "DELETE FROM TOKENS WHERE TOKEN = ?";
    private static final String INSERT_SQL = "INSERT INTO TOKENS (TOKEN,ID) VALUES (?,?)";
    private JdbcTemplate jdbcTemplate;
    private String insertSql;
    private String deleteSql;
    private String querySql;

    public DatabaseTokenProvider(DataSource ds){
        this.jdbcTemplate = new JdbcTemplate(ds);
    }

    public DatabaseTokenProvider(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void addTokenForId(String token, String id) {
        jdbcTemplate.update(getDeleteSql(),token);
        jdbcTemplate.update(getInsertSql(),token,id);
    }

    @Override
    public String getIdForToken(String token) {
        String id = jdbcTemplate.queryForObject(getQuerySql(),String.class,token);
        jdbcTemplate.update(getDeleteSql(),token);
        return id;
    }

    public String getInsertSql() {
        return insertSql != null ? insertSql : INSERT_SQL;
    }

    public void setInsertSql(String insertSql) {
        this.insertSql = insertSql;
    }

    public String getDeleteSql() {
        return deleteSql != null ? deleteSql : DELETE_SQL;
    }

    public void setDeleteSql(String deleteSql) {
        this.deleteSql = deleteSql;
    }

    public String getQuerySql() {
        return querySql != null ? querySql : QUERY_SQL;
    }

    public void setQuerySql(String querySql) {
        this.querySql = querySql;
    }
}
