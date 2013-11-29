package com.alipay.simplehbase.convertor;

import java.util.Date;

import org.apache.hadoop.hbase.util.Bytes;

import com.alipay.simplehbase.exception.SimpleHBaseException;

/**
 * Ĭ�ϵ�ColumnConvertor��
 * 
 * <pre>
 * ���Դ����������ͣ�
 * boolean,Boolean (1 byte �洢)
 * int,Integer (4 bytes �洢)
 * long,Long (8 bytes �洢)
 * String (utf-8 �洢)
 * Date (8 bytes �洢)
 * Enum (name����utf-8�洢)
 * </pre>
 * 
 * @author xinzhi
 * @version $Id: DefaultColumnConvertor.java 2013-09-11 ����11:27:31 xinzhi $
 * */
public class DefaultColumnConvertor implements ColumnConvertor {

    @Override
    public byte[] toBytes(Class<?> type, Object value) {
        if (value == null) {
            return null;
        }

        if (type == boolean.class || type == Boolean.class) {
            return Bytes.toBytes((Boolean) value);
        }

        if (type == int.class || type == Integer.class) {
            return Bytes.toBytes((Integer) value);
        }

        if (type == long.class || type == Long.class) {
            return Bytes.toBytes((Long) value);
        }

        if (type == String.class) {
            return Bytes.toBytes((String) value);
        }
        if (type == Date.class) {
            long time = ((Date) value).getTime();
            return Bytes.toBytes(time);

        }

        if (type.isEnum()) {
            String name = ((Enum<?>) value).name();
            return Bytes.toBytes(name);
        }

        throw new SimpleHBaseException("not support now. value=" + value
                + " type=" + type);
    }

    //TODO allen [simplehbase] ������û��IDE��������ʵ�ַ�ʽ��
    @Override
    public Object toObject(Class type, byte[] bytes) {
        if (bytes == null || bytes.length == 0) {
            return null;
        }

        if (type == boolean.class || type == Boolean.class) {
            return Bytes.toBoolean(bytes);
        }

        if (type == int.class || type == Integer.class) {
            return Bytes.toInt(bytes);
        }
        if (type == long.class || type == Long.class) {
            return Bytes.toLong(bytes);
        }
        if (type == String.class) {
            return Bytes.toString(bytes);
        }
        if (type == Date.class) {
            long time = Bytes.toLong(bytes);
            return new Date(time);
        }

        if (type.isEnum()) {
            String name = Bytes.toString(bytes);
            return Enum.valueOf(type, name);
        }

        throw new SimpleHBaseException("not support now. type=" + type);
    }

}
