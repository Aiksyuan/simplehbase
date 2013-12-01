package com.alipay.simplehbase.util;

import java.util.List;
import java.util.Map;

import com.alipay.simplehbase.exception.SimpleHBaseException;

/**
 * StringUtil��
 * 
 * @author xinzhi
 * @version $Id: StringUtil.java 2013-09-11 ����11:27:31 xinzhi $
 * */
public class StringUtil {

    /**
     * str�Ƿ���null��empty?
     * */
    public static boolean isEmptyString(String str) {
        return str == null || str.isEmpty();
    }

    /**
     * str�Ƿ��Ƿ�null�ҷ�empty?
     * */
    public static boolean isNotEmptyString(String str) {
        return !isEmptyString(str);
    }

    /**
     * ���str��Ϊempty��
     * */
    public static void checkEmptyString(String str) {
        if (isEmptyString(str)) {
            throw new SimpleHBaseException("string is null or empty.");
        }
    }

    /**
     * ����ַ������ȡ�
     * */
    public static void checkLength(String value, int length) {
        if (value.length() != length) {
            throw new SimpleHBaseException("checkLength error. value=" + value
                    + " length=" + length);
        }
    }

    public static void append(StringBuilder sb, String msg, Object value) {
        sb.append(msg + "=" + value + "\n");
    }

    public static void append(StringBuilder sb, String msg,
            Map<String, String> map) {
        sb.append(msg + "\n");
        if (map != null) {
            for (String key : map.keySet()) {
                sb.append(key + "=" + map.get(key) + "\n");
            }
        }
    }

    public static void append(StringBuilder sb, String msg, List<String> list) {
        sb.append(msg + "\n");
        if (list != null) {
            for (String s : list) {
                sb.append(s + "\n");
            }
        }
    }

    private StringUtil() {
    }
}
