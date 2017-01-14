package org.throwable.amqp.context.impl;

/**
 * @author zhangjinci
 * @version 2017/1/14 16:51
 * @function
 */
public class UserService {

    private String name;

    public void test(){
        System.out.println("test方法被调用 --- " + name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
