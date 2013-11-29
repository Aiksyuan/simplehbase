package com.alipay.simplehbase.config;

import java.util.Map;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.client.HTableInterface;
import org.apache.hadoop.hbase.client.HTablePool;

import com.alipay.simplehbase.exception.SimpleHBaseException;
import com.alipay.simplehbase.util.ConfigUtil;

/**
 * HTablePool��Holder��
 * 
 * @author xinzhi
 * @version $Id: HTablePoolHolder.java 2013-09-11 ����11:27:31 xinzhi $
 * */
public class HTablePoolHolder {

    /**
     * HTablePool.
     * */
    private HTablePool htablePool;

    /**
     * �õ�ָ��������HTableInterface��
     * 
     * @param tableName ������
     * @return HTableInterface��
     * */
    public HTableInterface getHTable(String tableName) {
        if (tableName == null) {
            throw new SimpleHBaseException("tableName is null.");
        }
        return htablePool.getTable(tableName);
    }

    /**
     * ��ʼ����
     * 
     * @param hbaseConfiguration hbase��config��
     * @param finalDataSourceConfig simplehbase��finalDataSourceConfig��
     * */
    public void init(Configuration hbaseConfiguration,
            Map<String, String> finalDataSourceConfig) {
        if (hbaseConfiguration == null) {
            throw new SimpleHBaseException("hbaseConfig is null.");
        }
        if (finalDataSourceConfig == null) {
            throw new SimpleHBaseException("finalDataSourceConfig is null.");
        }

        int hbasePoolSize = ConfigUtil.parsePositiveInt(finalDataSourceConfig,
                ConfigOfDataSource.HTABLE_POOL_SIZE,
                ConfigOfDataSource.HTABLE_POOL_SIZE_DEFAULT);

        htablePool = new HTablePool(hbaseConfiguration, hbasePoolSize);
    }

}
