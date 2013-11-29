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
 * ���ڱ�ʾ��fieldΪPOJO�İ汾�Ŷ���
 * ��ʾΪHBaseVersion��field����ͬʱ��ʾΪHBaseColumn��������Ч��
 * 1��POJOֻ��������1��fieldʹ�øñ�ʾ��
 * </pre>
 * 
 * @author xinzhi
 * @version $Id: HBaseVersion.java 2013-09-11 ����11:27:31 xinzhi $
 * */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface HBaseVersion {

}
