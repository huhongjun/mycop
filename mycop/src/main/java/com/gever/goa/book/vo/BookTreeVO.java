package com.gever.goa.book.vo;

import com.gever.vo.BaseTreeVO;
import com.gever.vo.VOInterface;

/**
 * XLoadTree xml文件生成的数据库存取VO类
 * 
 * 
 * 
 */

public class BookTreeVO extends BaseTreeVO implements VOInterface{
    public BookTreeVO() {
    }

		    // 在DefaultSQLHelper中，获得每一vo后执行该方法，然后填充返回的结果数组
    public void setOtherProperty(String[] values) {
        //设置点击父节点后显示其子菜单，需要treeAction的doTreeData、DAO的中编码支持[运行时动态加载]
        super.setSrc("/Book/treeBook.do?action=doTreeData&#38;nodeid=" + this.getNodeid());
    	    //设置节点动作
        super.setAction("/Book/listBook.do?nodeid=" + this.getNodeid());

        //super.setIsfolder("0");        //设定为叶子节点，即不管从数据库获得的值
        super.setTarget("middle");     //设置action的frame目标窗口
    }
}