package com.yiwenyin.integral.model.bo;

import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author huangding
 * @description
 * @date 2018/11/2 16:08
 */
@NoArgsConstructor
@Data
public class UserData {

    /**
     * id : 14 nationality : 中国 phonePrefix : +86 type : 0 phone : 17858449952 levelId : 0 integral
     * : 3 defaultAddressId : 14 nickName : user2345722 icon : https://csqncdn.maxleap.cn/NWJjMTVjZjIwMzUxY2IwMDA3YTY3ZmIx/qn-4b348255-b6f8-4ea6-993d-c469cac12318
     * background : https://csqncdn.maxleap.cn/NWJjMTVjZjIwMzUxY2IwMDA3YTY3ZmIx/qn-712dfea6-72e7-4ea9-ac28-b7ff447a9206
     * balance : 0.0 memberFreezeBalance : 0.0 freezeBalanceFlag : false chargeAmount : 0.0
     * consumeAmount : 0.0 createdAt : 1540538421930 updatedAt : 1541121762914 addressIds : [14]
     * userId : e70e82dfa8b94926a54baa2963084299 deleted : false totalSales : 0.0 disable : false
     * recommendedCode : selfRecommendedCode : 7b19c4 rowNum : 0 memberBankNum : 0 levelRemainTime :
     * -1 orderCountForBC : {"readyToReceive":0,"readyToGroup":0,"readyToComment":0,"readyToPay":0,"readyToDeliver":0}
     * orderCountForBBC : {"readyToReceive":0,"readyToGroup":0,"readyToComment":0,"readyToPay":0,"readyToDeliver":0}
     * sign : true
     */

    /**
     * 会员id(唯一)
     */
    private Integer id;
    /**
     * 国家
     */
    private String nationality;
    private String phonePrefix;
    /**
     * 用户类型;例如:0:app 1:QQ 2:微信 3:微博 4:匿名 5:facebook 6:twitter 7:google
     */
    private Integer type;
    private String phone;
    /**
     * 会员等级id
     */
    private Integer levelId;
    /**
     * 用户积分;例如：20
     */
    private Integer integral;
    /**
     * 默认地址id
     */
    private Integer defaultAddressId;
    /**
     * 昵称
     */
    private String nickName;
    /**
     * 头像地址；例如:https://www.baidu.com/img/bd_logo1.png
     */
    private String icon;
    /**
     * 背景图片;例如:https://www.baidu.com/img/bd_logo1.png
     */
    private String background;
    /**
     * 余额
     */
    private Double balance;
    /**
     * 创建时间
     */
    private Long createdAt;
    /**
     * 更新时间
     */
    private Long updatedAt;
    /**
     * 删除状态
     */
    private String deleted;
    /**
     * 地址id集合
     */
    private List<Integer> addressIds;
//    private Double memberFreezeBalance;
//
//    private Double freezeBalanceFlag;
//    private Double chargeAmount;
//    private Double consumeAmount;

//    private String userId;

//    private Double totalSales;
//    private String disable;
//    private String recommendedCode;
//    private String selfRecommendedCode;
//    private Integer rowNum;
//    private Integer memberBankNum;
//    private Integer levelRemainTime;
//    private OrderCountForBCBean orderCountForBC;
//    private OrderCountForBBCBean orderCountForBBC;
//    private String sign;


    @NoArgsConstructor
    @Data
    public static class OrderCountForBCBean {

        /**
         * readyToReceive : 0 readyToGroup : 0 readyToComment : 0 readyToPay : 0 readyToDeliver : 0
         */

        private Integer readyToReceive;
        private Integer readyToGroup;
        private Integer readyToComment;
        private Integer readyToPay;
        private Integer readyToDeliver;
    }

    @NoArgsConstructor
    @Data
    public static class OrderCountForBBCBean {

        /**
         * readyToReceive : 0 readyToGroup : 0 readyToComment : 0 readyToPay : 0 readyToDeliver : 0
         */

        private Integer readyToReceive;
        private Integer readyToGroup;
        private Integer readyToComment;
        private Integer readyToPay;
        private Integer readyToDeliver;
    }
}
