package com.drore.cloud.control.manger.web.Test;

import com.drore.cloud.control.manger.common.http.utils.HttpRequestUtils;
import com.drore.cloud.control.manger.common.log.annotations.ServerInvokeLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * 卓锐科技有限公司
 * Created by wmm on 2017/7/21.
 * email：6492178@gmail.com
 * description:
 *
 * @author wmm
 */
@RestController
public class TestController {
    private static Logger LOGGER = LoggerFactory.getLogger(TestController.class);

    /**
     * Test string.
     *
     * @return the string
     * @throws ExecutionException   the execution exception
     * @throws InterruptedException the interrupted exception
     */
    @RequestMapping("/test")
    @ServerInvokeLog(serverDescription = "测试接口", logType = LogType.API_LOG_TYPE)
    public String test() throws ExecutionException, InterruptedException {
        HashMap<String, Object> stringObjectHashMap = new HashMap<>();
        stringObjectHashMap.put("a", "b");
        stringObjectHashMap.put("c", "d");
        stringObjectHashMap.put("e", "f");
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> HttpRequestUtils.postJson("测试子线程", "http://www.baidu.com", stringObjectHashMap));
        return completableFuture.get();
    }
}
