package org.throwable.amqp.utils.http;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.throwable.amqp.utils.http.constant.HttpMethodEnum;
import org.throwable.amqp.utils.http.entity.Headers;
import org.throwable.amqp.utils.http.entity.Parameters;
import org.throwable.amqp.utils.http.exception.HttpClientException;


import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * @author zhangjinci
 * @version 2017/1/9 17:03
 * @function http客户端构件工厂
 */
public abstract class HttpClientComponentFactory {

    private static final String HTTP_PARAMS_PREFIX = "?";
    private static final String HTTP_PARAMS_PREFIX_SPLIT = "\\?";
    private static final String HTTP_PARAMS_SEPARATOR = "&";
    private static final String HTTP_PARAMS_EQUALS = "=";
    private static final String HTTP_PROTOCOL_PREFIX = "http";
    private static final String HTTPS_PROTOCOL_PREFIX = "https";
    public static final String DEFAULT_CHARSET = "UTF-8";


    public static CloseableHttpClient buildHttpClient(final HttpHost proxy, final HttpRequestRetryHandler retryHandler) {
        CloseableHttpClient client;
        HttpClientBuilder builder = HttpClients.custom();
        if (null != proxy) {
            builder.setProxy(proxy);
        }
        if (null != retryHandler) {
            builder.setRetryHandler(retryHandler);
        }
        client = builder.build();
        return client;
    }

    public static CloseableHttpClient buildDefailtHttpClient() {
        return buildHttpClient(null, null);
    }

    public static HttpRequestBase buildHttpRequestByMethod(HttpMethodEnum methodEnum, String url,
                                                           Headers headers,
                                                           Parameters params,
                                                           List<File> files) {
        assertUrlFormat(url);
        HttpRequestBase requestBase;
        switch (methodEnum) {
            case GET:
                requestBase = buildHttpGet(url, params);
                break;
            case POST:
                requestBase = buildHttpPost(url, params);
                break;
            case MULTI_POST:
                requestBase = buildHttpMultiPost(url, params, files);
                break;
            default: {
                requestBase = buildHttpGet(url, params);
            }
        }
        setHttpHeaders(requestBase,headers);
        return requestBase;
    }

    private static void setHttpHeaders(HttpRequestBase requestBase,Headers headers){
        if (null != headers && null != headers.getHeaders() && headers.getHeaders().size() > 0){
            requestBase.setHeaders(headers.getHeaders().toArray(new BasicHeader[headers.getHeaders().size()]));
        }
    }

    private static boolean checkHttpParameters(Parameters parameters){
        return null != parameters && null != parameters.getParameters() && parameters.getParameters().size() > 0;
    }

    private static void assertUrlFormat(String url) {
        if (null == url || url.length() == 0) {
            throw new HttpClientException("request url could not be null");
        } else if (!url.startsWith(HTTP_PROTOCOL_PREFIX) && !url.startsWith(HTTPS_PROTOCOL_PREFIX)) {
            throw new HttpClientException("request url protocol must be 'HTTP' or 'HTTPS'");
        }
    }

    private static String covertParamsToUrl(String url, Parameters params) {
        StringBuilder builder = new StringBuilder(url);
        if (checkHttpParameters(params)) {
            if (!url.endsWith(HTTP_PARAMS_PREFIX)) {
                builder.append(HTTP_PARAMS_PREFIX);
            }
            List<BasicNameValuePair> pairs = params.getParameters();
            for (BasicNameValuePair pair : pairs){
                builder.append(pair.getName()).append(HTTP_PARAMS_EQUALS).append(pair.getValue());
            }
        }
        return builder.toString();
    }


    private static HttpGet buildHttpGet(String url, Parameters params) {
        url = covertParamsToUrl(url, params);
        return new HttpGet(url);
    }



    private static HttpPost buildHttpPost(String url,Parameters params) {
        HttpPost post = new HttpPost(url);
        if (checkHttpParameters(params)) {
            try {
                post.setEntity(new UrlEncodedFormEntity(params.getParameters(), DEFAULT_CHARSET));
            } catch (UnsupportedEncodingException e) {
                throw new HttpClientException(e);
            }
        }
        return post;
    }

    private static HttpEntity buildHttpMultiPostEntity(Parameters params, List<File> files) {
        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        if (checkHttpParameters(params)) {
            List<BasicNameValuePair> pairs = params.getParameters();
            for (BasicNameValuePair pair : pairs) {
                builder.addPart(pair.getName(), new StringBody(pair.getValue(),
                        ContentType.TEXT_PLAIN.withCharset(DEFAULT_CHARSET)));
            }
        }
        if (null != files && !files.isEmpty()) {
            for (File file : files) {
                builder.addPart(file.getName(), new FileBody(file));
            }
        }
        return builder.build();
    }

    private static HttpRequestBase buildHttpMultiPost(String url, Parameters params, List<File> files) {
        HttpPost post = new HttpPost(url);
        post.setEntity(buildHttpMultiPostEntity(params, files));
        return post;
    }
}
