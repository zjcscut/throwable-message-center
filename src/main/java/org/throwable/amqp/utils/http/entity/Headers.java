package org.throwable.amqp.utils.http.entity;

import org.apache.http.message.BasicHeader;

import java.util.List;
import java.util.Map;

/**
 * @author zhangjinci
 * @version 2017/1/6 12:04
 * @function
 */
public interface Headers {

    List<BasicHeader> getHeaders();

    void setHeaders(List<BasicHeader> headers);

    void setHeaders(Map<String, String> headerMap);

    DefaultHeaders addHeader(String key, String value);

    DefaultHeaders addHeaders(List<BasicHeader> newHeaders);

    DefaultHeaders addHeaders(Map<String, String> headerMap);

    BasicHeader buildHeader(String key, String value);
}
