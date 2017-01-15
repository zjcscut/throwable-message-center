package org.throwable.amqp.context.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.throwable.Application;
import org.throwable.amqp.context.BeanDefinitionComponent;
import org.throwable.amqp.context.BeanRegisterHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		BeanDefinitionComponent componentU = new BeanDefinitionComponent(UserDAO.class);
		defaultBeanRegisterHandler.registerBeanDefinition(componentU);

		BeanDefinitionComponent component = new BeanDefinitionComponent(UserService.class);

		Map<String,String> ref = new HashMap<>(1);
		ref.put("userDAO","userDAO");
		component.setBeanPropertyReferences(ref);
        defaultBeanRegisterHandler.registerBeanDefinition(component);

		UserService userService = defaultBeanRegisterHandler.loadBeanFromContext(UserService.class);
        userService.test();
    }

}