package com.incuventure.cqrs.infrastructure;

import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 */
public class DatabaseTokenProviderTest {

    private static final String QUERY_SQL = "SELECT ID FROM TOKENS WHERE TOKEN = ?";
    private static final String DELETE_SQL = "DELETE FROM TOKENS WHERE TOKEN = ?";
    private static final String INSERT_SQL = "INSERT INTO TOKENS (TOKEN,ID) VALUES (?,?)";

    private DatabaseTokenProvider databaseTokenProvider;

    private JdbcTemplate jdbcTemplate;


    @Before
    public void setup(){
        jdbcTemplate = mock(JdbcTemplate.class);
        when(jdbcTemplate.queryForObject(anyString(),eq(String.class),eq("anyId"))).thenReturn("123-1231");

        databaseTokenProvider = new DatabaseTokenProvider(jdbcTemplate);
    }

    @Test
    public void successfullyGetToken(){
        String retrievedId = databaseTokenProvider.getIdForToken("anyId");
        verify(jdbcTemplate).queryForObject(anyString(),eq(String.class),eq("anyId"));
        verify(jdbcTemplate).update(anyString(),eq("anyId"));
        assertEquals("123-1231",retrievedId);
    }

    @Test
    public void deleteIdWhenAdding(){
       databaseTokenProvider.addTokenForId("token","id");
       verify(jdbcTemplate).update(DELETE_SQL,"token");
       verify(jdbcTemplate).update(INSERT_SQL,"token","id");
    }

    @Test
    public void callInsertWhenStoringTokens(){
        databaseTokenProvider.addTokenForId("token","id");
        verify(jdbcTemplate).update(anyString(),eq("token"),eq("id"));
    }


}
