package com.gever.goa.infoservice.action;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.gever.exception.DefaultException;
import com.gever.goa.infoservice.dao.IsAddressListDao;
import com.gever.goa.infoservice.dao.IsCustomerFactory;
import com.gever.goa.infoservice.vo.IsAddressListVO;
import com.gever.jdbc.BaseDAO;
import com.gever.struts.action.BaseAction;
import com.gever.struts.action.GoaActionConfig;
import com.gever.sysman.api.OrganizationUtil;
import com.gever.util.StringUtils;
import com.gever.vo.BaseTreeVO;


/**
 * <p>Title: 群组设置Action</p>
 * <p>Description: KOBE OFFICE 版权所有，违者必究！</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: KOBE OFFICE</p>
 * @author Hu.Walker
 * @version 1.0
 */
public class IsAddressListAction extends BaseAction {
    private IsAddressListDao isAddressListDao = null;

    public IsAddressListAction() {
    }

    protected void initData(GoaActionConfig cfg)
        throws DefaultException, Exception {
        IsAddressListForm myForm = (IsAddressListForm) cfg.getBaseForm();
        isAddressListDao = IsCustomerFactory.getInstance().createIsAddressList(super.dbData);
        cfg.setBaseDao((BaseDAO) isAddressListDao);

        if (myForm.getVo() == null) {
            myForm.setVo(new IsAddressListVO());
        }

        this.setVoSql(false);
    }

    protected String toList(GoaActionConfig cfg)
        throws DefaultException, Exception {
        IsAddressListForm form = (IsAddressListForm) cfg.getBaseForm();
        String paraflag = form.getParaFlag();
        int localid = Integer.parseInt(cfg.getBaseForm().getUserId());
        String paraSimpleQuery = form.getParaSimpleQuery();
        paraSimpleQuery = StringUtils.replaceText(paraSimpleQuery);

        if (paraSimpleQuery == null) {
            paraSimpleQuery = "";
        }

        if (paraflag == null) {
            paraflag = "0";
            form.setParaFlag("0");
        }


        //当paraflag＝0的时候列出自己的群组，当paraflag不为0的时候列出公共群组
        if ("0".equals(paraflag)) {
            cfg.getBaseForm().setSqlWhere(" AND IS_ADDRESS_LIST.GROUP_NAME LIKE '%" +
                paraSimpleQuery + "%'" + " AND USER_CODE=" + localid +
                " and is_address_list.type_flag=" + paraflag);
        } else {
            cfg.getBaseForm().setSqlWhere(" AND IS_ADDRESS_LIST.GROUP_NAME LIKE '%" +
                paraSimpleQuery + "%'" + " and is_address_list.type_flag=" +
                paraflag);
        }

        String strForward = super.toList(cfg);

        List dataList = (List) cfg.getBaseForm().getPageControl().getData();
        Iterator it = dataList.iterator();
        List displayList = new ArrayList();

        IsAddressListVO ialVO = null;

        while (it.hasNext()) {
            ialVO = (IsAddressListVO) it.next();

            String userNames = "";
            OrganizationUtil util = new OrganizationUtil();
            userNames = util.getUserNamesByUserdIDs(ialVO.getMember());
            userNames = userNames.trim();
            ialVO.setMemberNames(userNames);
            displayList.add(ialVO);
        }

        cfg.getBaseForm().getPageControl().setData(displayList);
        ((IsAddressListForm) cfg.getBaseForm()).setParaSimpleQuery("");

        return strForward;
    }

    protected String toEdit(GoaActionConfig cfg)
        throws DefaultException, Exception {
        String strForward = super.toEdit(cfg);
        IsAddressListForm form = (IsAddressListForm) cfg.getBaseForm();
        IsAddressListVO ialVO = ((IsAddressListVO) cfg.getBaseForm().getVo());
        String userNames = "";
        String paraFlag = form.getParaFlag();

        if ((ialVO.getMember() != null) && !ialVO.getMember().equals("")) {
            OrganizationUtil util = new OrganizationUtil();
            userNames = util.getUserNamesByUserdIDs(ialVO.getMember());
            userNames = userNames.trim();
            ((IsAddressListVO) cfg.getBaseForm().getVo()).setMemberNames(userNames);
        }

        return strForward;
    }

    protected String doPage(GoaActionConfig cfg, String pageType)
        throws DefaultException, Exception {
        String strForward = super.doPage(cfg, pageType);
        IsAddressListForm form = (IsAddressListForm) cfg.getBaseForm();
        String paraflag = form.getParaFlag();

        if (paraflag == null) {
            paraflag = "0";
            form.setParaFlag("0");
        }

        List dataList = (List) cfg.getBaseForm().getPageControl().getData();
        Iterator it = dataList.iterator();
        List displayList = new ArrayList();

        IsAddressListVO ialVO = null;

        while (it.hasNext()) {
            ialVO = (IsAddressListVO) it.next();

            String userNames = "";
            OrganizationUtil util = new OrganizationUtil();
            userNames = util.getUserNamesByUserdIDs(ialVO.getMember());
            userNames = userNames.trim();
            ialVO.setMemberNames(userNames);
            displayList.add(ialVO);
        }

        cfg.getBaseForm().getPageControl().setData(displayList);

        return this.FORWORD_LIST_PAGE;
    }

    protected String toView(GoaActionConfig cfg)
        throws DefaultException, Exception {
        String strForward = super.toView(cfg);

        IsAddressListVO ialVO = ((IsAddressListVO) cfg.getBaseForm().getVo());

        if ((ialVO.getMember() != null) && !ialVO.getMember().equals("")) {
            String userNames = "";
            OrganizationUtil util = new OrganizationUtil();
            userNames = util.getUserNamesByUserdIDs(ialVO.getMember());
            userNames = userNames.trim();
            ((IsAddressListVO) cfg.getBaseForm().getVo()).setMemberNames(userNames);
        }

        return strForward;
    }

    public String doStaticTreeData(GoaActionConfig cfg)
        throws DefaultException, Exception {
        BaseTreeVO staticVO1 = new BaseTreeVO();
        staticVO1.setNodeid("1");
        staticVO1.setNodename("我的群组");
        staticVO1.setAction("/infoservice/grouplist.do?paraFlag=0");
        staticVO1.setIsfolder("0");
        staticVO1.setSrc("");

        BaseTreeVO staticVO2 = new BaseTreeVO();
        staticVO2.setNodeid("2");
        staticVO2.setAction("/infoservice/grouplist.do?paraFlag=1");
        staticVO2.setNodename("公共群组");
        staticVO2.setIsfolder("0");
        staticVO2.setSrc("");

        List statictreeData = new ArrayList();
        statictreeData.add(staticVO1);
        statictreeData.add(staticVO2);

        cfg.getRequest().setAttribute("treeData", statictreeData);

        return TREE_PAGE;
    }

    /**
     * 重载doinsert方法
     */
    protected String doInsert(GoaActionConfig cfg, boolean isBack)
        throws DefaultException, Exception {
        IsAddressListForm form = (IsAddressListForm) cfg.getBaseForm();
        IsAddressListVO vo = (IsAddressListVO) form.getVo();
        String paraflag = form.getParaFlag();

        if ("1".equals(paraflag)) {
            vo.setType_flag(paraflag);
        } else {
            vo.setType_flag(paraflag);
        }

        String forward = super.doInsert(cfg, isBack);

        return forward;
    }
}
