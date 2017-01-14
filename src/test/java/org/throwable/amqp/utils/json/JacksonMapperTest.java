package org.throwable.amqp.utils.json;

import org.junit.Test;
import org.throwable.amqp.utils.json.vo.User;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @author zhangjinci
 * @version 2017/1/14 15:25
 * @function
 */
public class JacksonMapperTest {


    @Test
    public void TestParse() throws Exception {
        List<User> users = JacksonMapper.parseListFromJsonFile("mq.json", User.class);
        assertEquals(2, users.size());
        System.out.println(users.toString());
    }

}