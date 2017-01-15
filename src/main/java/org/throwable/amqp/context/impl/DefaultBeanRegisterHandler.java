package org.throwable.amqp.context.impl;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Service;
import org.throwable.amqp.context.BeanDefinitionComponent;
import org.throwable.amqp.context.BeanRegisterComponentFactory;
import org.throwable.amqp.context.BeanRegisterHandler;
import org.throwable.amqp.exception.BeanRegisterHandleException;
import org.throwable.amqp.utils.resource.ClassUtils;

/**
 * @author zhangjinci
 * @version 2017/1/14 16:28
 * @function
 */
@Service
public class DefaultBeanRegisterHandler implements BeanRegisterHandler, ApplicationContextAware {

	private ApplicationContext context;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.context = applicationContext;
	}

	@Override
	public void registerBeanDefinition(BeanDefinitionComponent component) {
		BeanDefinition beanDefinition = BeanRegisterComponentFactory.processBeanDefinitionComponent(component);
		getDefaultListableBeanFactory().registerBeanDefinition(component.getBeanName(), beanDefinition);
	}

	@Override
	public Class<?> loadContextClass(String className) {
		try {
			return ClassUtils.getClassLoader().loadClass(className);
		} catch (ClassNotFoundException e) {
			throw new BeanRegisterHandleException(e);
		}
	}

	@Override
	public Object loadBeanFromContext(String beanName) {
		return context.getBean(beanName);
	}

	@Override
	public <T> T loadBeanFromContext(String beanName, Class<T> clazz) {
		return context.getBean(beanName, clazz);
	}

	@Override
	public <T> T loadBeanFromContext(Class<T> clazz) {
		return context.getBean(clazz);
	}

	@Override
	public void removeBeanFromContext(String beanName) {
		DefaultListableBeanFactory beanFactory = getDefaultListableBeanFactory();
		if (beanFactory.containsBeanDefinition(beanName)) {
			beanFactory.removeBeanDefinition(beanName);
		}
	}


	private DefaultListableBeanFactory getDefaultListableBeanFactory() {
		ConfigurableApplicationContext configurableApplicationContext = (ConfigurableApplicationContext) context;
		return (DefaultListableBeanFactory) configurableApplicationContext.getBeanFactory();
	}
}
