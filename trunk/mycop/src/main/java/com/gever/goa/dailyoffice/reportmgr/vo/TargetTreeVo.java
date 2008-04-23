package com.gever.goa.dailyoffice.reportmgr.vo;

import com.gever.vo.BaseTreeVO;
import com.gever.vo.VOInterface;


/**
 *
 * <p>Title:目标管理目录树 </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: 天行软件</p>
 * @author Hu.Walker
 * @version 1.0
 */
public class TargetTreeVo extends BaseTreeVO implements VOInterface{
    public TargetTreeVo() {
    }
    public void setOtherProperty(String[] values) {
        super.setSrc(
            "/dailyoffice/reportmgr/treetarget.do?action=doTreeData&#38;nodeid=" +
            this.getNodeid());
        //设置点击父节点后显示其子菜单
        super.setAction("/dailyoffice/reportmgr/listtarget.do?nodeid=" +
                        this.getNodeid());
        //设置点击叶子节点连接到列表页面并且将部门ID传过去
        super.setTarget("middle");
        //设置target
    }

}
