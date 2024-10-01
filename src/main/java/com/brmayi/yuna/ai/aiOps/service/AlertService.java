package com.brmayi.yuna.ai.aiOps.service;

import cn.hutool.json.JSONObject;
import com.brmayi.yuna.ai.aiOps.infrastructure.client.DingtalkCallClient;
import com.brmayi.yuna.ai.aiOps.infrastructure.entity.DingTalkEntity;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;

@Service
@Slf4j
public class AlertService {
    @Value("${dingtalk.accessToken}")
    private String accessToken;
    @Value("${dingtalk.secret}")
    private String secret;
    @Resource
    private DingtalkCallClient dingtalkCallClient;
    public void sendWarning(String msg, String phones) {
        try {
            List<String> mobiles = Lists.newArrayList();
            if(StringUtils.isNotEmpty(phones)) {
                mobiles = Arrays.asList(phones.split(","));
            }

            // 单位毫秒
            long time = System.currentTimeMillis();
            String sign = genRobotSign(secret, time);
            DingTalkEntity dingTalkEntity = new DingTalkEntity();
            JSONObject notify = new JSONObject();
            notify.putOpt(IS_AT_ALL, false);
            notify.putOpt(AT_MOBILES, mobiles);
            dingTalkEntity.setAt(notify);
            JSONObject content = new JSONObject();
            content.putOpt(CONTENT, msg.trim());
            dingTalkEntity.setText(content);
            String result = dingtalkCallClient.send(accessToken, time, sign, dingTalkEntity);
            log.info("sendWarning text:{}, result:{}", msg, result);
        } catch (Exception e) {
            log.info("sendWarning error:{}", msg, e);
        }
    }

    private static final String CONTENT = "content";
    private static final String AT_MOBILES = "atMobiles";
    private static final String IS_AT_ALL = "isAtAll";
    private static final String HMAC_SHA256 = "HmacSHA256";

    private static String genRobotSign(String secret, long timestamp) {
        try {
            String stringToSign = timestamp + "\n" + secret;
            Mac mac = Mac.getInstance(HMAC_SHA256);
            mac.init(new SecretKeySpec(secret.getBytes("UTF-8"), HMAC_SHA256));
            byte[] signData = mac.doFinal(stringToSign.getBytes(StandardCharsets.UTF_8));
            return URLEncoder.encode(new String(Base64.encodeBase64(signData)),"UTF-8");
        } catch (Exception e) {
            log.error("generateSign fail:", e);
            return null;
        }
    }
}