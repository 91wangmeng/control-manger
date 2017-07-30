package com.drore.cloud.control.manger.common.http.utils;

import com.alibaba.fastjson.JSON;
import com.drore.cloud.control.manger.common.log.entity.HttpRequestLogEntity;
import com.drore.cloud.control.manger.common.log.utils.LinkedBlockingQueueUtils;
import com.drore.cloud.control.manger.common.log.utils.TransmittableThreadLocalUtils;
import org.apache.http.*;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.SocketException;
import java.net.URISyntaxException;
import java.nio.charset.UnsupportedCharsetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 卓锐科技有限公司
 * Created by wmm on 2017/7/20.
 * email：6492178@gmail.com
 * description:
 *
 * @author wmm
 */
public class HttpRequestUtils {
    private static Logger LOGGER = LoggerFactory.getLogger(HttpRequestUtils.class);
    private final static String UTF8 = "UTF-8";
    /**
     * The constant REQUEST_TIMEOUT.
     */
    public static int REQUEST_TIMEOUT = 30000;
    /**
     * The constant CONNECT_TIMEOUT.
     */
    public static int CONNECT_TIMEOUT = 30000;
    /**
     * The constant SOCKET_TIMEOUT.
     */
    public static int SOCKET_TIMEOUT = 30000;
    /**
     * The constant MAX_CONNECTION.
     */
    public static int MAX_CONNECTION = 100;
    /**
     * The constant MAX_HOST_CONNECTION.
     */
    public static int MAX_HOST_CONNECTION = 30;
    /**
     * The constant READER_SIZE.
     */
    public static int READER_SIZE = 8192;
    /**
     * The constant httpClient.
     */
    public static CloseableHttpClient httpClient = null;

    static {
        // 初始化线程池
        RequestConfig defaultRequestConfig = RequestConfig.custom().setConnectTimeout(REQUEST_TIMEOUT).setConnectionRequestTimeout(CONNECT_TIMEOUT).setSocketTimeout(SOCKET_TIMEOUT)
                .setExpectContinueEnabled(true).build();

        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
        connectionManager.setMaxTotal(MAX_CONNECTION); // 连接池最大并发连接数
        connectionManager.setDefaultMaxPerRoute(MAX_HOST_CONNECTION); // 单路由最大并发数

        HttpRequestRetryHandler retryHandler = (exception, executionCount, context) -> {
            // 重试1次,从1开始
            if (executionCount > 1) {
                return false;
            }
            if (exception instanceof NoHttpResponseException) {
                LOGGER.error(
                        "[NoHttpResponseException has retry request:" + context.toString() + "][executionCount:" + executionCount + "]");
                return true;
            } else if (exception instanceof SocketException) {
                LOGGER.error("[SocketException has retry request:" + context.toString() + "][executionCount:" + executionCount + "]");
                return true;
            }
            return false;
        };
        httpClient = HttpClients.custom().setConnectionManager(connectionManager).setDefaultRequestConfig(defaultRequestConfig).setRetryHandler(retryHandler)
                .build();
    }


    /**
     * Post form string.
     *
     * @param describe   the describe
     * @param url        the url
     * @param parameters the parameters
     * @return the string
     */
    public static String postForm(String describe, String url, Map<String, Object> parameters) {
        return postFormWithTimeOut(describe, url, parameters, null, null, null);
    }

