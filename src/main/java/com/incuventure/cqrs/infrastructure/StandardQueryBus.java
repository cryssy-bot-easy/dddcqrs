package com.incuventure.cqrs.infrastructure;

import com.incuventure.cqrs.query.QueryBus;
import com.incuventure.cqrs.query.QueryItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import javax.inject.Inject;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: Jett
 * Date: 7/30/12
 */
public class StandardQueryBus implements QueryBus{

    @Inject
    ApplicationContext applicationContext;

    @Override
    public HashMap<String, Object> dispatch(List<QueryItem> items) throws QueryException{

        HashMap<String, Object> resultSet = new HashMap<String, Object>();

        for(QueryItem qi : items) {

            // create a placeholder for our param classes
            Class<?>[] paramClasses = new Class<?>[qi.getArgs().size()];

            // create a place holder for all our parameters
            Object[] params = new Object[qi.getArgs().size()];
            
            // get list of all parameters
            List<Object> paramList = qi.getArgs();
            
            // assign each parameter to params 
            for(int idx = 0; idx < paramList.size(); idx++) {
            	params[idx] = paramList.get(idx);
            }

            Object instance = applicationContext.getBean(qi.getInterfaceName());

            // set all params to be of String type
            for(int index=0; index < paramClasses.length; index++) {
//                paramClasses[index] = String.class;
                paramClasses[index] = qi.getArgs().get(index).getClass();
            }

            // get the method specified
            try {
                Method method = instance.getClass().getMethod(qi.getMethodName(), paramClasses);

                // invoke the method
                Object result = method.invoke(instance, params);

                // if the query returned a list, add the list to the resultSet
                if(result instanceof List) {

                    // put the object inthe resultset using the key specified
                    resultSet.put(qi.getReturnKey(), result);

                }
                else if (result instanceof Map) {

                    // if it is not a list, create a list, add the result to the list and add it
                    // to the HashMap. We always return a list to the caller
                    List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
                    list.add((HashMap<String, Object>) result);

                    resultSet.put(qi.getReturnKey(), list);
                } else if (result instanceof String) {

                    // if this is a string (happens when we use the Token Provider interface), create
                    // a list of the string
                    List<String> list = new ArrayList<String>();
                    list.add((String) result);

                    resultSet.put(qi.getReturnKey(), list);

                }  else {
                    List<Object> list = new ArrayList<Object>();
                    list.add(result);
                    resultSet.put(qi.getReturnKey(),list);
                }


            }
            catch(NoSuchMethodException nsme) {
                System.out.println("nsme\n"+nsme.getMessage());
                nsme.printStackTrace();
                throw new QueryException(nsme);
            }
            catch(InvocationTargetException ite) {
                System.out.println("ite\n"+ite.getMessage());
                ite.printStackTrace();
                throw new QueryException(ite);
            }
            catch(IllegalAccessException iae) {
                throw new QueryException(iae);
            }
            catch(Exception e) {
                System.out.println("Failed to dispatch");
                System.out.println("e\n"+e.getMessage());
                e.printStackTrace();
                throw new QueryException(e);
            }

        }

        // return the result set
        return resultSet;

    }

}
