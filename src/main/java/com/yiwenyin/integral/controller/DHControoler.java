package com.yiwenyin.integral.controller;

import com.alibaba.fastjson.JSON;
import com.yiwenyin.integral.model.bo.DHUser;
import com.yiwenyin.integral.util.UserNameUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ConcurrentModificationException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.base.hd.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author huangding
 * @description
 * @date 2018/12/27 11:00
 */
@Controller
@RequestMapping("dh")
public class DHControoler {


    @Autowired
    private StringRedisTemplate redisTemplate;

    @RequestMapping("index")
    public String index(ModelMap modelMap) {
        return "ygb/dhqc/index";
    }

    @RequestMapping("getUser")
    @ResponseBody
    public JsonResult getUser() {
        Cursor<Entry<Object, Object>> curosr = redisTemplate.opsForHash()
            .scan("dh_user", ScanOptions.NONE);
        Map<Object, Object> result = new HashMap<>();
        while (curosr.hasNext()) {
            Entry<Object, Object> next = curosr.next();
            result.put(next.getKey(), next.getValue());
        }
        return JsonResult.DATA(result);
    }

    @RequestMapping("saveUser")
    @ResponseBody
    public JsonResult save() {
        for (int i = 0; i < 1000; i++) {
            DHUser dhUser = new DHUser();
            dhUser.setName(UserNameUtil.randomName(true, 3));
            dhUser.setMobile(UserNameUtil.getTel());
            dhUser.setReward(0);
            dhUser.setOpenId(String.valueOf(i + dhUser.getMobile()));
            redisTemplate.opsForHash()
                .put("dh_user", dhUser.getOpenId(), JSON.toJSONString(dhUser));
        }
        return JsonResult.SUCCESS();
    }

    @RequestMapping("saveLuck")
    @ResponseBody
    public JsonResult saveLuck(String openId, Integer reward) {
        Object dh_user = redisTemplate.opsForHash().get("dh_user", openId);
        DHUser dhUser = JSON.parseObject(dh_user.toString(), DHUser.class);
        dhUser.setReward(reward);
        redisTemplate.opsForHash().put("dh_user", openId, JSON.toJSONString(dhUser));
        return JsonResult.SUCCESS();
    }
}
