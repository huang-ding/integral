package com.yiwenyin.integral.controller;


import com.alibaba.fastjson.JSON;
import com.yiwenyin.integral.model.bo.DHUser;
import com.yiwenyin.integral.redis.RedisUtil;
import com.yiwenyin.integral.util.Coder;
import java.io.IOException;
import java.util.Map;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.base.hd.util.wechat.MsgType;
import org.base.hd.util.wechat.WeChatMsgUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author huangding
 * @description
 * @date 2018/12/7 17:18
 */
@RestController
@RequestMapping("weChat/notify")
@Slf4j
public class WeChatController {

    @Autowired
    protected HttpServletRequest request;
    @Autowired
    private RedisUtil redisUtil;

    /**
     * 检查是否存在空字符串
     */
    public static boolean hasBlank(String... strs) {
        if (strs == null || strs.length == 0) {
            return true;
        } else {
            for (String str : strs) {
                if (StringUtils.isBlank(str)) {
                    return true;
                }
            }
            return false;
        }
    }


    /**
     * 微信检查
     */
    @GetMapping("msg")
    public void getIsMsg(HttpServletResponse response)
        throws IOException {
        String echostr = request.getParameter("echostr");
        response.getWriter().println(echostr);

    }

    @PostMapping("msg")
    public void postMsg(HttpServletResponse response) throws Exception {

        Map<String, String> requestMap = WeChatMsgUtil.parseXml(request);
        String wxOriginalId = requestMap.get("ToUserName");
        String openId = requestMap.get("FromUserName");
        String msgType = requestMap.get("MsgType");
        Integer createTime = Integer.parseInt(requestMap.get("CreateTime"));
        try {
            switch (MsgType.getMagType(msgType)) {
                case TEXT:
                    String regEx = "^#[\u4e00-\u9fa5]{2,5}#\\d{11}$";
                    String content = requestMap.get("Content");
                    if (Pattern.matches(regEx, content)) {
                        String[] split = content.split("#");
                        DHUser dhUser = new DHUser();
                        dhUser.setMobile(split[2]);
                        dhUser.setName(split[1]);
                        dhUser.setReward(0);
                        dhUser.setOpenId(openId);
                        redisUtil.hset("dh_user", openId, JSON.toJSONString(dhUser));
                        String xml = "<xml>"
                            + "<ToUserName><![CDATA[%s]]></ToUserName>"
                            + "<FromUserName><![CDATA[%s]]></FromUserName>"
                            + "<CreateTime>%s</CreateTime>"
                            + "<MsgType><![CDATA[text]]></MsgType>"
                            + "<Content><![CDATA[%s]]></Content>"
                            + "</xml>";
                        String format = String
                            .format(xml, openId, wxOriginalId,
                                createTime, "参与成功！");
                        response.setContentType("application/xml; charset=utf-8");
                        response.getWriter().print(format);
                    }
                    return;
                default:
                    return;
            }
        } catch (NullPointerException e) {
            log.info("暂未解析类型{}", msgType);
        }

    }

    public static void main(String[] args) {
        String regEx = "^#[\u4e00-\u9fa5]{2,5}#\\d{11}$";
        String text = "#张三#17858449952";
        boolean matches = Pattern.matches(regEx, text);
        System.out.println(matches);
    }

}
