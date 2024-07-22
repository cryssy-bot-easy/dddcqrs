package com.incuventure.tests.token;

import com.incuventure.cqrs.token.TokenProvider;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * User: Jett
 * Date: 8/4/12
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/engine-test/engineTestContext.xml")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
public class TokenTest {

    @Autowired
    TokenProvider tokenProvider;

    @Test
    public void TestToken() {

        tokenProvider.addTokenForId("1234455", "tokenValue");

        // it should retrieve the value the first time we get it
        Assert.assertEquals(tokenProvider.getIdForToken("1234455"), "tokenValue");

        // the token should no longer be available when we retrieve it again
        Assert.assertNull(tokenProvider.getIdForToken("1234455"));


    }
}
