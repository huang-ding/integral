package com.yiwenyin.integral.comm;

import com.alibaba.fastjson.JSON;
import com.yiwenyin.integral.util.OkHttpUtil;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author huangding
 * @description 力谱云http请求封装
 * @date 2018/11/2 17:08
 */
@Component
public class LiPuYunHttp {

    @Resource
    private HttpAPI httpAPI;

    @Value("${lipuyun.masterKey}")
    private String masterKey;
    @Value("${lipuyun.restApiKey}")
    private String restApiKey;
    @Value("${lipuyun.appId}")
    private String appId;


    public String doGet(String url, Map<String, Object> map, Map<String, String> headers)
        throws Exception {
        headers.put("x-ml-appid", this.appId);
        headers.put("content-type", "application/json");
        headers.put("x-ml-masterkey", masterKey);
        return httpAPI.doGet(url, map, headers);
    }


    public String doGet(String url, Map<String, String> headers)
        throws Exception {
        return doGet(url, null, headers);
    }

    public String doPost(String url, Map<String, Object> map, Map<String, String> headers){
        headers.put("content-type", "application/json");
        headers.put("x-ml-appid", this.appId);
        String json = null;
        if (map != null && map.size() > 0) {
            json = JSON.toJSONString(map);
        }
        return OkHttpUtil.postJsonParams(url, json, headers);
    }

    public String doPost(String url, Map<String, Object> map) {
        return doPost(url, map, new HashMap<>(2));
    }


}
