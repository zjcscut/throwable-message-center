package org.throwable.amqp.core.rabbitmq.support;

import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitManagementTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.throwable.amqp.context.BeanDefinitionComponent;
import org.throwable.amqp.context.BeanRegisterHandler;
import org.throwable.amqp.core.rabbitmq.entity.BindingParameters;
import org.throwable.amqp.core.rabbitmq.entity.ConfigurationParameters;

import java.util.List;

/**
 * @author zhangjinci
 * @version 2017/1/16 10:34
 * @function rabbitmq实例注册器
 */
@Service
public class RabbitmqInstanceRegisterHandler {

    @Autowired
    @Qualifier(value = "defaultBeanRegisterHandler")
    private BeanRegisterHandler beanRegisterHandler;

    /**
     * 注册rabbitmq实例
     * 必须保证注册构件链的顺序,否则依赖有可能出错,因此所有注册构件的方法设置为私有
     * 注册顺序:
     * 1:带缓存的连接工厂
     * 2:rabbitAdmin
     * 3:rabbitManagementTemplate
     * 4:registerRabbitTemplate
     */
    public void registerRabbitmqInstance(ConfigurationParameters configurationParameters,
                                         List<BindingParameters> bindingParameters) {
        CachingConnectionFactory factory = registerRabbitmqCachingConnectionFactory(configurationParameters);
        registerRabbitmqRabbitAdmin(factory, bindingParameters);
        registerRabbitManagementTemplate(factory, configurationParameters);
        registerRabbitTemplate(factory,configurationParameters);
    }

    private CachingConnectionFactory registerRabbitmqCachingConnectionFactory(ConfigurationParameters configurationParameters) {
        BeanDefinitionComponent component
                = RabbitmqInstanceComponentFactory.createRabbitConnectionFactoryBeanComponent(configurationParameters);
        beanRegisterHandler.registerBeanDefinition(component);
        return beanRegisterHandler.loadBeanFromContext(component.getBeanName(), CachingConnectionFactory.class);
    }

    private void registerRabbitmqRabbitAdmin(CachingConnectionFactory factory,
                                             List<BindingParameters> bindingParameters) {
        BeanDefinitionComponent component = RabbitmqInstanceComponentFactory.createRabbitAdminBeanComponent(factory);
        beanRegisterHandler.registerBeanDefinition(component);
        RabbitAdmin rabbitAdmin = beanRegisterHandler.loadBeanFromContext(component.getBeanName(),
                RabbitAdmin.class);
        RabbitAdminOperator operator = new RabbitAdminOperator(rabbitAdmin);
        operator.init(bindingParameters);
    }

    private void registerRabbitManagementTemplate(CachingConnectionFactory factory,
                                                  ConfigurationParameters configurationParameters) {
        BeanDefinitionComponent component =
                RabbitmqInstanceComponentFactory.createRabbitManagementTemplateBeanComponent(factory, configurationParameters);
        beanRegisterHandler.registerBeanDefinition(component);
    }

    private void registerRabbitTemplate(CachingConnectionFactory factory,
                                        ConfigurationParameters configurationParameters) {
        BeanDefinitionComponent component =
                RabbitmqInstanceComponentFactory.createRabbitTemplateBeanComponent(factory, configurationParameters);
        beanRegisterHandler.registerBeanDefinition(component);
    }
}
