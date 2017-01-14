package org.throwable.amqp.utils.http;

import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.throwable.amqp.utils.http.constant.HttpMethodEnum;
import org.throwable.amqp.utils.http.exception.HttpClientException;
import org.throwable.amqp.utils.http.entity.Headers;
import org.throwable.amqp.utils.http.entity.Parameters;
import  org.throwable.amqp.utils.http.entity.Request;
import org.throwable.amqp.utils.http.entity.DefaultHttpRespnose;
import org.throwable.amqp.utils.http.entity.DefaultHttpRequest;
import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * @author zjc
 * @version 2017/1/7 11:46
 * @function 线程安全的Http客户端工具类
 */
public final class HttpClient {

    private static final Logger log = LoggerFactory.getLogger(HttpClient.class);

    private int socketTimeOut = 15000; //默认socket超时时间,单位:毫秒
    private int connectTimeOut = 15000; //默认connection超时时间,单位:毫秒
    private int connectRequestTimeOut = 15000; //默认connectRequest超时时间,单位:毫秒
    private final static int DEFAULT_TIMEOUT_SECONDS = 15;

    private Headers headers;
    private Parameters parameters;
    private Request request;

    HttpClient() {
        request = new DefaultHttpRequest();
        headers = request.getRequestHeaders();
        parameters = request.getRequestParameters();
    }

    public static HttpClient getInstance() {
        return new HttpClient();
    }

    public HttpClient addHeader(String key, String value) {
        headers.addHeader(key, value);
        return this;
    }

    public HttpClient addParameter(String key, String value) {
        parameters.addParameter(key, value);
        return this;
    }

    public String doGet(String uri,
                        Map<String, String> paramMap) throws HttpClientException {
        return doGet(uri, null, paramMap, DEFAULT_TIMEOUT_SECONDS).getContent();
    }

    public String doGet(String uri,
                        Map<String, String> paramMap, int timeOutSeconds) throws HttpClientException {
        return doGet(uri, null, paramMap, timeOutSeconds).getContent();
    }

    public DefaultHttpRespnose doGet(String uri, Map<String, String> headerMap,
                                                                                     Map<String, String> paramMap, int timeOutSeconds) throws HttpClientException {
        return doSendHttpRequest(HttpMethodEnum.GET, uri, headerMap, paramMap, null, timeOutSeconds);
    }

    public String doPost(String uri,
                         Map<String, String> paramMap) throws HttpClientException {
        return doPost(uri, null, paramMap, DEFAULT_TIMEOUT_SECONDS).getContent();
    }

    public String doPost(String uri,
                         Map<String, String> paramMap, int timeOutSeconds) throws HttpClientException {
        return doPost(uri, null, paramMap, timeOutSeconds).getContent();
    }

    public DefaultHttpRespnose doPost(String uri, Map<String, String> headerMap,
                                                                                      Map<String, String> paramMap, int timeOutSeconds) throws HttpClientException {
        return doSendHttpRequest(HttpMethodEnum.POST, uri, headerMap, paramMap, null, timeOutSeconds);
    }

    public DefaultHttpRespnose doMultiPost(String uri, List<File> files,
                                                                                           int timeOutSeconds) throws HttpClientException {
        return doMultiPost(uri, null, null, files, timeOutSeconds);
    }


    public DefaultHttpRespnose doMultiPost(String uri, Map<String, String> paramMap, List<File> files,
                                                                                           int timeOutSeconds) throws HttpClientException {
        return doMultiPost(uri, null, paramMap, files, timeOutSeconds);
    }

    public DefaultHttpRespnose doMultiPost(String uri, Map<String, String> headerMap,
                                                                                           Map<String, String> paramMap, List<File> files,
                                                                                           int timeOutSeconds) throws HttpClientException {
        return doSendHttpRequest(HttpMethodEnum.MULTI_POST, uri, headerMap, paramMap, files, timeOutSeconds);
    }

    public DefaultHttpRespnose doSendHttpRequest(HttpMethodEnum methodEnum, String url,
                                                                                                 Map<String, String> headerMap,
                                                                                                 Map<String, String> paramMap,
                                                                                                 List<File> files, int timeOutSeconds) {
        String responseResult;
        headers.addHeaders(headerMap);
        parameters.addParameters(paramMap);
        setTimeOutInner(timeOutSeconds);
        RequestConfig config = request.getRequestConfigBuilder()
                .setSocketTimeout(socketTimeOut)
                .setConnectTimeout(connectTimeOut)
                .setConnectionRequestTimeout(connectRequestTimeOut)
                .build();
        HttpRequestBase requestBase
                = HttpClientComponentFactory.buildHttpRequestByMethod(methodEnum, url, headers, parameters, files);
        requestBase.setConfig(config);
        DefaultHttpRespnose result = new DefaultHttpRespnose();
        try (CloseableHttpClient client = HttpClientComponentFactory.buildDefailtHttpClient();
             CloseableHttpResponse response = client.execute(requestBase)) {
            result.setStatusCode(response.getStatusLine().getStatusCode());
            result.setHeaders(response.getAllHeaders());
            HttpEntity entity = response.getEntity();
            result.setContentEncoding(entity.getContentEncoding());
            result.setContentType(entity.getContentType());
            result.setContentLength(entity.getContentLength());
            responseResult = EntityUtils.toString(entity, HttpClientComponentFactory.DEFAULT_CHARSET);
            if (null == responseResult || responseResult.length() == 0) {
                responseResult = null;
            }
            result.setContent(responseResult);
            return result;
        } catch (Exception e) {
            if (log.isDebugEnabled()) {
                log.error(String.format("send http request failed,url : [%s],message : [%s]", url, e));
            }
            throw new HttpClientException(e);
        }
    }

    private void setTimeOutInner(int seconds) {
        seconds = seconds * 1000;
        this.connectTimeOut = seconds;
        this.socketTimeOut = seconds;
        this.connectRequestTimeOut = seconds;
    }


}
