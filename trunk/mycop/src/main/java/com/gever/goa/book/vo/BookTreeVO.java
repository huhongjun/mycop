package com.gever.goa.book.vo;

import com.gever.vo.BaseTreeVO;
import com.gever.vo.VOInterface;

/**
 * XLoadTree xml�ļ����ɵ����ݿ��ȡVO��
 * 
 * 
 * 
 */

public class BookTreeVO extends BaseTreeVO implements VOInterface{
    public BookTreeVO() {
    }

		    // ��DefaultSQLHelper�У����ÿһvo��ִ�и÷�����Ȼ����䷵�صĽ������
    public void setOtherProperty(String[] values) {
        //���õ�����ڵ����ʾ���Ӳ˵�����ҪtreeAction��doTreeData��DAO���б���֧��[����ʱ��̬����]
        super.setSrc("/Book/treeBook.do?action=doTreeData&#38;nodeid=" + this.getNodeid());
    	    //���ýڵ㶯��
        super.setAction("/Book/listBook.do?nodeid=" + this.getNodeid());

        //super.setIsfolder("0");        //�趨ΪҶ�ӽڵ㣬�����ܴ����ݿ��õ�ֵ
        super.setTarget("middle");     //����action��frameĿ�괰��
    }
}