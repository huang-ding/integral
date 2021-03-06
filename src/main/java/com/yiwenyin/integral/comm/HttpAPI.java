package com.yiwenyin.integral.comm;

import com.yiwenyin.integral.util.HttpResult;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author huangding
 * @description
 * @date 2018/11/2 15:13
 */
@Component
@Slf4j
public class HttpAPI {

    @Autowired
    private CloseableHttpClient httpClient;

    @Autowired
    private RequestConfig config;

    /**
     * 不带参数的get请求，如果状态码为200，则返回body，如果不为200，则返回null
     */
    public String doGet(String url) throws Exception {
        return this.doGet(url, null);
    }

    /**
     * 不带参数的get请求，如果状态码为200，则返回body，如果不为200，则返回null
     */
    public String doGet(String url, Map<String, String> headers) throws Exception {
        // 声明 http get 请求
        HttpGet httpGet = new HttpGet(url);

        // 装载配置信息
        httpGet.setConfig(config);

        //头部信息封装
        if (headers != null && headers.size() > 0) {
            headers.forEach((key, val) -> {
                httpGet.setHeader(key, val);
            });
        }

        // 发起请求
        CloseableHttpResponse response = this.httpClient.execute(httpGet);

        // 判断状态码是否为200
        if (response.getStatusLine().getStatusCode() == 200) {
            // 返回响应体的内容
            return EntityUtils.toString(response.getEntity(), "UTF-8");
        }
        log.error("请求出错：url={url}", url);
        log.error("请求出错：headers={headers}", headers);
        log.error("请求出错：response={response}", EntityUtils.toString(
            response.getEntity(), "UTF-8"));
        return null;
    }

    /**
     * 带参数的get请求，如果状态码为200，则返回body，如果不为200，则返回null
     */
    public String doGet(String url, Map<String, Object> map, Map<String, String> headers)
        throws Exception {
        URIBuilder uriBuilder = new URIBuilder(url);
        if (map != null) {
            // 遍历map,拼接请求参数
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                uriBuilder.setParameter(entry.getKey(), entry.getValue().toString());
            }
        }

        // 调用不带参数的get请求
        return this.doGet(uriBuilder.build().toString(), headers);

    }

    /**
     * 带参数的post请求
     */
    public HttpResult doPost(String url, Map<String, Object> map, Map<String, String> headers)
        throws Exception {
        // 声明httpPost请求
        HttpPost httpPost = new HttpPost(url);
        // 加入配置信息
        httpPost.setConfig(config);

        //头部信息封装
        if (headers != null && headers.size() > 0) {
            headers.forEach((key, val) -> {
                httpPost.setHeader(key, val);
            });
        }

        // 判断map是否为空，不为空则进行遍历，封装from表单对象
        if (map != null) {
            List<NameValuePair> list = new ArrayList<NameValuePair>();
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                list.add(new BasicNameValuePair(entry.getKey(), entry.getValue().toString()));
            }
            // 构造from表单对象
            UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(list, "UTF-8");

            // 把表单放到post里
            httpPost.setEntity(urlEncodedFormEntity);
        }

        // 发起请求
        CloseableHttpResponse response = this.httpClient.execute(httpPost);
        return new HttpResult(response.getStatusLine().getStatusCode(), EntityUtils.toString(
            response.getEntity(), "UTF-8"));
    }


    /**
     * 不带参数post请求
     */
    public HttpResult doPost(String url) throws Exception {
        return this.doPost(url, null, null);
    }

    /**
     * 不带头部参数post请求
     */
    public HttpResult doPost(String url, Map<String, Object> map) throws Exception {
        return this.doPost(url, map, null);
    }


}



