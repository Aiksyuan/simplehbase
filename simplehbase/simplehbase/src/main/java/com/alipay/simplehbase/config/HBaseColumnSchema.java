package com.alipay.simplehbase.config;

import org.apache.hadoop.hbase.util.Bytes;
import org.apache.log4j.Logger;

import com.alipay.simplehbase.exception.SimpleHBaseException;
import com.alipay.simplehbase.type.DefaultTypeHandlers;
import com.alipay.simplehbase.type.TypeHandler;
import com.alipay.simplehbase.type.TypeHandlerHolder;
import com.alipay.simplehbase.util.ClassUtil;
import com.alipay.simplehbase.util.StringUtil;
import com.alipay.simplehbase.util.Util;

/**
 * HbaseTable���е�schema��
 * 
 * <pre>
 * ��������hbaseTable����Ԫ��Ϣ��
 * </pre>
 * 
 * @author xinzhi
 * @version $Id: HBaseColumnSchema.java 2013-09-11 ����11:27:31 xinzhi $
 * 
 * */
public class HBaseColumnSchema {

    /** log. */
    private static Logger log = Logger.getLogger(HBaseColumnSchema.class);

    // ----------config------
    /** hbase��family�� */
    @ConfigAttr
    private String        family;
    /** hbase��qualifier�� */
    @ConfigAttr
    private String        qualifier;
    /** ��Ӧ��javaType�� */
    @ConfigAttr
    private String        typeName;
    /** typeHandler��type�� */
    @ConfigAttr
    private String        typeHandlerName;

    // -----------runtime------
    /** hbase��family�� */
    private byte[]        familyBytes;
    /** hbase��qualifier�� */
    private byte[]        qualifierBytes;
    /** ��Ӧ��javaType�� */
    private Class<?>      type;
    /** TypeHandler�� */
    private TypeHandler   typeHandler;

    /**
     * ��ʼ����
     * */
    public void init() {

        Util.checkEmptyString(family);
        Util.checkEmptyString(qualifier);
        Util.checkEmptyString(typeName);

        try {
            familyBytes = Bytes.toBytes(family);
            qualifierBytes = Bytes.toBytes(qualifier);
            type = ClassUtil.forName(typeName);
            Util.checkNull(type);
            
            if (StringUtil.isEmptyString(typeHandlerName)) {
                typeHandler = DefaultTypeHandlers.findDefaultHandler(type);
                typeHandlerName = typeHandler.getClass().getName();
            } else {
                typeHandler = TypeHandlerHolder
                        .findTypeHandler(typeHandlerName);
            }

            Util.checkNull(typeHandlerName);
            Util.checkNull(typeHandler);

        } catch (Exception e) {
            log.error(e);
            throw new SimpleHBaseException(e);
        }
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public String getQualifier() {
        return qualifier;
    }

    public void setQualifier(String qualifier) {
        this.qualifier = qualifier;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public byte[] getFamilyBytes() {
        return familyBytes;
    }

    public byte[] getQualifierBytes() {
        return qualifierBytes;
    }

    public String getTypeHandlerName() {
        return typeHandlerName;
    }

    public void setTypeHandlerName(String typeHandlerName) {
        this.typeHandlerName = typeHandlerName;
    }

    public Class<?> getType() {
        return type;
    }

    public TypeHandler getTypeHandler() {
        return typeHandler;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[family=" + family + ",");
        sb.append("qualifier=" + qualifier + ",");
        sb.append("typeName=" + typeName + ",");
        sb.append("typeHandlerName=" + typeHandlerName + "]");
        return sb.toString();
    }
}
