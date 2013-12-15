package com.alipay.simplehbase.client;

import java.util.List;
import java.util.Map;

import com.alipay.simplehbase.config.HBaseDataSource;
import com.alipay.simplehbase.config.HBaseTableConfig;

/**
 * SimpleHbaseClient��
 * 
 * <pre>
 * ʹ��simpleHbase��client��
 * </pre>
 * 
 * @author xinzhi
 * @version $Id: SimpleHbaseClient.java 2013-09-11 ����11:27:31 xinzhi $
 * */
public interface SimpleHbaseClient {

    /**
     * ����rowKeyָ���Ķ���
     * 
     * @param rowKey rowKey��
     * @param type POJO type��
     * @return POJO��
     * */
    public <T> T findObject(RowKey rowKey, Class<? extends T> type);

    /**
     * ����[startRowKey,endRowKey)��Χ��POJO�����б�
     * 
     * @param startRowKey startRowKey��
     * @param endRowKey endRowKey��
     * @param type POJO type��
     * @return POJO�����б�
     * */
    public <T> List<T> findObjectList(RowKey startRowKey, RowKey endRowKey,
            Class<? extends T> type);

    /**
     * ����[startRowKey,endRowKey)��Χ��POJO�����б�
     * 
     * @param startRowKey startRowKey��
     * @param endRowKey endRowKey��
     * @param type POJO type��
     * @param startIndex �����ʼ������0-based��
     * @param length ����б�size���ơ�
     * @return POJO�����б�
     * */
    public <T> List<T> findObjectList(RowKey startRowKey, RowKey endRowKey,
            Class<? extends T> type, long startIndex, long length);

    /**
     * ��̬����id��ѯ������[startRowKey,endRowKey)��Χ��POJO�����б�
     * 
     * @param startRowKey startRowKey��
     * @param endRowKey endRowKey��
     * @param type POJO type��
     * @param id ��̬���id��
     * @param para ������
     * 
     * @return POJO�����б�
     * */
    public <T> List<T> findObjectList(RowKey startRowKey, RowKey endRowKey,
            Class<? extends T> type, String id, Map<String, Object> para);

    /**
     * ��̬����id��ѯ������[startRowKey,endRowKey)��Χ��POJO�����б�
     * 
     * @param startRowKey startRowKey��
     * @param endRowKey endRowKey��
     * @param type POJO type��
     * @param startIndex �����ʼ������0-based��
     * @param length ����б�size���ơ�
     * @param id ��̬���id��
     * @param para ������
     * 
     * @return POJO�����б�
     * */
    public <T> List<T> findObjectList(RowKey startRowKey, RowKey endRowKey,
            Class<? extends T> type, long startIndex, long length, String id,
            Map<String, Object> para);

    /**
     * ��̬����hql��ѯ������[startRowKey,endRowKey)��Χ��POJO�����б�
     * 
     * @param startRowKey startRowKey��
     * @param endRowKey endRowKey��
     * @param type POJO type��
     * @param hql ԭ��hql��
     * @param para ������
     * 
     * @return POJO�����б�
     * */
    public <T> List<T> findObjectListByRawHql(RowKey startRowKey,
            RowKey endRowKey, Class<? extends T> type, String hql,
            Map<String, Object> para);

    /**
     * ��̬����hql��ѯ������[startRowKey,endRowKey)��Χ��POJO�����б�
     * 
     * @param startRowKey startRowKey��
     * @param endRowKey endRowKey��
     * @param type POJO type��
     * @param startIndex �����ʼ������0-based��
     * @param length ����б�size���ơ�
     * @param hql ԭ��hql��
     * @param para ������
     * 
     * @return POJO�����б�
     * */
    public <T> List<T> findObjectListByRawHql(RowKey startRowKey,
            RowKey endRowKey, Class<? extends T> type, long startIndex,
            long length, String hql, Map<String, Object> para);

    /**
     * Put POJO��
     * 
     * @param rowKey rowKey��
     * @param t POJO��
     * */
    public <T> void putObject(RowKey rowKey, T t);

    /**
     * Delete POJO��
     * 
     * @param rowKey rowKey��
     * */
    public void deleteObject(RowKey rowKey);

    /**
     * Delete POJOList��
     * 
     * @param startRowKey startRowKey��
     * @param endRowKey endRowKey��
     * 
     * */
    public void deleteObjectList(RowKey startRowKey, RowKey endRowKey);

    //-----------------------���·���ֻ�����ڴ��汾�ŵ�POJO��-----------------------
    /**
     * ����POJO��
     * 
     * <pre>
     * rowKey��Ӧ��POJO�汾Ϊnullʱ(���ݲ�����)����ɹ���
     * </pre>
     * 
     * @param rowKey rowKey��
     * @param t POJO
     * @return �Ƿ����ɹ���
     * */
    public <T> boolean insertObject(RowKey rowKey, T t);

    /**
     * ����POJO��
     * 
     * <pre>
     * oldT�еİ汾��Ϊ�ϰ汾�š�
     * newT�еİ汾��Ϊ�°汾�š�
     * </pre>
     * 
     * @param rowKey rowKey��
     * @param oldT oldT��
     * @param newT newT��
     * @return �Ƿ���³ɹ���
     * */
    public <T> boolean updateObject(RowKey rowKey, T oldT, T newT);

    /**
     * ����POJO��
     * 
     * @param rowKey rowKey��
     * @param t POJO��
     * @param oldVersion �ϰ汾��
     * @return �Ƿ���³ɹ���
     * */
    public <T> boolean updateObjectWithVersion(RowKey rowKey, T t,
            Object oldVersion);

    /**
     * �õ�HBaseDataSource��
     * 
     * @return HBaseDataSource��
     * */
    public HBaseDataSource getHBaseDataSource();

    /**
     * ����HBaseDataSource��
     * 
     * @param hbaseDataSource HBaseDataSource��
     * */
    public void setHBaseDataSource(HBaseDataSource hbaseDataSource);

    /**
     * �õ�HBaseTableConfig��
     * 
     * @return HBaseTableConfig��
     * */
    public HBaseTableConfig getHbaseTableConfig();

    /**
     * ����HBaseTableConfig��
     * 
     * @param hbaseTableConfig HBaseTableConfig��
     * */
    public void setHbaseTableConfig(HBaseTableConfig hbaseTableConfig);

}
