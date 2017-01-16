package org.throwable.amqp.core.rabbitmq.support;

import org.junit.Test;
import org.throwable.amqp.core.rabbitmq.entity.BindingParameters;
import org.throwable.amqp.core.rabbitmq.entity.ConfigurationParameters;
import org.throwable.amqp.core.rabbitmq.support.entity.MultiPropertiesVo;
import org.throwable.amqp.utils.json.JacksonMapper;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangjinci
 * @version 2017/1/16 18:02
 * @function
 */
public class TestBuildVo {

    @Test
    public void Test2()throws Exception{
        List<MultiPropertiesVo> multiPropertiesVos = new ArrayList<>();
        MultiPropertiesVo vo1  = new MultiPropertiesVo();
        ConfigurationParameters config1 = new ConfigurationParameters("guest","guest","localhost", 5672);
        config1.setMandatory(true);
        config1.setPublisherReturns(true);
        vo1.setConfigurationParameters(config1);
        List<BindingParameters> list1 = new ArrayList<>();
        BindingParameters p1 = new BindingParameters();
        p1.setDescription("111111");
        p1.setExchangeName("exchange1");
        p1.setExchangeType("DIRECT");
        p1.setQueueName("queue-1");
        p1.setRoutingKey("queue-key-1");
        list1.add(p1);
        vo1.setBindingParametersList(list1);
        multiPropertiesVos.add(vo1);

        MultiPropertiesVo vo2  = new MultiPropertiesVo();
        ConfigurationParameters config2 = new ConfigurationParameters("guest","guest","192.168.1.39", 5672);
        config2.setMandatory(true);
        config2.setPublisherReturns(true);
        vo2.setConfigurationParameters(config2);
        List<BindingParameters> list2 = new ArrayList<>();
        BindingParameters p2 = new BindingParameters();
        p2.setDescription("2222222");
        p2.setExchangeName("exchange2");
        p2.setExchangeType("DIRECT");
        p2.setQueueName("queue-2");
        p2.setRoutingKey("queue-key-2");
        list2.add(p2);
        vo2.setBindingParametersList(list2);
        multiPropertiesVos.add(vo2);

        System.out.println(JacksonMapper.buildNormalMapper().toJson(multiPropertiesVos));
    }
}
