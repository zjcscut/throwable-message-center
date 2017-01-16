package org.throwable.amqp.core.rabbitmq.support;

import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitManagementTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.throwable.amqp.context.BeanDefinitionComponent;
import org.throwable.amqp.core.rabbitmq.constants.ContextConstants;
import org.throwable.amqp.core.rabbitmq.entity.ConfigurationParameters;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zjc
 * @version 2017/1/15 21:26
 * @description
 */
public class RabbitmqInstanceComponentFactory {

    @Deprecated
    public static CachingConnectionFactory createCachingConnectionFactory(ConfigurationParameters configurationParameters) {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setUsername(configurationParameters.getUsername());
        connectionFactory.setPassword(configurationParameters.getPassword());
        connectionFactory.setHost(configurationParameters.getHost());
        connectionFactory.setPort(configurationParameters.getPort());
        connectionFactory.setVirtualHost(configurationParameters.getVirtualHost());
        connectionFactory.setPublisherReturns(configurationParameters.getPublisherReturns());  //允许错误路由的发布消息返回
        return connectionFactory;
    }

    private static String createFactoryNameKey(String host, Integer port) {
        return RabbitmqInstanceKeyFactory.generateNameKey(ContextConstants.RABBIT_CONNECTION_FACTORY_NAME,
                host, port);
    }

    public static BeanDefinitionComponent createRabbitConnectionFactoryBeanComponent(ConfigurationParameters configurationParameters) {
        BeanDefinitionComponent component = new BeanDefinitionComponent(CachingConnectionFactory.class);
        component.setBeanName(createFactoryNameKey(configurationParameters.getHost(), configurationParameters.getPort()));
        Map<String, Object> propValues = new HashMap<>();
        propValues.put("username", configurationParameters.getUsername());
        propValues.put("password", configurationParameters.getPassword());
        propValues.put("host", configurationParameters.getHost());
        propValues.put("port", configurationParameters.getPort());
        propValues.put("virtualHost", configurationParameters.getVirtualHost());
        propValues.put("publisherReturns", configurationParameters.getPublisherReturns());
        component.setBeanPropertyValues(propValues);
        return component;
    }

    @Deprecated
    public static RabbitAdmin createRabbitAdmin(CachingConnectionFactory factory) {
        return new RabbitAdmin(factory);
    }

    public static BeanDefinitionComponent createRabbitAdminBeanComponent(CachingConnectionFactory factory) {
        BeanDefinitionComponent component = new BeanDefinitionComponent(RabbitAdmin.class);
        String beanName = RabbitmqInstanceKeyFactory.generateNameKey(ContextConstants.RABBITADMIN_NAME,
                factory.getHost(), factory.getPort());
        component.setBeanName(beanName);
        List<Object> constructorArgValues = new ArrayList<>();
        constructorArgValues.add(factory);
        component.setConstructorArgValues(constructorArgValues);
        return component;
    }

    private static String createManagementTemplateNameKey(String host, Integer port) {
        return RabbitmqInstanceKeyFactory.generateNameKey(ContextConstants.RABBIT_MANAGEMENT_TEMPLATE_NAME,
                host, port);
    }

    public static BeanDefinitionComponent createRabbitManagementTemplateBeanComponent(CachingConnectionFactory factory,
                                                                                      ConfigurationParameters configurationParameters) {
        BeanDefinitionComponent component = new BeanDefinitionComponent(RabbitAdmin.class);
        component.setBeanName(createManagementTemplateNameKey(configurationParameters.getHost(),configurationParameters.getPort()));
        String uri = generateRabbitManagementTemplateUri(factory.getHost(), factory.getPort());
        List<Object> constructorArgValues = new ArrayList<>();
        constructorArgValues.add(uri);
        constructorArgValues.add(factory.getUsername());
        constructorArgValues.add(configurationParameters.getPassword());
        component.setConstructorArgValues(constructorArgValues);
        return component;
    }


    @Deprecated
    public static RabbitManagementTemplate createRabbitManagementTemplate(CachingConnectionFactory factory, String password) {
        String uri = generateRabbitManagementTemplateUri(factory.getHost(), factory.getPort());
        return new RabbitManagementTemplate(uri, factory.getUsername(), password);
    }

    private static String generateRabbitManagementTemplateUri(String host, int port) {
        StringBuilder builder = new StringBuilder(host);
        builder.append(ContextConstants.COLON)
                .append(port)
                .append(ContextConstants.RABBIT_MANAGEMENT_TEMPLATE_URI_CONTEXT);
        return builder.toString();
    }

    public static BeanDefinitionComponent createRabbitTemplateBeanComponent(CachingConnectionFactory factory,
                                                                                      ConfigurationParameters configurationParameters) {
        BeanDefinitionComponent component = new BeanDefinitionComponent(RabbitTemplate.class);
        component.setBeanName(createRabbitTemplateNameKey(configurationParameters.getHost(),configurationParameters.getPort()));
        List<Object> constructorArgValues = new ArrayList<>();
        constructorArgValues.add(factory);
        component.setConstructorArgValues(constructorArgValues);
        Map<String, Object> propValues = new HashMap<>();
        propValues.put("encoding", ContextConstants.DEFAULT_CHARSET_ENCODING);
        propValues.put("mandatory", configurationParameters.getMandatory());
        propValues.put("messageConverter", MessageConverterManager.contentTypeDelegatingMessageConverter());
        component.setBeanPropertyValues(propValues);
        return component;
    }


    private static String createRabbitTemplateNameKey(String host, Integer port) {
        return RabbitmqInstanceKeyFactory.generateNameKey(ContextConstants.RABBIT_TEMPLATE_NAME,
                host, port);
    }

}
