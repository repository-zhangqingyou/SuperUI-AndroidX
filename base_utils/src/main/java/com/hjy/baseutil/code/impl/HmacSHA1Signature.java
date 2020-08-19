package com.hjy.baseutil.code.impl;


import com.blankj.utilcode.util.EncodeUtils;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/**
 * Author: zhangqingyou
 * Date: 2020/5/19 10:43
 * Des:
 */
public class HmacSHA1Signature {
    private static final String DEFAULT_ENCODING = "UTF-8";
    private static final String ALGORITHM = "HmacSHA1";
    private static final String VERSION = "1";
    private static final Object LOCK = new Object();
    private static Mac macInstance;

    public HmacSHA1Signature() {
    }

    public String getAlgorithm() {
        return "HmacSHA1";
    }

    public String getVersion() {
        return "1";
    }

    public String computeSignature(String key, String data) {
        try {
            byte[] signData = this.sign(key.getBytes("UTF-8"), data.getBytes("UTF-8"));
            return String.valueOf(EncodeUtils.base64Encode(signData));
        } catch (UnsupportedEncodingException var4) {
            throw new RuntimeException("Unsupported algorithm: UTF-8");
        }
    }

    private byte[] sign(byte[] key, byte[] data) {
        try {
            if (macInstance == null) {
                synchronized (LOCK) {
                    if (macInstance == null) {
                        macInstance = Mac.getInstance("HmacSHA1");
                    }
                }
            }

            Mac mac;
            try {
                mac = (Mac) macInstance.clone();
            } catch (CloneNotSupportedException var5) {
                mac = Mac.getInstance("HmacSHA1");
            }

            mac.init(new SecretKeySpec(key, "HmacSHA1"));
            return mac.doFinal(data);
        } catch (NoSuchAlgorithmException var7) {
            throw new RuntimeException("Unsupported algorithm: HmacSHA1");
        } catch (InvalidKeyException var8) {
            throw new RuntimeException();
        }
    }


}
