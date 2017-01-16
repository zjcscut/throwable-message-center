package org.throwable.amqp.core.rabbitmq.support;

import org.springframework.amqp.support.converter.ContentTypeDelegatingMessageConverter;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.http.MediaType;

/**
 * @author zhangjinci
 * @version 2017/1/16 17:46
 * @function
 */
public final class MessageConverterManager {

    public static ContentTypeDelegatingMessageConverter contentTypeDelegatingMessageConverter() {
        ContentTypeDelegatingMessageConverter converter = new ContentTypeDelegatingMessageConverter();
        converter.addDelegate(MediaType.APPLICATION_JSON_VALUE, new Jackson2JsonMessageConverter());
        converter.addDelegate(MediaType.TEXT_PLAIN_VALUE, new Jackson2JsonMessageConverter());
        return converter;
    }
}
