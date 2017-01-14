package org.throwable.amqp.context.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.throwable.Application;
import org.throwable.amqp.context.BeanRegisterHandler;

import static org.junit.Assert.*;

/**
 * @author zhangjinci
 * @version 2017/1/14 16:49
 * @function
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class DefaultBeanRegisterHandlerTest {

    @Autowired
    @Qualifier(value = "defaultBeanRegisterHandler")
    private BeanRegisterHandler defaultBeanRegisterHandler;

    @Test
    public void test1() throws Exception {
//        defaultBeanRegisterHandler.registerBeanDefinition("org.throwable.amqp.context.impl.UserService");

        Object bean = defaultBeanRegisterHandler.loadBeanFromContext("org.throwable.amqp.context.impl.UserService");
        UserService userService = (UserService) bean;
        userService.test();
    }

}