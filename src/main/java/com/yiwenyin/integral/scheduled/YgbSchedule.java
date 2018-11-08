package com.yiwenyin.integral.scheduled;

import com.yiwenyin.integral.enums.RedisEnum;
import com.yiwenyin.integral.redis.RedisUtil;
import com.yiwenyin.integral.service.YgbService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author huangding
 * @description 银股宝定时器
 * @date 2018/11/8 10:02
 */
@Component
@Slf4j
public class YgbSchedule {

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private YgbService ygbService;

    /**
     * 10秒执行一次
     */
    @Scheduled(fixedDelay = 10000)
    public void ygbMarketValueTask() throws Exception {
        log.info("银股宝市值定时刷新开始");
        String phone = "17858449952";
        String password = "hd520156";
        double ygbMarketValue = ygbService.getYgbMarketValue(phone, password);
        redisUtil.set(RedisEnum.YGB_MARKET.getVal(), String.valueOf(ygbMarketValue));
        log.info("银股宝市值定时刷新结束：{}", ygbMarketValue);
    }

}
