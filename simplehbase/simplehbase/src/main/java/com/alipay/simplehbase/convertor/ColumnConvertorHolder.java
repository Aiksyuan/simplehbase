package com.alipay.simplehbase.convertor;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import com.alipay.simplehbase.exception.SimpleHBaseException;
import com.alipay.simplehbase.util.ClassUtil;

/**
 * ColumnConvertorʵ����Holder�ࡣ
 * 
 * @author xinzhi
 * @version $Id: ColumnConvertorHolder.java 2013-09-11 ����11:27:31 xinzhi $
 * */
public class ColumnConvertorHolder {

    /**
     * ColumnConvertor��Type -> ColumnConvertor��ʵ����
     * */
    private static ConcurrentMap<String, ColumnConvertor> convertorCache = new ConcurrentHashMap<String, ColumnConvertor>();

    /**
     * ��ColumnConvertor��Type�õ�ColumnConvertor��ʵ����
     * 
     * @param type ColumnConvertor��Type��
     * @return ColumnConvertor��ʵ����
     * */
    public static ColumnConvertor findConvertor(String type) {

        if (convertorCache.get(type) == null) {
            try {
                Class<?> c = ClassUtil.forName(type);
                convertorCache.putIfAbsent(type,
                        (ColumnConvertor) c.newInstance());
            } catch (Exception e) {
                throw new SimpleHBaseException(e);
            }
        }
        return convertorCache.get(type);
    }
}
