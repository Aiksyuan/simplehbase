package com.alipay.simplehbase.type;

/**
 * TypeHandler�ض���java��class type,�����hbase��صĸ�ʽת���ȹ�����
 * 
 * <pre>
 * һ����ԣ�һ��TypeHandler����һ��java class��
 * ����װ�����Ϳ��Էŵ�ͬһ��TypeHandler���档
 * </pre>
 * 
 * @author xinzhi
 * @version $Id: TypeHandler.java 2013-09-11 ����11:27:31 xinzhi $
 * */
public interface TypeHandler {

	/**
	 * ת��valueΪhbase����ֵ��
	 * 
	 * @param type
	 *            java��type��
	 * @param value
	 *            java��value��
	 * @return hbase����ֵ��
	 * */
	public byte[] toBytes(Class<?> type, Object value);

	/**
	 * ת��hbase����ֵΪjava��object��
	 * 
	 * @param type
	 *            java��type��
	 * @param bytes
	 *            hbase����ֵ��
	 * @return java��value��
	 * */
	public Object toObject(Class<?> type, byte[] bytes);
}
