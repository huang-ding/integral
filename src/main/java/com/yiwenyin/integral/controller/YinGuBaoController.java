package com.yiwenyin.integral.controller;

import com.alibaba.fastjson.JSON;
import com.yiwenyin.integral.comm.LiPuYunHttp;
import com.yiwenyin.integral.enums.RedisEnum;
import com.yiwenyin.integral.model.bo.IntegralInfo;
import com.yiwenyin.integral.model.bo.UserData;
import com.yiwenyin.integral.redis.RedisUtil;
import com.yiwenyin.integral.service.YgbService;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author huangding
 * @description
 * @date 2018/11/2 14:31
 */
@Controller
@RequestMapping("yingubao")
@Slf4j
public class YinGuBaoController {

    @Resource
    private LiPuYunHttp liPuYunHttp;

    @Autowired
    private YgbService ygbService;

    @Autowired
    private RedisUtil redisUtil;




    @RequestMapping("index")
    public String index(String maxleap_appid, String maxleap_userid, String maxleap_sessiontoken,
        ModelMap modelMap) throws Exception {
        log.info("maxleap_appid:" + maxleap_appid + " maxleap_userid:" + maxleap_userid
            + " maxleap_sessiontoken:" + maxleap_sessiontoken);
        if (maxleap_appid == null || maxleap_userid == null || maxleap_sessiontoken == null) {
            //跳转到登录页面
            return "redirect:http://5bc15cf20351cb0007a67fb1.h5.maxwonapps.com/maxh5/member/login";
        }
        modelMap.addAttribute("userId", Integer.parseInt(maxleap_userid));
        modelMap.addAttribute("sessionToken", maxleap_sessiontoken);

        UserData userData = ygbService
            .getUserDataByUserId(maxleap_userid, maxleap_sessiontoken);

        //获取银股宝市值
        double ygbMarketValue = Double.parseDouble(redisUtil.get(RedisEnum.YGB_MARKET.getVal()));
        double ygbMarket = new BigDecimal(ygbMarketValue)
            .multiply(new BigDecimal(userData.getIntegral()))
            .setScale(2, BigDecimal.ROUND_DOWN).doubleValue();

        modelMap.addAttribute("ygbMarket", ygbMarket);

        modelMap.addAttribute("ygbWorth", ygbMarketValue);

        modelMap.addAttribute("userData", userData);

        return "ygb/index";
    }

    @RequestMapping("detail")
    public String detail(String userId,
        ModelMap modelMap) {

        modelMap.addAttribute("userId", userId);
        Map<String, String> headers = new HashMap<>();
        headers.put("cache-control", "no-cache");

        try {
            String json = liPuYunHttp
                .doGet("https://wonapi.maxleap.cn/1.0/mems/" + userId + "/integral",
                    headers);
            IntegralInfo integralInfo = JSON.parseObject(json, IntegralInfo.class);
            Collections.reverse(integralInfo.getResults());
            modelMap.addAttribute("integralInfo", integralInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "ygb/detail";
    }


}
