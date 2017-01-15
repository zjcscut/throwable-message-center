package org.throwable.amqp.core.rabbitmq;

import org.springframework.amqp.support.converter.ContentTypeDelegatingMessageConverter;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;

/**
 * @author zjc
 * @version 2017/1/15 20:39
 * @description
 */
@Configuration
public class RabbitmqAutoConfiguration  {

	/**
	 * 这个类是在1.4.2版本中引入的，并可基于MessageProperties的contentType属性允许委派给一个特定的MessageConverter.
	 * 默认情况下，如果没有contentType属性或值没有匹配配置转换器时，它会委派给SimpleMessageConverter.
	 *
	 * @return converter
	 * @see org.springframework.amqp.support.converter.SimpleMessageConverter
	 */
	@Bean(name = "contentTypeDelegatingMessageConverter")
	public ContentTypeDelegatingMessageConverter contentTypeDelegatingMessageConverter() {
		ContentTypeDelegatingMessageConverter converter = new ContentTypeDelegatingMessageConverter();
		converter.addDelegate(MediaType.APPLICATION_JSON_VALUE, new Jackson2JsonMessageConverter()); //添加处理application/json消息的转换器
		converter.addDelegate(MediaType.TEXT_PLAIN_VALUE, new Jackson2JsonMessageConverter());
		return converter;
	}



}
