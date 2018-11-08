package com.yiwenyin.integral.service;

import com.alibaba.fastjson.JSON;
import com.yiwenyin.integral.comm.LiPuYunHttp;
import com.yiwenyin.integral.model.bo.UserData;
import com.yiwenyin.integral.util.OkHttpUtil;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 * @author huangding
 * @description
 * @date 2018/11/3 14:26
 */
@Service
public class YgbService {

    @Resource
    private LiPuYunHttp liPuYunHttp;


    /**
     * 获取指定用户数据
     */
    public UserData getUserDataByUserId(String userId, String maxleap_sessiontoken) {

        Map<String, String> headers = new HashMap<>();
        headers.put("x-ml-session-token", maxleap_sessiontoken);
        try {
            String json = liPuYunHttp
                .doGet("https://wonapi.maxleap.cn/1.0/mems/" + userId, headers);
            UserData userData = JSON.parseObject(json, UserData.class);

            //如果用户没有自定义页面
            if (StringUtils.isBlank(userData.getBackground())) {
                userData.setBackground(
                    "http://csqncdn.maxleap.cn/NThmODY4OGRlMTRkNTEwMDA2MTAxOTJk/qn-a6fcfacf-61be-4e83-8f1e-a98864fd1441.jpg");
            }
            return userData;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取银股宝当前市值
     */
    public double getYgbMarketValue(String phone, String password) throws Exception {

        UserData userData = login(phone, password);
        if (userData == null) {
            return 0;
        }
        //银股宝当前价值 =指定账户余额（分）/积分(*100)
        BigDecimal balance = new BigDecimal(userData.getBalance());
        BigDecimal integral = new BigDecimal(userData.getIntegral())
            .multiply(new BigDecimal(100));

        double ygbWorth = balance.divide(integral, 2, BigDecimal.ROUND_DOWN).doubleValue();
        return ygbWorth;
    }


    /**
     * 用户登录
     */
    public UserData login(String phone, String password) throws Exception {
        String url = "https://wonapi.maxleap.cn/1.0/mems/passwordLogin";
        Map<String, Object> map = new HashMap<>(2);
        map.put("phone", phone);
        map.put("password", DigestUtils.md5Hex(password));
        String httpResult = liPuYunHttp.doPost(url, map);
        if (StringUtils.isNotBlank(httpResult)) {
            return JSON.parseObject(httpResult, UserData.class);
        }
        return null;
    }

    public static void main(String[] args) throws IOException {
        String password = DigestUtils.md5Hex("hd520156");
//        OkHttpClient client = new OkHttpClient();
//
//        MediaType mediaType = MediaType.parse("application/json");
//        okhttp3.RequestBody body = RequestBody.create(mediaType, "{   \"phone\": \"17858449952\",   \"password\": \""+password+"\"}");
//        Request request = new Request.Builder()
//            .url("https://wonapi.maxleap.cn/1.0/mems/passwordLogin")
//            .post(body)
//            .addHeader("x-ml-appid", "5bc15cf20351cb0007a67fb1")
//            .addHeader("content-type", "application/json")
//            .build();
//        Response response = client.newCall(request).execute();
//        System.out.println(response.body().string());

        Map<String, String> headers = new HashMap<>(2);
        headers.put("content-type", "application/json");
        headers.put("x-ml-appid", "5bc15cf20351cb0007a67fb1");
        String json = null;
        Map<String, Object> map = new HashMap<>(2);
        map.put("phone", "17858449952");
        map.put("password", DigestUtils.md5Hex("hd520156"));
        if (map != null && map.size() > 0) {
            json = JSON.toJSONString(map);
        }
        String s = OkHttpUtil
            .postJsonParams("https://wonapi.maxleap.cn/1.0/mems/passwordLogin", json, headers);
        System.out.println(s);

    }

}
