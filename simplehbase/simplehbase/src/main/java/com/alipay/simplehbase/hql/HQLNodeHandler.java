package com.alipay.simplehbase.hql;

import org.w3c.dom.Node;

/**
 * HQLNodeHandler�������NodeΪHQLNode��
 * 
 * 
 * @author xinzhi
 * @version $Id: HQLNodeHandler.java 2013-09-11 ����11:27:31 xinzhi $
 * */
public interface HQLNodeHandler {

    /**
     * ����NodeΪHQLNode��
     * */
    public HQLNode handle(Node node);
}
