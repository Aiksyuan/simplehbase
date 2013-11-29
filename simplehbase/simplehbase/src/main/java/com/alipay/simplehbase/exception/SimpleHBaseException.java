package com.alipay.simplehbase.exception;

/**
 * SimpleHbase��ͨ���쳣��
 * 
 * <pre>
 * ����simplehbase��ܵ��쳣��Ӧ�ü̳д��ࡣ
 * </pre>
 * 
 * @author xinzhi
 * @version $Id: SimpleHBaseException.java 2013-09-11 ����11:27:31 xinzhi $
 * */
public class SimpleHBaseException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public SimpleHBaseException(String message) {
        super(message);
    }

    public SimpleHBaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public SimpleHBaseException(Throwable cause) {
        super(cause);
    }
}
