package org.throwable.amqp.exception;

/**
 * @author zhangjinci
 * @version 2017/1/14 16:35
 * @function
 */
public class BeanRegisterHandleException extends RuntimeException {

    public BeanRegisterHandleException(String message) {
        super(message);
    }

    public BeanRegisterHandleException(String message, Throwable cause) {
        super(message, cause);
    }

    public BeanRegisterHandleException(Throwable cause) {
        super(cause);
    }
}
