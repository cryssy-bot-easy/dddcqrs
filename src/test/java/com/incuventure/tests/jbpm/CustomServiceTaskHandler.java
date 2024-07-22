package com.incuventure.tests.jbpm;

import org.drools.runtime.process.WorkItem;
import org.drools.runtime.process.WorkItemHandler;
import org.drools.runtime.process.WorkItemManager;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * User: Jett
 * Date: 6/7/12
 */
public class CustomServiceTaskHandler implements WorkItemHandler {

    public void executeWorkItem(WorkItem workItem, WorkItemManager manager) {

        String i = (String) workItem.getParameter("Interface");
        String operation = (String) workItem.getParameter("Operation");
        String parameterType = (String) workItem.getParameter("ParameterType");
        Object parameter = workItem.getParameter("Parameter");
        try {
            Class<?> c = Class.forName(i);
            Object instance = c.newInstance();
            Class<?>[] classes = null;
            Object[] params = null;
            if (parameterType != null) {
                classes = new Class<?>[] {
                        Class.forName(parameterType)
                };
                params = new Object[] {
                        parameter
                };
            }
            Method method = c.getMethod(operation, classes);
            Object result = method.invoke(instance, params);
            Map<String, Object> results = new HashMap<String, Object>();
            results.put("Result", result);

            for(String param : results.keySet()) {
                System.out.println(workItem.getName() +  " parameter: " + param + " value: " + results.get(param));
            }

            System.out.println("marking service task node as completed");
            manager.completeWorkItem(workItem.getId(), results);
        } catch (ClassNotFoundException e) {
            System.err.println(e);
        } catch (InstantiationException e) {
            System.err.println(e);
        } catch (IllegalAccessException e) {
            System.err.println(e);
        } catch (NoSuchMethodException e) {
            System.err.println(e);
        } catch (InvocationTargetException e) {
            System.err.println(e);
        }
    }

    public void abortWorkItem(WorkItem workItem, WorkItemManager manager) {
        // Do nothing, cannot be aborted
    }


}
