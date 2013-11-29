package com.alipay.simplehbase.util;

import java.io.IOException;

import org.apache.hadoop.hbase.client.HTableInterface;
import org.apache.hadoop.hbase.client.ResultScanner;

import com.alipay.simplehbase.client.RowKey;
import com.alipay.simplehbase.client.TypeInfo;
import com.alipay.simplehbase.exception.SimpleHBaseException;

/**
 * Client Util��
 * 
 * @author xinzhi
 * @version $Id: Util.java 2013-09-11 ����11:27:31 xinzhi $
 * */
public class Util {

    /**
     * Check object is null.
     * 
     * @param obj obj.
     * */
    public static void checkNull(Object obj) {
        if (obj == null) {
            throw new SimpleHBaseException("obj is null.");
        }
    }

    /**
     * ���rowKey.
     * 
     * <pre>
     * rowKey��Ϊnull��
     * rowKey��toBytes��Ϊnull��
     * </pre>
     * 
     * @param rowKey rowKey��
     * */
    public static void checkRowKey(RowKey rowKey) {
        if (rowKey == null) {
            throw new SimpleHBaseException("rowkey is null.");
        }
        if (rowKey.toBytes() == null) {
            throw new SimpleHBaseException("rowkey bytes is null.");
        }
    }

    /**
     * �ر�HTableInterface��
     * 
     * @param htableInterface htableInterface��
     * */
    public static void close(HTableInterface htableInterface) {
        if (htableInterface == null) {
            return;
        }

        try {
            htableInterface.close();
        } catch (IOException e) {
            throw new SimpleHBaseException("close htableInterface exception.",
                    e);
        }
    }

    /**
     * �ر�ResultScanner��
     * 
     * @param resultScanner resultScanner��
     * */
    public static void close(ResultScanner resultScanner) {
        if (resultScanner == null) {
            return;
        }
        resultScanner.close();
    }

    /**
     * ���typeInfo�Ƿ��Ǵ��汾�ŵ�typeInfo��
     * 
     * @param typeInfo typeInfo��
     * */
    public static void checkVersioned(TypeInfo typeInfo) {
        checkNull(typeInfo);
        if (!typeInfo.isVersionedType()) {
            throw new SimpleHBaseException("not a versioned type. typeInfo = "
                    + typeInfo.getType());
        }
    }

    /**
     * ���2����������ͬ��class��
     * 
     * @param obj ��1������
     * @param other ��2������
     * */
    public static void checkSameType(Object obj, Object other) {
        checkNull(obj);
        checkNull(other);
        if (obj.getClass() != other.getClass()) {
            throw new SimpleHBaseException("not same type. obj = " + obj
                    + " other = " + other);
        }
    }

    private Util() {
    }
}
