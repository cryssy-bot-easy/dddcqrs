package com.incuventure.cqrs.infrastructure;

import com.incuventure.cqrs.api.WebAPICallDispatcher;
import com.incuventure.cqrs.api.WebAPIHandler;
import com.incuventure.cqrs.api.WebAPIHandlerEntry;
import com.incuventure.ddd.infrastructure.events.EventListener;
import com.incuventure.ddd.infrastructure.events.impl.SimpleEventPublisher;
import com.incuventure.ddd.infrastructure.events.impl.handlers.AsynchronousEventHandler;
import com.incuventure.ddd.infrastructure.events.impl.handlers.EventHandler;
import com.incuventure.ddd.infrastructure.events.impl.handlers.SpringEventHandler;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.DestructionAwareBeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * User: Jett
 * Date: 5/31/12
 */

@Component
public class StandardAPIHandlerProvider implements ApplicationContextAware, DestructionAwareBeanPostProcessor {

    @Autowired
    private ConfigurableListableBeanFactory beanFactory;

    protected ApplicationContext applicationContext;

    @Autowired
    private StandardAPICallDispatcher standardAPICallDispatcher;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public void postProcessBeforeDestruction(Object o, String s) throws BeansException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {

        for (Method method : bean.getClass().getMethods()) {

            WebAPIHandler handlerAnnotation = method.getAnnotation(WebAPIHandler.class);

            // skip on to the next if this one does not have the handler
            if (handlerAnnotation == null) {
                continue;
            }

            try {
                Class<?> parameterType = method.getParameterTypes()[0];

                // check if handles parameter was provided
                Method m = handlerAnnotation.getClass().getMethod("handles");
                String apiMethod = (String) m.invoke(handlerAnnotation);

                WebAPIHandlerEntry entry = new WebAPIHandlerEntry(beanName, method, beanFactory );

                System.out.println("registering handler for : " + apiMethod);
                standardAPICallDispatcher.registerCallHandler(apiMethod, entry);

            }
            catch(NoSuchMethodException nsme) {
                // igno
                System.out.println("1");
            }
            catch(Exception e) {
                // impossible
                System.out.println("2");
                e.printStackTrace();
            }

//            if (listenerAnnotation.asynchronous()){
//                //TODO just a temporary fake impl
//                EventHandler handler = new AsynchronousEventHandler(eventType, beanName, method, beanFactory);
//                //TODO add to some queue
////                eventPublisher.registerEventHandler(handler);
//            }
//            else{
////                System.out.println("**** identified "  + beanName + "." + method.getName() + " as a handler of " + eventType.getName());
//
//                EventHandler handler = new SpringEventHandler(eventType, beanName, method, beanFactory);
//                eventPublisher.registerEventHandler(handler);
//            }
        }

        return bean;
    }
}
