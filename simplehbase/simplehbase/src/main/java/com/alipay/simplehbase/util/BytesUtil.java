package com.alipay.simplehbase.util;

import org.apache.hadoop.hbase.util.Bytes;

import com.alipay.simplehbase.exception.SimpleHBaseException;

/**
 * �ֽڹ����ࡣ
 * 
 * @author xinzhi
 * @version $Id: BytesUtil.java, v 0.1 2013-9-13 ����03:09:40 xinzhi Exp $
 */
public class BytesUtil {

    /** byte[] ZERO */
    public static final byte[] ZERO = { (byte) 0 };

    /** byte[] ONE */
    public static final byte[] ONE  = { (byte) 1 };

    /**
     * �ֽ�����ϲ�
     * 
     * @param bytesArray ����ֽ�����
     * @return �ϲ���һ���ֽ�����
     */
    public static byte[] merge(byte[]... bytesArray) {
        byte[] result = new byte[] {};

        if (bytesArray == null) {
            return result;
        }

        for (byte[] bytes : bytesArray) {
            if (bytes == null) {
                continue;
            }
            result = Bytes.add(result, bytes);
        }

        return result;
    }

    /**
     * ������鳤�ȡ�
     * */
    public static void checkLength(byte[] values, int length) {
        if (values.length != length) {
            throw new SimpleHBaseException("checkLength error. values.length="
                    + values.length + " length=" + length);
        }
    }
}