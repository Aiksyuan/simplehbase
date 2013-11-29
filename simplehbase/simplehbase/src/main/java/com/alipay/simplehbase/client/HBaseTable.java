package com.alipay.simplehbase.client;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * simpleHbase��POJO-Hbaseӳ�������ࡣ
 * 
 * <pre>
 * ������POJO��type��
 * HBaseColumn���õ�family���Զ�����ͨ��HBaseTable���õ�family��
 * </pre>
 * 
 * @author xinzhi
 * @version $Id: HBaseTable.java 2013-09-11 ����11:27:31 xinzhi $
 * */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface HBaseTable {

    /**
     * default family name��
     * 
     * @return default family��
     */
    public String defaultFamily() default "";
}
