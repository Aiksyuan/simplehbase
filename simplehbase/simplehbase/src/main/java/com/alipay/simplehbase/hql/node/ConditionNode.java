package com.alipay.simplehbase.hql.node;

import java.util.Map;

import com.alipay.simplehbase.hql.HQLNode;
import com.alipay.simplehbase.hql.HQLNodeType;
import com.alipay.simplehbase.util.StringUtil;

abstract public class ConditionNode extends PrependNode {

    protected ConditionNode(HQLNodeType hqlNodeType) {
        super(hqlNodeType);
    }

    /**
     * �����Ƿ����㡣
     * */
    abstract protected boolean isConditionSatisfied(Map<String, Object> para);

    @Override
    final public void applyParaMap(Map<String, Object> para, StringBuilder sb,
            Map<Object, Object> context) {
        if (isConditionSatisfied(para)) {
            //�����dynamicNode���ӽڵ㣬���һ����������ʱ����dynamicNode�ķǿ�prepend���ǵ�ǰ��prepend��
            String prepend = getPrependValue();

            HQLNode parent = this.getParent();
            if (parent != null
                    && parent.getHqlNodeType() == HQLNodeType.Dynamic
                    && !context.containsKey(parent)) {

                context.put(parent, "override prepend once");

                String parentPrepend = ((DynamicNode) parent).getPrependValue();
                if (StringUtil.isNotEmptyString(parentPrepend)) {
                    prepend = parentPrepend;
                }
            }

            if (prepend != null) {
                sb.append(BlankSpace);
                sb.append(prepend);
            }

            for (HQLNode hqlNode : subNodeList) {
                hqlNode.applyParaMap(para, sb, context);
            }
        }
    }
}
