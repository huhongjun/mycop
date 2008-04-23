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
 * <p>Title: 学历控制器</p>
 * <p>Description: 学历控制器</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: 天行软件</p>
 * @author Hu.Walker
 * @version 1.0
 */

public class KnowledgeAction
    extends BaseAction {
  KnowDao knowDao = null;
  public KnowledgeAction() {
  }

  /**
   * 初始化页面数据
   * @param actionform 当前的vo对象
   * @return 当前所用的vo对象
   * @throws DefaultException
   */
  protected void initData(GoaActionConfig cfg) throws DefaultException,
      Exception {

    KnowledgeForm myForm = (KnowledgeForm) cfg.getBaseForm(); //得到form变量
    knowDao = AdminFactory.getInstance().createKnow(super.dbData); //得到相对应dao的实例
    cfg.setBaseDao( (BaseDAO) knowDao);
    //super.setBaseDao( (BaseDAO) sampleDao); //设置父类dao

    //容错处理,防止vo对象为null
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
      request.setAttribute("ErrorMsg", "学历代码为汉字为两位，重新输入！");
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
  * 新增动作(继续返回到本页面新增)
  * @param cfg 当前Action相关配置类
  * @param isBack 是否返回
  * @return forward地址
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
     request.setAttribute("ErrorMsg", "学历代码为汉字为两位，重新输入！");
     return this.FORWORD_EDIT_PAGE;
   }
   try {
     retforword = super.doInsert(cfg, isBack);
   }
   catch (DefaultException e) {
     if (e.getMessage().equals("PK repeat!")) {
       request.setAttribute("ErrorMsg", "学历代码为汉字为两位，重新输入！");
       //throw new DefaultException("学历代码相同,请重新填写");
       retforword=super.FORWORD_EDIT_PAGE;
     }
     else {
       throw e;
     }
   }
   return retforword;

 }

}
