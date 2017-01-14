package org.throwable.amqp.utils.http.entity;

import org.apache.http.message.BasicNameValuePair;

import java.util.List;
import java.util.Map;

/**
 * @author zhangjinci
 * @version 2017/1/6 12:04
 * @function
 */
public interface Parameters {


    List<BasicNameValuePair> getParameters();

    void setParameters(Map<String, String> parameterMap);

    void setParameters(List<BasicNameValuePair> parameters);

    void addParameter(String key, String value);

    void addParameters(Map<String, String> parameterMap);

}
