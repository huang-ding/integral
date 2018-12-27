package com.yiwenyin.integral.model.bo;

import lombok.Data;

/**
 * @author huangding
 * @description
 * @date 2018/12/27 11:20
 */
@Data
public class DHUser implements Comparable<DHUser>{

    private String name;
    private String mobile;
    private Integer reward;
    private String openId;

    @Override
    public int compareTo(DHUser o) {
        return this.reward - o.getReward();
    }
}
