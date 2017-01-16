package org.throwable.amqp.core.rabbitmq.entity;

import org.throwable.amqp.core.rabbitmq.constants.ContextConstants;

/**
 * @author zhangjinci
 * @version 2017/1/16 15:50
 * @function
 */
public class ConfigurationParameters {

    private String username;
    private String password;
    private String host;
    private Integer port;
    private String virtualHost;
    private Boolean publisherReturns;
    private Boolean mandatory;
    private String retryPolicy;

    public ConfigurationParameters() {
    }

    public ConfigurationParameters(String username, String password, String host, Integer port) {
        this.username = username;
        this.password = password;
        this.host = host;
        this.port = port;
        this.virtualHost = ContextConstants.DEFAULT_RABBIT_VIRTUAL_HOST;
    }

    public ConfigurationParameters(String username, String password, String host, Integer port, String virtualHost) {
        this.username = username;
        this.password = password;
        this.host = host;
        this.port = port;
        this.virtualHost = virtualHost;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getVirtualHost() {
        return virtualHost;
    }

    public void setVirtualHost(String virtualHost) {
        this.virtualHost = virtualHost;
    }

    public Boolean getPublisherReturns() {
        return publisherReturns;
    }

    public void setPublisherReturns(Boolean publisherReturns) {
        this.publisherReturns = publisherReturns;
    }

    public String getRetryPolicy() {
        return retryPolicy;
    }

    public void setRetryPolicy(String retryPolicy) {
        this.retryPolicy = retryPolicy;
    }

    public Boolean getMandatory() {
        return mandatory;
    }

    public void setMandatory(Boolean mandatory) {
        this.mandatory = mandatory;
    }
}
