package org.throwable.amqp.utils.http.entity;

import org.apache.http.client.config.RequestConfig;

/**
 * @author zhangjinci
 * @version 2017/1/6 12:02
 * @function
 */
public interface Request {

    Headers getRequestHeaders();

    Parameters getRequestParameters();

    RequestConfig.Builder getRequestConfigBuilder();
}
