package org.throwable.amqp.core.rabbitmq.constants;

/**
 * @author zhangjinci
 * @version 2017/1/16 9:52
 * @function
 */
public interface ContextConstants {

    String COLON = ":";

    String RABBIT_MANAGEMENT_TEMPLATE_URI_CONTEXT = "/api";

    String DEFAULT_RABBIT_VIRTUAL_HOST = "/";

    String DEFAULT_NAMEKEY_PREFIX = "$$";

    String RABBITADMIN_NAME = "rabbitAdmin";

    String RABBIT_CONNECTION_FACTORY_NAME = "cachingConnectionFactory";

    String RABBIT_MANAGEMENT_TEMPLATE_NAME = "rabbitManagementTemplate";

    String RABBIT_TEMPLATE_NAME = "rabbitTemplate";

    String DEFAULT_CHARSET_ENCODING= "UTF-8";
}
