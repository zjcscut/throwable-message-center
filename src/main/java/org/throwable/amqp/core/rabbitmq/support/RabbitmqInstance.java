package org.throwable.amqp.core.rabbitmq.support;

import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitManagementTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

/**
 * @author zjc
 * @version 2017/1/15 21:23
 * @description rabbitmq实例
 */
public class RabbitmqInstance {

	/**
	 * 带缓存的Rabbitmq连接工厂
	 * @see org.springframework.amqp.rabbit.connection.CachingConnectionFactory
	 */
	private CachingConnectionFactory cachingConnectionFactory;
	/**
	 * rabbitmq 超级管理员
	 * @see org.springframework.amqp.rabbit.core.RabbitAdmin
	 */
	private RabbitAdmin rabbitAdmin;
	/**
	 * rabbitTemplate rabbitmq模板
	 * @see org.springframework.amqp.rabbit.core.RabbitTemplate
	 */
	private RabbitTemplate rabbitTemplate;
	/**
	 * rabbitManagementTemplate rabbitmq客户端管理模板
	 * @see org.springframework.amqp.rabbit.core.RabbitManagementTemplate
	 */
	private RabbitManagementTemplate rabbitManagementTemplate;

	public RabbitmqInstance() {
	}

	public CachingConnectionFactory getCachingConnectionFactory() {
		return cachingConnectionFactory;
	}

	public void setCachingConnectionFactory(CachingConnectionFactory cachingConnectionFactory) {
		this.cachingConnectionFactory = cachingConnectionFactory;
	}

	public RabbitAdmin getRabbitAdmin() {
		return rabbitAdmin;
	}

	public void setRabbitAdmin(RabbitAdmin rabbitAdmin) {
		this.rabbitAdmin = rabbitAdmin;
	}

	public RabbitTemplate getRabbitTemplate() {
		return rabbitTemplate;
	}

	public void setRabbitTemplate(RabbitTemplate rabbitTemplate) {
		this.rabbitTemplate = rabbitTemplate;
	}

	public RabbitManagementTemplate getRabbitManagementTemplate() {
		return rabbitManagementTemplate;
	}

	public void setRabbitManagementTemplate(RabbitManagementTemplate rabbitManagementTemplate) {
		this.rabbitManagementTemplate = rabbitManagementTemplate;
	}

}
