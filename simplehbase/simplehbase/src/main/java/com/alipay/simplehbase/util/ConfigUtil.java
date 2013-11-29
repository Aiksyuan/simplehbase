package com.alipay.simplehbase.util;

import java.util.Map;

/**
 * ConfigUtil��
 * 
 * @author xinzhi
 * @version $Id: ConfigUtil.java 2013-09-11 ����11:27:31 xinzhi $
 * */
public class ConfigUtil {

    /**
     * ����config�е�key��Ӧ��value�����������valueС��1�򷵻�defaultValue��
     * 
     * @param config config��
     * @param key key��
     * @param defaultValue defaultValue��
     * @return config�е�key��Ӧ��value�����������valueС��1�򷵻�defaultValue��
     * */
    public static int parsePositiveInt(Map<String, String> config, String key,
            int defaultValue) {
        try {
            String vaule = config.get(key);
            int result = Integer.parseInt(vaule);
            if (result > 0) {
                return result;
            } else {
                return defaultValue;
            }
        } catch (Exception e) {
            return defaultValue;
        }
    }

    private ConfigUtil() {
    }
}
