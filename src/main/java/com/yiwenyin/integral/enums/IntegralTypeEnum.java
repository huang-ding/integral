package com.yiwenyin.integral.enums;

/**
 * @author huangding
 * @description 力谱云积分类型
 * @date 2018/11/3 17:07
 */
public enum IntegralTypeEnum {

    /**
     * 00 注册增加积分 增加 01 每日签到 增加 02 积分兑换金额 减少 03 奖励 增加 04 消费 减少 05 消费金额兑换积分 增加 06 打赏 减少 07 被打赏 增加 08
     * 打分 增加 09 三级分销奖励  增加 10 推荐奖励 增加 11 被推荐奖励 增加 12 充值奖励    增加 13 购买文章 减少 14 订单取消返还积分 增加 15 退货返还积分
     * 增加 16 其他 增加 17 其他 减少 18 退货扣除积分 减少 19 换货返还积分 增加 20 换货扣除积分 减少 21 维修换还积分 增加 22 维修扣除积分 减少 23
     * 充值奖励积分(与12区别在同时充值余额增加的字段不同)  增加 24 用户取消订单扣除  减少 25 用户取消订单增加  增加、 26 ios内购积分      增加 27 发红包 减少
     * 28 收红包           增加 29 退还红包         增加
     */

    TYPE_00("00", "注册增加积分", 1),
    TYPE_01("01", "每日签到", 1),
    TYPE_02("02", "积分兑换金额", 0),
    TYPE_03("03", "奖励", 1),
    TYPE_04("04", "消费", 0),
    TYPE_05("05", "消费金额兑换积分", 1),
    TYPE_06("06", "打赏", 0),
    TYPE_07("07", "被打赏", 1),
    TYPE_08("08", "打分", 1),
    TYPE_09("09", "三级分销奖励", 1),
    TYPE_10("10", "推荐奖励", 1),
    TYPE_11("11", "被推荐奖励", 1),
    TYPE_12("12", "充值奖励", 1),
    TYPE_13("13", "购买文章", 0),
    TYPE_14("14", "订单取消返还积分", 1),
    TYPE_15("15", "退货返还积分", 1),
    TYPE_16("16", "其他", 1),
    TYPE_17("17", "其他", 0),
    TYPE_18("18", "退货扣除积分", 0),
    TYPE_19("19", "换货返还积分", 1),
    TYPE_20("20", "换货扣除积分", 0),
    TYPE_21("21", "维修换还积分", 1),
    TYPE_22("22", "维修扣除积分", 0),
    TYPE_23("23", "充值奖励积分", 1),
    TYPE_24("24", "用户取消订单扣除", 0),
    TYPE_25("25", "用户取消订单增加", 1),
    TYPE_26("26", "ios内购积分", 1),
    TYPE_27("27", "发红包", 0),
    TYPE_28("28", "收红包", 1),
    TYPE_29("29", "退还红包", 1),

    ;

    IntegralTypeEnum(String name, String value, Integer type) {
        this.name = name;
        this.value = value;
        this.type = type;
    }

    public static IntegralTypeEnum getIntegralTypeEnum(String name) {
        //values()方法返回enum实例的数组
        for (IntegralTypeEnum integralTypeEnum : values()) {
            if (name.equals(integralTypeEnum.getName())) {
                return integralTypeEnum;
            }
        }
        return null;
    }

    private String name;
    private String value;
    /**
     * 1= 增加 0 = 减少
     */
    private Integer type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }


}
