package org.throwable.amqp.utils.http.exception;

/**
 * @author zhangjinci
 * @version 2017/1/7 16:49
 * @function
 */
public class HttpClientException extends RuntimeException{

    public HttpClientException() {
    }

    public HttpClientException(String message) {
        super(message);
    }

    public HttpClientException(Throwable cause) {
        super(cause);
    }

    public HttpClientException(String message, Throwable cause) {
        super(message, cause);
    }
}
