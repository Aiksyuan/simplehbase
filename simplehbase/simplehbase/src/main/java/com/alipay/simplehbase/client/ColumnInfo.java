package com.alipay.simplehbase.client;

import java.lang.reflect.Field;

import org.apache.hadoop.hbase.util.Bytes;

import com.alipay.simplehbase.exception.SimpleHBaseException;
import com.alipay.simplehbase.util.StringUtil;

/**
 * POJO��field��Hbase����ӳ����Ϣ��
 * 
 * @author xinzhi
 * @version $Id: ColumnInfo.java 2013-09-11 ����11:27:31 xinzhi $
 * */
public class ColumnInfo {

    /**
     * ��POJO��field������ColumnInfo��
     * 
     * @param type POJO��class type��
     * @param field POJO��field��
     * @return ��������ColumnInfo��
     * */
    public static ColumnInfo parse(Class<?> type, Field field) {
        String defaultFamily = null;

        HBaseTable hbaseTable = type.getAnnotation(HBaseTable.class);
        if (hbaseTable != null) {
            defaultFamily = hbaseTable.defaultFamily();
        }

        HBaseColumn hbaseColumn = field.getAnnotation(HBaseColumn.class);
        if (hbaseColumn == null) {
            return null;
        }

        String family = hbaseColumn.family();
        String qualifier = hbaseColumn.qualifier();

        if (StringUtil.isEmptyString(family)) {
            family = defaultFamily;
        }

        if (StringUtil.isEmptyString(family)) {
            throw new SimpleHBaseException("family is null or empty. type="
                    + type + " field=" + field);
        }

        if (StringUtil.isEmptyString(qualifier)) {
            throw new SimpleHBaseException("qualifier is null or empty. type="
                    + type + " field=" + field);
        }

        ColumnInfo columnInfo = new ColumnInfo();
        columnInfo.type = type;
        columnInfo.field = field;
        columnInfo.family = family;
        columnInfo.familyBytes = Bytes.toBytes(family);
        columnInfo.qualifier = qualifier;
        columnInfo.qualifierBytes = Bytes.toBytes(qualifier);

        return columnInfo;
    }

    /** POJO��class type�� */
    Class<?> type;
    /** POJO��field�� */
    Field    field;
    /** hbase��family�� */
    String   family;
    /** hbase��family�� */
    byte[]   familyBytes;
    /** hbase��qualifier�� */
    String   qualifier;
    /** hbase��qualifier�� */
    byte[]   qualifierBytes;

    private ColumnInfo() {
    }

    @Override
    public String toString() {
        return "type=" + type + " field=" + field + " family=" + family
                + " qualifier=" + qualifier;
    }
}