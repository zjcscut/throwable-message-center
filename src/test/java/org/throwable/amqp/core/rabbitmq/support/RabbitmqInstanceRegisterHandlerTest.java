package org.throwable.amqp.core.rabbitmq.support;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.throwable.Application;
import org.throwable.amqp.core.rabbitmq.entity.BindingParameters;
import org.throwable.amqp.core.rabbitmq.entity.ConfigurationParameters;
import org.throwable.amqp.core.rabbitmq.support.entity.MultiPropertiesVo;
import org.throwable.amqp.utils.json.JacksonMapper;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author zhangjinci
 * @version 2017/1/16 17:48
 * @function
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class RabbitmqInstanceRegisterHandlerTest {

    @Autowired
    private RabbitmqInstanceRegisterHandler rabbitmqInstanceRegisterHandler;

    @Test
    public void Test1() throws Exception {
        List<MultiPropertiesVo> multiPropertiesVos = JacksonMapper.parseListFromJsonFile("/config.json", MultiPropertiesVo.class);
        for (MultiPropertiesVo multiPropertiesVo : multiPropertiesVos) {
            rabbitmqInstanceRegisterHandler.registerRabbitmqInstance(multiPropertiesVo.getConfigurationParameters(),
                    multiPropertiesVo.getBindingParametersList());

        }


        System.in.read();
    }


}