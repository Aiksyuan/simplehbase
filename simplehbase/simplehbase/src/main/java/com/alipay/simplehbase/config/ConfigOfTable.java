package com.alipay.simplehbase.config;

/**
 * SimpleHbase��table˽�������
 * 
 * @author xinzhi
 * @version $Id: ConfigOfTable.java 2013-09-11 ����11:27:31 xinzhi $
 * */
public class ConfigOfTable {
    /**
     * SCAN_CACHING������SCAN��caching��С��
     * */
    public static final String SCAN_CACHING         = "SCAN_CACHING";
    /**
     * SCAN_CACHING��Ĭ�ϴ�С��
     * */
    public static final int    SCAN_CACHING_DEFAULT = 50;

    /**
     * DELETE_BATCH������ɾ��ʱ�������Ĵ�С��
     * */
    public static final String DELETE_BATCH         = "DELETE_BATCH";
    /**
     * DELETE_BATCH��Ĭ�ϴ�С��
     * */
    public static final int    DELETE_BATCH_DEFAULT = 20;
}
