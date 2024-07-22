package com.incuventure.tests.queries;

import java.util.HashMap;
import java.util.List;

/**
 * User: Jett
 * Date: 7/30/12
 */
public interface ITestFinder {

    public List<HashMap<String, String>> findNoArg();
    public List<HashMap<String, String>> findOneArg();
    public List<HashMap<String, String>> findMultiArgs(String arg1, String arg2);

}
