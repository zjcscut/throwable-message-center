package org.throwable.amqp.utils.http.entity;

import org.apache.http.message.BasicHeader;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author zhangjinci
 * @version 2017/1/6 12:07
 * @function
 */
public class DefaultHeaders implements Headers {

    List<BasicHeader> headers;

    public DefaultHeaders() {
        this.headers = new ArrayList<BasicHeader>();
    }

    public List<BasicHeader> getHeaders() {
        return headers;
    }

    public void setHeaders(List<BasicHeader> headers) {
        this.headers = headers;
    }

    public void setHeaders(Map<String, String> headerMap) {
        if (null != headerMap && !headerMap.isEmpty()) {
            headers.clear();
            for (Map.Entry<String, String> entry : headerMap.entrySet()) {
                headers.add(new BasicHeader(entry.getKey(), entry.getValue()));
            }
        }
    }

    public DefaultHeaders addHeader(String key, String value) {
        this.headers.add(new BasicHeader(key, value));
        return this;
    }

    public DefaultHeaders addHeaders(List<BasicHeader> newHeaders) {
        this.headers.addAll(newHeaders);
        return this;
    }

    public DefaultHeaders addHeaders(Map<String, String> headerMap) {
        if (null != headerMap && !headerMap.isEmpty()) {
            for (Map.Entry<String, String> entry : headerMap.entrySet()) {
                headers.add(new BasicHeader(entry.getKey(), entry.getValue()));
            }
        }
        return this;
    }

    public BasicHeader buildHeader(String key, String value) {
        return new BasicHeader(key, value);
    }

}
