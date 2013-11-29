package com.alipay.simplehbase.config;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.hadoop.hbase.util.Bytes;

import com.alipay.simplehbase.convertor.ColumnConvertor;
import com.alipay.simplehbase.convertor.ColumnConvertorHolder;
import com.alipay.simplehbase.exception.SimpleHBaseException;
import com.alipay.simplehbase.util.StringUtil;

/**
 * HbaseTable��schema��
 * 
 * <pre>
 * ��������hbaseTable��Ԫ��Ϣ��
 * </pre>
 * 
 * @author xinzhi
 * @version $Id: HBaseTableSchema.java 2013-09-11 ����11:27:31 xinzhi $
 * 
 * */
public class HBaseTableSchema {

    //------------constant----------------
    /**
     * Family��Qualifier�����ӷ���
     **/
    private static final String            FamilyQualifierConnector = "^";

    //------------xml config-------------------
    /**
     * tableName. not null.
     * */
    @ConfigAttr
    private String                         tableName;
    /**
     * Ĭ�ϵ�Family��
     * */
    @ConfigAttr
    private String                         defaultFamily;
    /**
     * Ĭ�ϵ�columnConvertor��type��
     * */
    @ConfigAttr
    private String                         defaultColumnConvertorType;

    //------------runtime-------------------
    /**
     * Ĭ�ϵ�Family��
     * */
    private byte[]                         defaultFamilyBytes;
    /**
     * Ĭ�ϵ�columnConvertor��
     * */
    private ColumnConvertor                defaultColumnConvertor;
    /**
     * Family��Qualifier -> HBaseColumnSchema��
     * */
    private Map<String, HBaseColumnSchema> haseColumnSchemas        = new TreeMap<String, HBaseColumnSchema>();

    /**
     * ��ʼ����
     * */
    public void init(List<HBaseColumnSchema> hbaseColumnSchemas) {

        StringUtil.checkEmptyString(tableName);
        if (StringUtil.isNotEmptyString(defaultFamily)) {
            defaultFamilyBytes = Bytes.toBytes(defaultFamily);
        }

        if (StringUtil.isNotEmptyString(defaultColumnConvertorType)) {
            defaultColumnConvertor = ColumnConvertorHolder
                    .findConvertor(defaultColumnConvertorType);

        }

        if (hbaseColumnSchemas.isEmpty()) {
            throw new SimpleHBaseException("no HBaseColumnSchemas.");
        }

        for (HBaseColumnSchema columnSchema : hbaseColumnSchemas) {
            if (StringUtil.isEmptyString(columnSchema.getFamily())) {
                columnSchema.setFamily(defaultFamily);
            }

            if (StringUtil.isEmptyString(columnSchema.getColumnConvertorType())) {
                columnSchema.setColumnConvertorType(defaultColumnConvertorType);
            }

            columnSchema.init();

            String key = mapKey(columnSchema.getFamily(),
                    columnSchema.getQualifier());
            haseColumnSchemas.put(key, columnSchema);
        }

    }

    /**
     * ����family��qualifier����HBaseColumnSchema��
     * */
    public HBaseColumnSchema findColumnSchema(String family, String qualifier) {
        return haseColumnSchemas.get(mapKey(family, qualifier));
    }

    /**
     * ��family��qualifier����haseColumnSchemas��keyֵ��
     * */
    private String mapKey(String family, String qualifier) {
        return family + FamilyQualifierConnector + qualifier;
    }

    public String getDefaultFamily() {
        return defaultFamily;
    }

    public void setDefaultFamily(String defaultFamily) {
        this.defaultFamily = defaultFamily;
    }

    public String getDefaultColumnConvertorType() {
        return defaultColumnConvertorType;
    }

    public void setDefaultColumnConvertorType(String defaultColumnConvertorType) {
        this.defaultColumnConvertorType = defaultColumnConvertorType;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public byte[] getDefaultFamilyBytes() {
        return defaultFamilyBytes;
    }

    public void setDefaultFamilyBytes(byte[] defaultFamilyBytes) {
        this.defaultFamilyBytes = defaultFamilyBytes;
    }

    public ColumnConvertor getDefaultColumnConvertor() {
        return defaultColumnConvertor;
    }

    public void setDefaultColumnConvertor(ColumnConvertor defaultColumnConvertor) {
        this.defaultColumnConvertor = defaultColumnConvertor;
    }

    public Map<String, HBaseColumnSchema> getHaseColumnSchemas() {
        return haseColumnSchemas;
    }

    public void setHaseColumnSchemas(
            Map<String, HBaseColumnSchema> haseColumnSchemas) {
        this.haseColumnSchemas = haseColumnSchemas;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("---------------table--------------------------\n");
        StringUtil.append(sb, "tableName", tableName);
        StringUtil.append(sb, "defaultFamily", defaultFamily);
        StringUtil.append(sb, "defaultColumnConvertorType",
                defaultColumnConvertorType);

        for (HBaseColumnSchema columnSchema : haseColumnSchemas.values()) {
            StringUtil.append(sb, "columnSchema", columnSchema);
        }

        sb.append("---------------table--------------------------\n");
        return sb.toString();
    }
}