    /**
     * Post form with time out string.
     *
     * @param describe                 the describe
     * @param url                      the url
     * @param parameters               the parameters
     * @param connectionRequestTimeout the connection request timeout
     * @param connectTimeout           the connect timeout
     * @param socketTimeout            the socket timeout
     * @return the string
     */
    public static String postFormWithTimeOut(String describe, String url, Map<String, Object> parameters, Integer connectionRequestTimeout,
                                             Integer connectTimeout, Integer socketTimeout) {
        List<NameValuePair> nvps = covertMap2NVPS(parameters);
        try {
            LOGGER.debug("post-req:url:{},param:{}", url, JSON.toJSONString(parameters));
            HttpPost post = new HttpPost(url);

            if (connectionRequestTimeout != null && connectTimeout != null && socketTimeout != null) {
                RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(connectTimeout)
                        .setConnectionRequestTimeout(connectionRequestTimeout).setSocketTimeout(socketTimeout).build();
                post.setConfig(requestConfig);
            }
            post.setHeader("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE);
            post.setEntity(new UrlEncodedFormEntity(nvps, UTF8));
            String result = invoke(describe, post, parameters);

            return result;
        } catch (UnsupportedEncodingException e) {
            LOGGER.error("[HttpRequestUtils][post][Unsupported Encoding Exception]", e);
        }
        return null;
    }

    /**
     * Post json string.
     *
     * @param describe the describe
     * @param url      the url
     * @param params   the params
     * @return the string
     */
    public static String postJson(String describe, String url, Map<String, Object> params) {
        return postJsonWithTimeOut(describe, url, params, null, null, null);
    }

    /**
     * Post json with time out string.
     *
     * @param describe                 the describe
     * @param url                      the url
     * @param params                   the params
     * @param connectionRequestTimeout the connection request timeout
     * @param connectTimeout           the connect timeout
     * @param socketTimeout            the socket timeout
     * @return the string
     */
    public static String postJsonWithTimeOut(String describe, String url, Map<String, Object> params, Integer connectionRequestTimeout,
                                             Integer connectTimeout, Integer socketTimeout) {
        String result = null;
        try {
            LOGGER.debug("post-req:url:{},param:{}", url, JSON.toJSONString(params));
            HttpPost post = new HttpPost(url);
            if (connectionRequestTimeout != null && connectTimeout != null && socketTimeout != null) {
                RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(connectTimeout)
                        .setConnectionRequestTimeout(connectionRequestTimeout).setSocketTimeout(socketTimeout).build();
                post.setConfig(requestConfig);
            }
            post.setHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE);
            post.setEntity(new StringEntity(JSON.toJSONString(params), UTF8));
            result = invoke(describe, post, params);

        } catch (UnsupportedCharsetException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Get string.
     *
     * @param describe the describe
     * @param url      the url
     * @return the string
     */
    public static String get(String describe, String url) {
        return getWithTimeOut(describe, url, null, null, null, null);
    }

    /**
     * Gets with param.
     *
     * @param describe the describe
     * @param url      the url
     * @param params   the params
     * @return the with param
     */
    public static String getWithParam(String describe, String url, Map<String, Object> params) {
        return getWithTimeOut(describe, url, params, null, null, null);
    }

    /**
     * Gets with time out.
     *
     * @param describe                 the describe
     * @param url                      the url
     * @param params                   the params
     * @param connectionRequestTimeout the connection request timeout
     * @param connectTimeout           the connect timeout
     * @param socketTimeout            the socket timeout
     * @return the with time out
     */
    public static String getWithTimeOut(String describe, String url, Map<String, Object> params, Integer connectionRequestTimeout,
                                        Integer connectTimeout, Integer socketTimeout) {
        return getWithHeaderAndTimeOut(describe, url, params, null, connectTimeout, connectTimeout, socketTimeout);
    }

    /**
     * Gets with header and time out.
     *
     * @param describe                 the describe
     * @param url                      the url
     * @param params                   the params
     * @param headers                  the headers
     * @param connectionRequestTimeout the connection request timeout
     * @param connectTimeout           the connect timeout
     * @param socketTimeout            the socket timeout
     * @return the with header and time out
     */
    public static String getWithHeaderAndTimeOut(String describe, String url, Map<String, Object> params, Map<String, String> headers, Integer connectionRequestTimeout,
                                                 Integer connectTimeout, Integer socketTimeout) {
        String result = null;
        try {
            LOGGER.debug("get-req:url:{},param:{}", url, JSON.toJSONString(params));
            URIBuilder ub = new URIBuilder(url);
            if (params != null && !params.isEmpty()) {
                ArrayList<NameValuePair> pairs = covertMap2NVPS(params);
                ub.setParameters(pairs);
            }
            HttpGet get = new HttpGet(ub.build());
            if (headers != null) {
                headers.forEach((key, value) -> get.setHeader(key, value));
            }
            if (connectionRequestTimeout != null && connectTimeout != null && socketTimeout != null) {
                RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(connectTimeout)
                        .setConnectionRequestTimeout(connectionRequestTimeout).setSocketTimeout(socketTimeout).build();
                get.setConfig(requestConfig);
            }
            result = invoke(describe, get, params);

        } catch (UnsupportedCharsetException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return result;
    }

    private static String invoke(String describe, HttpRequestBase request, Map<String, Object> params) {
        HttpRequestLogEntity httpRequestLogEntity = new HttpRequestLogEntity();
        CloseableHttpResponse response = null;
        String responseStr = "";
        long startTs = System.currentTimeMillis();
        try {
            response = httpClient.execute(request);
            httpRequestLogEntity.setSuccess(true);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                HttpEntity entity = response.getEntity();
                responseStr = EntityUtils.toString(entity, UTF8);
            }

        } catch (IOException e) {
            httpRequestLogEntity.setSuccess(false);
            httpRequestLogEntity.setFailCause(e.getMessage());
            LOGGER.error(
                    "[HttpRequestUtils][invoke][method:" + request.getMethod() + " URI:" + request.getURI() + "] is request exception", e);
        } finally {

            if (response != null) {
                try {
                    response.close();

                } catch (IOException e) {
                    LOGGER.error(
                            "[HttpRequestUtils][invoke][method:" + request.getMethod() + " URI:" + request.getURI() + "] is closed exception",
                            e);
                }
            }
            try {
                long endTs = System.currentTimeMillis();
                long consumeTime = endTs - startTs;
                if (consumeTime > 5000) {
                    LOGGER.warn("url:{}\nresponse:{}\nconsume:{}", request.getURI(), response, consumeTime);
                    LOGGER.debug("所有存活线程=" + Thread.getAllStackTraces().size());
                }
                Header firstHeader = request.getFirstHeader("Content-Type");
                String contentType = firstHeader == null ? "" : firstHeader.getValue();
                if (!MediaType.MULTIPART_FORM_DATA_VALUE.equals(contentType)) {
                    httpRequestLogEntity.setParentId(TransmittableThreadLocalUtils.get());
                    httpRequestLogEntity.setConsumeTime(consumeTime);
                    httpRequestLogEntity.setParam(params);
                    httpRequestLogEntity.setResponse(responseStr);
                    httpRequestLogEntity.setUrl(request.getURI().toURL().toString());
                    httpRequestLogEntity.setDescribe(describe);
                    LinkedBlockingQueueUtils.offerHttpRequest(httpRequestLogEntity);
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
        return responseStr;
    }

    private static ArrayList<NameValuePair> covertMap2NVPS(Map<String, Object> params) {
        ArrayList<NameValuePair> pairs = new ArrayList<>();
        if (params != null && !params.isEmpty()) {
            params.forEach((s, o) -> pairs.add(new BasicNameValuePair(s, JSON.toJSONString(o))));
        }
        return pairs;
    }


    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        HashMap<String, Object> stringObjectHashMap = new HashMap<>();
        stringObjectHashMap.put("a", "a");
        stringObjectHashMap.put("b", "b");
        String test = HttpRequestUtils.get("打开百度", "http://www.baidu.com");
        System.out.println(test);
    }

}
