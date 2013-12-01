package com.alipay.simplehbase.type;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import com.alipay.simplehbase.exception.SimpleHBaseException;
import com.alipay.simplehbase.util.ClassUtil;

/**
 * TypeHandlerʵ����Holder�ࡣ
 * 
 * @author xinzhi
 * @version $Id: TypeHandlerHolder.java 2013-09-11 ����11:27:31 xinzhi $
 * */
public class TypeHandlerHolder {

	/**
	 * TypeHandler��Type -> TypeHandler��ʵ����
	 * */
	private static ConcurrentMap<String, TypeHandler> typeHandlerCache = new ConcurrentHashMap<String, TypeHandler>();

	/**
	 * ��TypeHandler��Type�õ�TypeHandler��ʵ����
	 * 
	 * @param type
	 *            TypeHandler��Type��
	 * @return TypeHandler��ʵ����
	 * */
	public static TypeHandler findTypeHandler(String type) {

		if (typeHandlerCache.get(type) == null) {
			try {
				Class<?> c = ClassUtil.forName(type);
				typeHandlerCache.putIfAbsent(type,
						(TypeHandler) c.newInstance());
			} catch (Exception e) {
				throw new SimpleHBaseException(e);
			}
		}
		return typeHandlerCache.get(type);
	}
}
