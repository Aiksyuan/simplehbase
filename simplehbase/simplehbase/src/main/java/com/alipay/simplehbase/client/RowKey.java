package com.alipay.simplehbase.client;

/**
 * RowKey��Ӧ��hbase��rowkey��
 * 
 * @author xinzhi
 * @version $Id: RowKey.java 2013-09-11 ����11:27:31 xinzhi $
 * */
public interface RowKey {

    /**
     * ת������Ϊbyte[]����ʽ��
     * 
     * @return rowKey��byte[]��ʽ��
     * */
    public byte[] toBytes();
}
