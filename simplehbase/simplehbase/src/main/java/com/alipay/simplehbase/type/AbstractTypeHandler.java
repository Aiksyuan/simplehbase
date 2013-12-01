package com.alipay.simplehbase.type;

import com.alipay.simplehbase.exception.SimpleHBaseException;

/**
 * TypeHandler�ĹǼ�ʵ�֡�
 * 
 * @author xinzhi
 * @version $Id: AbstractTypeHandler.java 2013-09-11 ����11:27:31 xinzhi $
 * */
abstract public class AbstractTypeHandler implements TypeHandler {

	/**
	 * ��Handler�Ƿ����java��class type��
	 * */
	abstract protected boolean aboutToHandle(Class<?> type);

	abstract protected byte[] innerToBytes(Class<?> type, Object value);

	abstract protected Object innerToObject(Class<?> type, byte[] bytes);

	@Override
	public byte[] toBytes(Class<?> type, Object value) {
		if (!aboutToHandle(type)) {
			throw new SimpleHBaseException("wrong type to handle. type = "
					+ type);
		}

		if (value == null) {
			return null;
		}

		return innerToBytes(type, value);
	}

	@Override
	public Object toObject(Class<?> type, byte[] bytes) {
		if (!aboutToHandle(type)) {
			throw new SimpleHBaseException("wrong type to handle. type = "
					+ type);
		}

		if (bytes == null || bytes.length == 0) {
			return null;
		}

		return innerToObject(type, bytes);
	}

}
