package com.incuventure.cqrs.infrastructure;

import com.incuventure.cqrs.annotation.CommandHandler;
import com.incuventure.cqrs.command.HandlerProvider;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import javax.inject.Inject;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;

/**
 * User: Jett
 * Date: 6/11/12
 */
public class StandardCommandHandlerProvider implements HandlerProvider, ApplicationListener<ContextRefreshedEvent> {

    @Inject
    private ConfigurableListableBeanFactory beanFactory;

    // two handler registries: one for standard Commands the other for WebCommands
    private Map<Class<?>, String> handlers = new HashMap<Class<?>, String>();
    private Map<String, String> webCommandHandlers = new HashMap<String, String>();

    @SuppressWarnings("unchecked")
    @Override
    public CommandHandler<Object> getHandler(Object command) {
        String beanName = handlers.get(command.getClass());
        if (beanName == null) {
            throw new RuntimeException("command handler not found. Command class is " + command.getClass());
        }
        CommandHandler<Object> handler = beanFactory.getBean(beanName, CommandHandler.class);
        return handler;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        handlers.clear();
        String[] commandHandlersNames = beanFactory.getBeanNamesForType(CommandHandler.class);
        for (String beanName : commandHandlersNames) {
            BeanDefinition commandHandler = beanFactory.getBeanDefinition(beanName);
            try {
                Class<?> handlerClass = Class.forName(commandHandler.getBeanClassName());
                handlers.put(getHandledCommandType(handlerClass), beanName);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private Class<?> getHandledCommandType(Class<?> clazz) {
        Type[] genericInterfaces = clazz.getGenericInterfaces();
        ParameterizedType type = findByRawType(genericInterfaces, CommandHandler.class);
        return (Class<?>) type.getActualTypeArguments()[0];
    }

    private ParameterizedType findByRawType(Type[] genericInterfaces, Class<?> expectedRawType) {
        for (Type type : genericInterfaces) {
            if (type instanceof ParameterizedType) {
                ParameterizedType parametrized = (ParameterizedType) type;
                if (expectedRawType.equals(parametrized.getRawType())) {
                    return parametrized;
                }
            }
        }
        throw new RuntimeException();
    }
}

