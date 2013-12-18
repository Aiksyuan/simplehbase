package com.alipay.simplehbase.hql;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.alipay.simplehbase.util.Util;
import com.sun.istack.internal.Nullable;

/**
 * һ��hql�Ľڵ㡣
 * 
 * @author xinzhi
 * @version $Id: HQLNode.java 2013-09-11 ����11:27:31 xinzhi $
 * */
abstract public class HQLNode {
    /** BlankSpace�� */
    protected static final String BlankSpace  = " ";

    /** parent hql �ڵ㡣 */
    protected HQLNode             parent;
    /** HQLNodeType�� */
    protected HQLNodeType         hqlNodeType;

    /** �ӽڵ��б� */
    protected List<HQLNode>       subNodeList = new ArrayList<HQLNode>();

    /**
     * Ӧ�ò���map�󣬸ýڵ��HQL�ַ�����
     * 
     * @param para ����map��
     * @param sb StringBuilder������ռ�����
     * @param context Ӧ�ò�������HQL�ַ���ʱ�������ġ�
     * */
    public abstract void applyParaMap(@Nullable Map<String, Object> para,
            StringBuilder sb, Map<Object, Object> context);

    protected HQLNode(HQLNodeType hqlNodeType) {
        Util.checkNull(hqlNodeType);

        this.hqlNodeType = hqlNodeType;
    }

    public void addSubHQLNode(HQLNode hqlNode) {
        subNodeList.add(hqlNode);
    }

    public HQLNode getParent() {
        return parent;
    }

    public void setParent(HQLNode parent) {
        this.parent = parent;
    }

    public HQLNodeType getHqlNodeType() {
        return hqlNodeType;
    }
}
