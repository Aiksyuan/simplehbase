package com.alipay.simplehbase.config;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * ���һ��java�����attrΪһ���������ԡ�
 * 
 * @author xinzhi
 * @version $Id: ConfigAttr.java 2013-09-11 ����11:27:31 xinzhi $
 * */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.SOURCE)
public @interface ConfigAttr {
}
