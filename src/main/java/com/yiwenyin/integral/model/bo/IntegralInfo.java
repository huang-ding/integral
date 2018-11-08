package com.yiwenyin.integral.model.bo;

import com.yiwenyin.integral.enums.IntegralTypeEnum;
import com.yiwenyin.integral.util.DateUtil;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author huangding
 * @description
 * @date 2018/11/3 17:05
 */
@NoArgsConstructor
@Data
@Slf4j
public class IntegralInfo {


    /**
     * count : 1 results : [{"id":191,"memberId":54,"currentNum":0,"integral":100,"createdAt":1492744436592,"integralType":"00","operator":"系统","beforeIntegral":0,"afterIntegral":100,"type":"1"}]
     */

    private Integer count;
    private List<ResultsBean> results;

    @NoArgsConstructor
    @Data
    public static class ResultsBean {

        /**
         * id : 191 memberId : 54 currentNum : 0 integral : 100 createdAt : 1492744436592
         * integralType : 00 operator : 系统 beforeIntegral : 0 afterIntegral : 100 type : 1
         */

        private int id;
        private Integer memberId;
        private Integer currentNum;
        private Integer integral;
        private Long createdAt;
        private String integralType;
        private String operator;
        private Integer beforeIntegral;
        private Integer afterIntegral;
        /**
         * 1、系统 2、管理员 3、用户
         */
        private String type;
        private String typeRemark;
        private String createdAtFormat;
        private Integer symbolType;

        public String getTypeRemark() {
            IntegralTypeEnum integralTypeEnum = IntegralTypeEnum
                .getIntegralTypeEnum(this.integralType);
            if (null != integralTypeEnum) {
                return integralTypeEnum.getValue();
            }
            return "其他";
        }

        /**
         * 1= 增加 0 = 减少
         */
        public Integer getSymbolType() {
            IntegralTypeEnum integralTypeEnum = IntegralTypeEnum
                .getIntegralTypeEnum(this.integralType);
            if (null != integralTypeEnum) {
                return integralTypeEnum.getType();
            }
            return 0;
        }

        public String getCreatedAtFormat() {
            if (null == this.createdAt) {
                return "时间未知";
            }
            return DateUtil.getDateTimeOfTimestampString(this.createdAt);
        }

    }

}
