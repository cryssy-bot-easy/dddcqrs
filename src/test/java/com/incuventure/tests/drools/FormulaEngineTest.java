package com.incuventure.tests.drools;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mvel2.MVEL;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * User: Jett
 * Date: 7/24/12
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/engine-test/engineTestContext.xml"})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
@Ignore
public class FormulaEngineTest {

    @Test
    public void testFormula() {
        String formulaExpression = "amount * factor";

        Map vars = new HashMap();
        vars.put("amount", new BigDecimal(50.01));
        vars.put("factor", new BigDecimal(0.5));

        BigDecimal result = (BigDecimal) MVEL.eval(formulaExpression, vars);
        System.out.println(result.toString());

    }

}

