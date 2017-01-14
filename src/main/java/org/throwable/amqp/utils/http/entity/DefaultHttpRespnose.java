package org.throwable.amqp.utils.http.entity;

import org.apache.http.Header;

/**
 * @author zhangjinci
 * @version 2017/1/6 12:09
 * @function
 */
public class DefaultHttpRespnose implements Response {

    private int statusCode; //状态码
    private String content; //返回值内容
    private Header[] headers; //所有请求头
    private Header contentType; //contentType
    private Header contentEncoding; //编码
    private long contentLength; //内容长度

    public DefaultHttpRespnose() {
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Header[] getHeaders() {
        return headers;
    }

    public void setHeaders(Header[] headers) {
        this.headers = headers;
    }

    public Header getContentType() {
        return contentType;
    }

    public void setContentType(Header contentType) {
        this.contentType = contentType;
    }

    public Header getContentEncoding() {
        return contentEncoding;
    }

    public void setContentEncoding(Header contentEncoding) {
        this.contentEncoding = contentEncoding;
    }

    public long getContentLength() {
        return contentLength;
    }

    public void setContentLength(long contentLength) {
        this.contentLength = contentLength;
    }
}
