package com.gever.goa.user.vo;

import com.gever.vo.BaseTreeVO;
import com.gever.vo.VOInterface;

/**
 * XLoadTree xml�ļ����ɵ����ݿ��ȡVO��
 * 
 * 
 * 
 */

public class UserTreeVO extends BaseTreeVO implements VOInterface{
    public UserTreeVO() {
    }

		    // ��DefaultSQLHelper�У����ÿһvo��ִ�и÷�����Ȼ����䷵�صĽ������
    public void setOtherProperty(String[] values) {
        //���õ�����ڵ����ʾ���Ӳ˵�����ҪtreeAction��doTreeData��DAO���б���֧��[����ʱ��̬����]
        super.setSrc("/User/treeUser.do?action=doTreeData&#38;nodeid=" + this.getNodeid());
    	    //���ýڵ㶯��
        super.setAction("/User/listUser.do?nodeid=" + this.getNodeid());

        //super.setIsfolder("0");        //�趨ΪҶ�ӽڵ㣬�����ܴ����ݿ��õ�ֵ
        super.setTarget("middle");     //����action��frameĿ�괰��
    }
}