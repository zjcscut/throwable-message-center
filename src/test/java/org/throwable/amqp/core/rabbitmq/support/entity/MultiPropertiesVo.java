package org.throwable.amqp.core.rabbitmq.support.entity;

import org.throwable.amqp.core.rabbitmq.entity.BindingParameters;
import org.throwable.amqp.core.rabbitmq.entity.ConfigurationParameters;

import java.util.List;

/**
 * @author zhangjinci
 * @version 2017/1/16 17:50
 * @function
 */
public class MultiPropertiesVo {

    private ConfigurationParameters configurationParameters;
    private List<BindingParameters> BindingParametersList;

    public MultiPropertiesVo() {
    }

    public ConfigurationParameters getConfigurationParameters() {
        return configurationParameters;
    }

    public void setConfigurationParameters(ConfigurationParameters configurationParameters) {
        this.configurationParameters = configurationParameters;
    }

    public List<BindingParameters> getBindingParametersList() {
        return BindingParametersList;
    }

    public void setBindingParametersList(List<BindingParameters> bindingParametersList) {
        BindingParametersList = bindingParametersList;
    }
}
