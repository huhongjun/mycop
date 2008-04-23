package com.gever.goa.admin.action;

import javax.servlet.http.HttpServletRequest;

import com.gever.exception.DefaultException;
import com.gever.goa.admin.dao.AdminFactory;
import com.gever.goa.admin.dao.MarriageDao;
import com.gever.goa.admin.vo.MarriageVO;
import com.gever.jdbc.BaseDAO;
import com.gever.struts.action.BaseAction;
import com.gever.struts.action.GoaActionConfig;

/**
 * <p>Title: 婚姻控制器</p>
 * <p>Description: 婚姻控制器</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: 天行软件</p>
 * @author Hu.Walker
 * @version 1.0
 */

public class MarriageAction extends BaseAction {
    MarriageDao marriageDao = null;
    public MarriageAction() {
    }

    /**
     * 初始化页面数据
     * @param actionform 当前的vo对象
     * @return 当前所用的vo对象
     * @throws DefaultException
     */
    protected void initData(GoaActionConfig cfg) throws DefaultException,
        Exception {

        MarriageForm myForm = (MarriageForm) cfg.getBaseForm(); //得到form变量
        marriageDao = AdminFactory.getInstance().createMarriage(super.dbData); //得到相对应dao的实例
        cfg.setBaseDao( (BaseDAO) marriageDao);
        //super.setBaseDao( (BaseDAO) sampleDao); //设置父类dao

        //容错处理,防止vo对象为null
        if (myForm.getVo() == null)
            myForm.setVo(new MarriageVO());
    }

    protected String doInsert(GoaActionConfig cfg,boolean isBack) throws DefaultException,
         Exception{
       String refoword=null;
       HttpServletRequest request=cfg.getRequest();
       MarriageForm marriageForm=(MarriageForm)cfg.getBaseForm();
       MarriageVO marriageVO=(MarriageVO)marriageForm.getVo();
       String marriage_code=marriageVO.getMarriage_code();
       byte[] marriage_code_byte=marriage_code.getBytes();
       int marriage_int=marriage_code_byte.length;
       if(marriage_int>4){
         request.setAttribute("ErrorMsg", "婚姻代码是汉字为两位，重新输入！");
         return this.FORWORD_EDIT_PAGE;
       }
       try{
         refoword=super.doInsert(cfg,isBack);
       }
       catch(DefaultException e){
         if(e.getMessage().equals("pk")){
          // throw new DefaultException("婚姻代码重复,请重新填写!");
           request.setAttribute("ErrorMsg", "婚姻代码重复,请重新填写!");
         return this.FORWORD_EDIT_PAGE;
         }else{
           throw e;
         }
       }
       return refoword;
     }
protected String doUpdate(GoaActionConfig cfg,boolean isBack) throws
     DefaultException,Exception{
       String forward = null;
       HttpServletRequest request = cfg.getRequest();
       MarriageForm marriageForm = (MarriageForm) cfg.getBaseForm();
       MarriageVO marriageVO = (MarriageVO) marriageForm.getVo();
       String marriage_code = marriageVO.getMarriage_code();
       byte[] marriage_code_byte = marriage_code.getBytes();
       int marriage_int = marriage_code_byte.length;
       if (marriage_int > 4) {
         request.setAttribute("ErrorMsg", "婚姻代码是汉字为两位，重新输入！");
         return this.FORWORD_EDIT_PAGE;
       }
       try{
      forward = super.doUpdate(cfg, isBack);
    }
    catch (DefaultException e) {
      if (e.getMessage().equals("PK")) {
        return this.FORWORD_LIST_PAGE;
      }
      else throw e;
    }
    return forward;
  }


}
