package com.yiwenyin.integral.enums;

/**
 * @author huangding
 * @description
 * @date 2018/11/8 10:07
 */

public enum RedisEnum {
    /**
     * 银股宝当前市值
     */
    YGB_MARKET("ygbMarket");

    RedisEnum(String val) {
        this.val = val;
    }

    private String val;

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }
}
