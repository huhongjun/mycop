package com.gever.goa.admin.action;

import javax.servlet.http.HttpServletRequest;

import com.gever.exception.DefaultException;
import com.gever.goa.admin.dao.AdminFactory;
import com.gever.goa.admin.dao.KnowDao;
import com.gever.goa.admin.vo.KnowVO;
import com.gever.jdbc.BaseDAO;
import com.gever.struts.action.BaseAction;
import com.gever.struts.action.GoaActionConfig;

/**
 * <p>Title: ѧ��������</p>
 * <p>Description: ѧ��������</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: �������</p>
 * @author Hu.Walker
 * @version 1.0
 */

public class KnowledgeAction
    extends BaseAction {
  KnowDao knowDao = null;
  public KnowledgeAction() {
  }

  /**
   * ��ʼ��ҳ������
   * @param actionform ��ǰ��vo����
   * @return ��ǰ���õ�vo����
   * @throws DefaultException
   */
  protected void initData(GoaActionConfig cfg) throws DefaultException,
      Exception {

    KnowledgeForm myForm = (KnowledgeForm) cfg.getBaseForm(); //�õ�form����
    knowDao = AdminFactory.getInstance().createKnow(super.dbData); //�õ����Ӧdao��ʵ��
    cfg.setBaseDao( (BaseDAO) knowDao);
    //super.setBaseDao( (BaseDAO) sampleDao); //���ø���dao

    //�ݴ���,��ֹvo����Ϊnull
    if (myForm.getVo() == null) {
      myForm.setVo(new KnowVO());
    }
  }
  protected String doUpdate(GoaActionConfig cfg, boolean isBack) throws
      DefaultException,
      Exception {
    String forward = null;
    HttpServletRequest request = cfg.getRequest();
    KnowledgeForm knowledgeForm = (KnowledgeForm) cfg.getBaseForm();
    KnowVO knowVO = (KnowVO) knowledgeForm.getVo();
    String knowledge_code = knowVO.getKnowledge_code();
    byte[] knowledge_code_byte = knowledge_code.getBytes();
    int knowledge_code_int = knowledge_code_byte.length;
    if (knowledge_code_int > 4) {
      request.setAttribute("ErrorMsg", "ѧ������Ϊ����Ϊ��λ���������룡");
      return FORWORD_EDIT_PAGE;
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


  /**
  * ��������(�������ص���ҳ������)
  * @param cfg ��ǰAction���������
  * @param isBack �Ƿ񷵻�
  * @return forward��ַ
  * @throws DefaultException
  * @throws Exception
  */
 protected String doInsert(GoaActionConfig cfg, boolean isBack) throws
     DefaultException,
     Exception {
   String retforword = null;
   HttpServletRequest request = cfg.getRequest();
   KnowledgeForm knowledgeForm = (KnowledgeForm) cfg.getBaseForm();
   KnowVO knowVO = (KnowVO) knowledgeForm.getVo();
   String knowledge_code = knowVO.getKnowledge_code();
   byte[] knowledge_code_byte = knowledge_code.getBytes();
   int knowledge_code_int = knowledge_code_byte.length;
   if (knowledge_code_int > 4) {
     request.setAttribute("ErrorMsg", "ѧ������Ϊ����Ϊ��λ���������룡");
     return this.FORWORD_EDIT_PAGE;
   }
   try {
     retforword = super.doInsert(cfg, isBack);
   }
   catch (DefaultException e) {
     if (e.getMessage().equals("PK repeat!")) {
       request.setAttribute("ErrorMsg", "ѧ������Ϊ����Ϊ��λ���������룡");
       //throw new DefaultException("ѧ��������ͬ,��������д");
       retforword=super.FORWORD_EDIT_PAGE;
     }
     else {
       throw e;
     }
   }
   return retforword;

 }

}
