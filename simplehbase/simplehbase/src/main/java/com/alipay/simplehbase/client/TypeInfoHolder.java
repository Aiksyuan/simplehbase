package com.alipay.simplehbase.client;

import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;

/**
 * TypeInfo��Holder�ࡣ
 * 
 * @author xinzhi
 * @version $Id: TypeInfoHolder.java 2013-09-11 ����11:27:31 xinzhi $
 * */
public class TypeInfoHolder {

    /** log. */
    private static Logger                                log       = Logger.getLogger(TypeInfoHolder.class);

    /** type->typeInfo map. */
    private static ConcurrentHashMap<Class<?>, TypeInfo> typeInfos = new ConcurrentHashMap<Class<?>, TypeInfo>();

    /**
     * ��type�õ�TypeInfo��
     * 
     * @param type POJO��type��
     * @return TypeInfo��
     */
    public static TypeInfo findTypeInfo(Class<?> type) {

        if (typeInfos.get(type) == null) {
            TypeInfo oldTypeInfo = typeInfos.putIfAbsent(type,
                    TypeInfo.parse(type));
            if (oldTypeInfo == null) {
                log.info("register TypeInfo\n" + typeInfos.get(type));
            }
        }

        return typeInfos.get(type);
    }
}