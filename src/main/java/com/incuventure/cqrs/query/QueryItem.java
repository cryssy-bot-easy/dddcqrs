package com.incuventure.cqrs.query;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * User: Jett
 * Date: 7/30/12
 */
public class QueryItem implements Serializable {

    String interfaceName;
    String returnKey;
    String methodName;
    List<Object> args;

    public String getMethodName() {
        return methodName;
    }

    public QueryItem(String returnKey, Class<?> iface, String methodName, Object... args) throws NoSuchMethodException {

        // array of classes for our
        Class<?>[] paramClasses = new Class<?>[args.length];

        this.returnKey = returnKey;
        this.interfaceName = iface.getSimpleName();
        this.args = new ArrayList<Object>();
        this.methodName = methodName;


        // create an array of param types so we can check if a method exist
        // in the same loop, we also set the internal args variable to the value;
        for(int index=0; index < paramClasses.length; index++) {

            paramClasses[index] = args[index].getClass();
//            this.args.add(args[0]);

            // add all parameters in args instead of just the first parameter (in args[0)
            this.args.add(args[index]);
        }

        // attempt to find a method with the given name and number of String parameters;
        Method method = iface.getMethod(methodName, paramClasses);

    }

    public String getInterfaceName() {
        return interfaceName;
    }

    public String getReturnKey() {
        return returnKey;
    }

    public List<Object> getArgs() {
        return args;
    }
}
