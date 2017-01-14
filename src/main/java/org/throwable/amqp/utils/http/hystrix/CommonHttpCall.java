package org.throwable.amqp.utils.http.hystrix;

import com.netflix.hystrix.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.throwable.amqp.utils.http.HttpClient;

import java.util.Map;

/**
 * @author zhangjinci
 * @version 2017/1/11 10:05
 * @function
 */
public class CommonHttpCall extends HystrixCommand<String> {

    private final String url;
    private final Map<String, String> params;

    private static final Logger log = LoggerFactory.getLogger(CommonHttpCall.class);

    public CommonHttpCall(String url, Map<String, String> params) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("hystrix.common.http"))
                .andCommandKey(HystrixCommandKey.Factory.asKey("hystrix.common.http"))
                .andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("hystrix.common.http"))
                .andCommandPropertiesDefaults(
                        HystrixCommandProperties.Setter()
                                .withCircuitBreakerEnabled(true)  //开启断路器
                                .withCircuitBreakerRequestVolumeThreshold(2) //断路器阈值,设置一个滑动窗口内触发熔断的最少请求量
                                .withCircuitBreakerSleepWindowInMilliseconds(60 * 1000) //设置触发熔断后,拒绝请求后多长时间开始尝试再次执行
                                .withFallbackEnabled(true)  //是否使用降级处理
                                .withExecutionIsolationThreadInterruptOnTimeout(true) //超时是否中断run方法的执行
                                .withExecutionTimeoutInMilliseconds(5000) //run方法执行的超时时间(秒)
                )

        );
        this.url = url;
        this.params = params;
    }

    @Override
    protected String run() throws Exception {
        if (log.isDebugEnabled()) {
            log.info("Execution of Command: url= {}", url);
        }
        return HttpClient.getInstance().doPost(url, params);
    }


    @Override
    protected String getFallback() {
        return String.format("Http request fallbackFor url : [%s] , params : [%s]", url, params.toString());
    }
}
