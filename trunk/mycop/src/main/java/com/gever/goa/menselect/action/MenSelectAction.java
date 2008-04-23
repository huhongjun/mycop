package com.gever.goa.menselect.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.gever.exception.DefaultException;
import com.gever.goa.dailyoffice.smsmgr.dao.SmsFactory;
import com.gever.goa.dailyoffice.smsmgr.dao.SmsMgrDAO;
import com.gever.goa.infoservice.dao.IsAddressListDao;
import com.gever.goa.infoservice.dao.IsCustomerFactory;
import com.gever.goa.menselect.dao.MenSelectDAO;
import com.gever.goa.menselect.dao.MenSelectFactory;
import com.gever.goa.menselect.vo.MenSelectVO;
import com.gever.jdbc.BaseDAO;
import com.gever.struts.action.BaseAction;
import com.gever.struts.action.GoaActionConfig;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: 天行软件</p>
 * @author Hu.Walker
 * @version 1.0
 */

public class MenSelectAction extends BaseAction {
    MenSelectDAO menSelectDao = null;
    IsAddressListDao isAddressListDao = null;
   public MenSelectAction() {
   }

   private static final String DEPT_FORWARD = "dept";
   private static final String THEAM_FORWARD="theam";

    protected void initData(GoaActionConfig cfg) throws DefaultException,
        Exception {

        MenSelectForm myForm = (MenSelectForm) cfg.getBaseForm(); //得到form变量
        //testDao = new TestDAOImp(super.dbData);
        menSelectDao = MenSelectFactory.getInstance().createMenSelect(super.dbData);
        cfg.setBaseDao( (BaseDAO) menSelectDao);
        //super.setBaseDao( (BaseDAO) sampleDao); //设置父类dao

        //容错处理,防止vo对象为null
//        if (myForm.getVo() == null)
//            myForm.setVo(new MemSelectVO());
    }

    public String initUserArray(GoaActionConfig cfg) {
        MenSelectForm form = (MenSelectForm)cfg.getBaseForm();
        form.setInitUserInfoArray(initUserInfoArray());
        return super.FORWORD_LIST_PAGE;
    }


    public String toList(GoaActionConfig cfg) throws DefaultException,
        Exception {
        //System.out.println("---- in toList in MenSelectAction ----");
       MenSelectForm form = (MenSelectForm)cfg.getBaseForm();
       HttpSession session = cfg.getSession();
       HttpServletRequest request = cfg.getRequest();

       form.setInitUserInfoArray(initUserInfoArray());
       //form.setInitUserInfoArray(menSelectDao.getDeptInfoArray());
       //ArrayList bmList = menSelectDao.getConstCode("T_DEPARTMENT","ID","Name","1=1");
       setIsAddressListDao();
       ArrayList bmList = menSelectDao.getDeptArray();
       ArrayList zwList = menSelectDao.getConstCode("T_JOB", "ID", "Name",
           "1=1");
       List addressList = isAddressListDao.queryAddressById(form.getUserId());//menSelectDao.getAddressList(form.getUserId());
       session.setAttribute("bmList",bmList);
       session.setAttribute("zwList",zwList);
       session.setAttribute("addressList",addressList);
       return this.FORWORD_LIST_PAGE;
    }

    private void setIsAddressListDao() {
        isAddressListDao = IsCustomerFactory.getInstance().createIsAddressList(super.dbData);
    }

    public String toDept(GoaActionConfig cfg) throws DefaultException,
        Exception {
       MenSelectForm form = (MenSelectForm)cfg.getBaseForm();
       form.setInitUserInfoArray(menSelectDao.getDeptInfoArray());
        return this.DEPT_FORWARD;
    }

	public String toTheam(GoaActionConfig cfg) throws DefaultException,
		Exception {
	   MenSelectForm form = (MenSelectForm)cfg.getBaseForm();
	   form.setInitUserInfoArray(menSelectDao.getTheamArray());
		return this.THEAM_FORWARD;
	}


    public String toOutList(GoaActionConfig cfg) throws DefaultException,
        Exception {
       // System.out.println("---- in toOutList in MenSelectAction ----");
       MenSelectForm form = (MenSelectForm)cfg.getBaseForm();
       HttpSession session = cfg.getSession();
       //HttpServletRequest request = cfg.getRequest();

       form.setInitUserInfoArray(initCardUserInfoArray());
       SmsMgrDAO smsMgrDao = SmsFactory.getInstance().createSmsMgrDAO(super.dbData);
       List cardSelectList = smsMgrDao.queryCardTypeByUserID(form.getUserId());

       session.setAttribute("cardSelectList",cardSelectList);
       return this.FORWORD_LIST_PAGE;
    }

    private String initUserInfoArray() {
        String userInfo = "";
        ArrayList users = new ArrayList();
        users = menSelectDao.getUsersInfo();
        String initArray = "	var userArray = new Array();\n";
        for (int i = 0; i < users.size(); i++) {
            userInfo += "	userArray[" + i + "] = new User(\"" +
                ( (MenSelectVO) users.get(i)).getName() + "\",\""
                + ( (MenSelectVO) users.get(i)).getId() + "\",\"" +
                ( (MenSelectVO) users.get(i)).getDeptid() + "\",\"" +
                ( (MenSelectVO) users.get(i)).getJobid() + "\");\n";
        }
        return initArray + userInfo;
    }

    private String initCardUserInfoArray() throws DefaultException {
        String userInfo = "";
        ArrayList users = new ArrayList();
        users = menSelectDao.getCardUsersInfo();
        String initArray = "	var userArray = new Array();\n";
       // System.out.println("=users==="+users.size());
        for (int i = 0; i < users.size(); i++) {
            userInfo += "	userArray[" + i + "] = new User(\"" +
                ( (MenSelectVO) users.get(i)).getName() + "\",\""
                + ( (MenSelectVO) users.get(i)).getId() + "\",\"" +
                ( (MenSelectVO) users.get(i)).getDeptid() + "\",\"" +
                ( (MenSelectVO) users.get(i)).getJobid() + "\");\n";
        }
        return initArray + userInfo;
    }


}
