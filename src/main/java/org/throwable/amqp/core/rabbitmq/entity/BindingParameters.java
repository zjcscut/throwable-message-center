package org.throwable.amqp.core.rabbitmq.entity;

/**
 * @author zhangjinci
 * @version 2017/1/16 15:49
 * @function
 */
public class BindingParameters {

    private String queueName;
    private String exchangeName;
    private String exchangeType;
    private String routingKey;
    private String description;

    public BindingParameters() {
    }

    public BindingParameters(String queueName, String exchangeName, String exchangeType, String routingKey) {
        this.queueName = queueName;
        this.exchangeName = exchangeName;
        this.exchangeType = exchangeType;
        this.routingKey = routingKey;
    }

    public String getQueueName() {
        return queueName;
    }

    public void setQueueName(String queueName) {
        this.queueName = queueName;
    }

    public String getExchangeName() {
        return exchangeName;
    }

    public void setExchangeName(String exchangeName) {
        this.exchangeName = exchangeName;
    }

    public String getExchangeType() {
        return exchangeType;
    }

    public void setExchangeType(String exchangeType) {
        this.exchangeType = exchangeType;
    }

    public String getRoutingKey() {
        return routingKey;
    }

    public void setRoutingKey(String routingKey) {
        this.routingKey = routingKey;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
