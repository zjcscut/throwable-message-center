package org.throwable.amqp.utils.http.entity;


import org.apache.http.client.config.RequestConfig;

/**
 * @author zhangjinci
 * @version 2017/1/6 12:08
 * @function
 */
public class DefaultHttpRequest implements Request {

    private Headers headers;
    private Parameters parameters;
    private RequestConfig.Builder requestConfigBuilder;

    public DefaultHttpRequest() {
        this.headers = new DefaultHeaders();
        this.parameters = new DefaultParameters();
        requestConfigBuilder = RequestConfig.custom();
    }

    @Override
    public Headers getRequestHeaders() {
        return headers;
    }

    @Override
    public Parameters getRequestParameters() {
        return parameters;
    }

    public void setHeaders(Headers headers) {
        this.headers = headers;
    }

    public void setParameters(Parameters parameters) {
        this.parameters = parameters;
    }

    public RequestConfig.Builder getRequestConfigBuilder() {
        return requestConfigBuilder;
    }

    public void setRequestConfigBuilder(RequestConfig.Builder requestConfigBuilder) {
        this.requestConfigBuilder = requestConfigBuilder;
    }
}
