package com.alipay.simplehbase.client;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * simpleHbase��POJO-Hbaseӳ�������ࡣ
 * 
 * <pre>
 * ������POJO��field��
 * HBaseColumn���õ�family���Զ�����ͨ��HBaseTable���õ�family��
 * </pre>
 * 
 * @author xinzhi
 * @version $Id: HBaseColumn.java 2013-09-11 ����11:27:31 xinzhi $
 * */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface HBaseColumn {

    /**
     * family name��
     * 
     * @return family��
     */
    public String family() default "";

    /**
     * qualifier name��
     * 
     * @return qualifier��
     */
    public String qualifier();
}
