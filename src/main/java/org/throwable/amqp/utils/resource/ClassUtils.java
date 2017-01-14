package org.throwable.amqp.utils.resource;

/**
 * @author zhangjinci
 * @version 2017/1/14 16:30
 * @function
 */
public final class ClassUtils {

    public static ClassLoader getClassLoader(){
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        if (null == loader){
            loader = ClassUtils.class.getClassLoader();
            if (null == loader){
                loader = ClassLoader.getSystemClassLoader();
            }
        }
        return loader;
    }
}
