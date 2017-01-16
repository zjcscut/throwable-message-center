package org.throwable.amqp.core.rabbitmq.constants;

/**
 * @author zhangjinci
 * @version 2017/1/16 16:06
 * @function 重试策略
 */
public enum RetryPolicyEnum {

    NONE,
    SIMPLE,
    BACKOFF,
    RANDOM_BACKOFF

}
