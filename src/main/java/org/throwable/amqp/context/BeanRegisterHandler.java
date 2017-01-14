package org.throwable.amqp.context;


/**
 * @author zhangjinci
 * @version 2017/1/14 16:25
 * @function
 */
public interface BeanRegisterHandler {


    void registerBeanDefinition(BeanDefinitionComponent component);

    Class<?> loadContextClass(String className);

    Object loadBeanFromContext(String beanName);
}
