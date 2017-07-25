package com.drore.cloud.control.manger.web.Test;

import com.drore.cloud.control.manger.common.http.utils.HttpRequestUtils;
import com.drore.cloud.control.manger.common.log.annotations.ServerInvokeLog;
import com.drore.cloud.control.manger.common.log.constant.InvokerType;
import com.drore.cloud.control.manger.common.log.constant.LogType;
import com.drore.cloud.control.manger.log.domain.HttpRequestLogMongoEntity;
import com.drore.cloud.control.manger.log.domain.ServerInvokeLogMongoEntity;
import com.drore.cloud.control.manger.log.service.LogMangerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
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

    @Resource
    private LogMangerService logMangerService;

    /**
     * Test string.
     *
     * @return the string
     * @throws ExecutionException   the execution exception
     * @throws InterruptedException the interrupted exception
     */
    @RequestMapping("/test")
    @ServerInvokeLog(serverDescription = "管控后台管理系统测试接口", logType = LogType.API_LOG_TYPE, invoker = InvokerType.GIS_INVOKER_TYPE)
    public String test() throws ExecutionException, InterruptedException {
        HashMap<String, Object> stringObjectHashMap = new HashMap<>();
        stringObjectHashMap.put("a", "b");
        stringObjectHashMap.put("c", "d");
        stringObjectHashMap.put("e", "f");
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> HttpRequestUtils.postJson("测试子线程", "http://www.baidu.com", stringObjectHashMap));
        return "请求成功";
    }

    @RequestMapping("/getServerLog")
    @ServerInvokeLog(serverDescription = "获取服务日志", logType = LogType.API_LOG_TYPE, invoker = InvokerType.GIS_INVOKER_TYPE)
    public Page<ServerInvokeLogMongoEntity> getListA(@RequestParam String id, @PageableDefault() Pageable pageable) throws ExecutionException, InterruptedException {
        //Pageable pageable = new PageRequest(pageQO.getPage(), pageQO.getSize());
        return logMangerService.getList(pageable);
    }

    @RequestMapping("/getHttpRequest")
    @ServerInvokeLog(serverDescription = "获取网络请求", logType = LogType.API_LOG_TYPE, invoker = InvokerType.GIS_INVOKER_TYPE)
    public Page<HttpRequestLogMongoEntity> getList(@RequestParam String id, @RequestBody @PageableDefault() Pageable pageable) throws ExecutionException, InterruptedException {
        return logMangerService.getList(id, pageable);
    }
}
