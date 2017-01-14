package org.throwable.amqp.utils.http.entity;

import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author zhangjinci
 * @version 2017/1/6 12:08
 * @function
 */
public class DefaultParameters implements Parameters {

    List<BasicNameValuePair> parameters;

    public DefaultParameters() {
        this.parameters = new ArrayList<BasicNameValuePair>();
    }

    public List<BasicNameValuePair> getParameters() {
        return parameters;
    }

    public void setParameters(Map<String, String> parameterMap) {
        if (null != parameterMap && !parameterMap.isEmpty()) {
            parameters.clear();
            for (Map.Entry<String, String> entry : parameterMap.entrySet()) {
                parameters.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }
        }
    }

    public void setParameters(List<BasicNameValuePair> parameters) {
        this.parameters = parameters;
    }

    public void addParameter(String key,String value){
        parameters.add(new BasicNameValuePair(key,value));
    }

    public void addParameters(Map<String, String> parameterMap){
        if (null != parameterMap && !parameterMap.isEmpty()) {
            for (Map.Entry<String, String> entry : parameterMap.entrySet()) {
                parameters.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }
        }
    }
}
