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
 * <p>Title: ����������</p>
 * <p>Description: ����������</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: �������</p>
 * @author Hu.Walker
 * @version 1.0
 */

public class MarriageAction extends BaseAction {
    MarriageDao marriageDao = null;
    public MarriageAction() {
    }

    /**
     * ��ʼ��ҳ������
     * @param actionform ��ǰ��vo����
     * @return ��ǰ���õ�vo����
     * @throws DefaultException
     */
    protected void initData(GoaActionConfig cfg) throws DefaultException,
        Exception {

        MarriageForm myForm = (MarriageForm) cfg.getBaseForm(); //�õ�form����
        marriageDao = AdminFactory.getInstance().createMarriage(super.dbData); //�õ����Ӧdao��ʵ��
        cfg.setBaseDao( (BaseDAO) marriageDao);
        //super.setBaseDao( (BaseDAO) sampleDao); //���ø���dao

        //�ݴ���,��ֹvo����Ϊnull
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
         request.setAttribute("ErrorMsg", "���������Ǻ���Ϊ��λ���������룡");
         return this.FORWORD_EDIT_PAGE;
       }
       try{
         refoword=super.doInsert(cfg,isBack);
       }
       catch(DefaultException e){
         if(e.getMessage().equals("pk")){
          // throw new DefaultException("���������ظ�,��������д!");
           request.setAttribute("ErrorMsg", "���������ظ�,��������д!");
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
         request.setAttribute("ErrorMsg", "���������Ǻ���Ϊ��λ���������룡");
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
