package org.throwable.amqp.core.rabbitmq.support;

import org.springframework.util.Assert;
import org.throwable.amqp.core.rabbitmq.constants.ContextConstants;

/**
 * @author zhangjinci
 * @version 2017/1/16 16:40
 * @function
 */
public final class RabbitmqInstanceKeyFactory {

    public static String generateKey(String host, Integer port) {
        Assert.hasText(host);
        Assert.notNull(port);
        return new StringBuilder(host).append(ContextConstants.COLON).append(port).toString();
    }

    public static String generateNameKey(String name, String host, Integer port) {
        Assert.hasText(name);
        return new StringBuilder(name).append(ContextConstants.DEFAULT_NAMEKEY_PREFIX)
                .append(generateKey(host, port)).toString();
    }


}
