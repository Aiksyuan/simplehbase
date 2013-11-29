package com.alipay.simplehbase.client;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.alipay.simplehbase.exception.SimpleHBaseException;

/**
 * POJO��Hbase��ӳ����Ϣ��
 * 
 * @author xinzhi
 * @version $Id: TypeInfo.java 2013-09-11 ����11:27:31 xinzhi $
 * */
public class TypeInfo {

    /**
     * ����POJO��type����TypeInfo��
     * 
     * @param type POJO��type��
     * @return ��������TypeInfo��
     * */
    public static TypeInfo parse(Class<?> type) {
        if (type == null) {
            throw new SimpleHBaseException("type is null.");
        }

        TypeInfo typeInfo = new TypeInfo();
        typeInfo.type = type;

        Field[] fields = type.getDeclaredFields();

        int versionFieldCounter = 0;

        for (Field field : fields) {

            field.setAccessible(true);

            ColumnInfo columnInfo = ColumnInfo.parse(type, field);
            if (columnInfo == null) {
                continue;
            }

            typeInfo.columnInfos.add(columnInfo);

            if (field.isAnnotationPresent(HBaseVersion.class)) {
                typeInfo.versionedColumnInfo = columnInfo;
                versionFieldCounter++;
            }

            typeInfo.families.add(columnInfo.family);
        }

        if (versionFieldCounter > 1) {
            throw new SimpleHBaseException("more than one versioned fields.");
        }

        if (typeInfo.columnInfos.isEmpty()) {
            throw new SimpleHBaseException("columnInfos is empty.");
        }

        return typeInfo;
    }

    /** POJO��type�� */
    private Class<?>         type;
    /** ��POJO��ColumnInfo�б� */
    private List<ColumnInfo> columnInfos = new ArrayList<ColumnInfo>();
    /** ��POJO�İ汾��field��Ӧ��ColumnInfo,����Ϊnull�� */
    private ColumnInfo       versionedColumnInfo;
    /** ��Typeʹ�õ�����family���ơ� */
    private Set<String>      families    = new HashSet<String>();

    /**
     * �Ƿ��Ǵ��汾�ŵ�typeInfo��
     * 
     * @return �Ƿ��Ǵ��汾�ŵ�typeInfo��
     * */
    public boolean isVersionedType() {
        return versionedColumnInfo != null;
    }

    public Class<?> getType() {
        return type;
    }

    public List<ColumnInfo> getColumnInfos() {
        return columnInfos;
    }

    public ColumnInfo getVersionedColumnInfo() {
        return versionedColumnInfo;
    }

    public Set<String> getFamilies() {
        return families;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("-----------------" + getClass()
                + "-----------------------\n");
        sb.append("type=" + type + "\n");
        sb.append("versionedColumnInfo=" + versionedColumnInfo + "\n");
        sb.append("families=" + families + "\n");
        for (ColumnInfo columnInfo : columnInfos) {
            sb.append(columnInfo + "\n");
        }
        sb.append("-----------------" + getClass()
                + "-----------------------\n");
        return sb.toString();
    }
}
