package com.alipay.simplehbase.convertor;

/**
 * Hbase���к�javaType��value֮���ת������
 * 
 * @author xinzhi
 * @version $Id: ColumnConvertor.java 2013-09-11 ����11:27:31 xinzhi $
 * */
public interface ColumnConvertor {

    /**
     * ת��valueΪhbase����ֵ��
     * 
     * @param type java��type��
     * @param value java��value��
     * @return hbase����ֵ��
     * */
    public byte[] toBytes(Class<?> type, Object value);

    /**
     * ת��hbase����ֵΪjava��object��
     * 
     * @param type java��type��
     * @param bytes hbase����ֵ��
     * @return java��value��
     * */
    public Object toObject(Class<?> type, byte[] bytes);

}
