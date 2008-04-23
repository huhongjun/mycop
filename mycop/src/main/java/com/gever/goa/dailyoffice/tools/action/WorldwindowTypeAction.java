package com.gever.goa.dailyoffice.tools.action;

import javax.servlet.http.HttpServletRequest;

import com.gever.exception.DefaultException;
import com.gever.goa.dailyoffice.tools.dao.WorldwindowFactory;
import com.gever.goa.dailyoffice.tools.dao.WorldwindowTypeDao;
import com.gever.goa.dailyoffice.tools.vo.WorldwindowTypeVO;
import com.gever.jdbc.BaseDAO;
import com.gever.struts.action.BaseAction;
import com.gever.struts.action.GoaActionConfig;

/**����֮��ģ��
 * <p>Title: </p>
 * <p>Description: GOA</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: �������</p>
 * @author Hu.Walker
 * @version 1.0
 */
public class WorldwindowTypeAction extends BaseAction {
  private WorldwindowTypeDao dao;
 /**
    * ��ʼ��ҳ������
    * @param actionform ��ǰ��vo����
    * @return ��ǰ���õ�vo����
    * @throws DefaultException
    */
   protected void initData(GoaActionConfig cfg) {
      WorldwindowTypeForm myForm = (WorldwindowTypeForm)cfg.getBaseForm(); //�õ�form����
      dao = WorldwindowFactory.getInstance().createWorldwindowTypeDao(super.dbData); //�õ����Ӧdao��ʵ��
      cfg.setBaseDao((BaseDAO)dao);  //���ø���dao

       //�ݴ���,��ֹvo����Ϊnull
       if ( myForm.getVo() == null)
       myForm.setVo(new WorldwindowTypeVO()) ;
       return ;
   }

    /**
    * ��ȡtree������
    * @param cfg ��ǰAction���������
    * @return forward��ַ
    * @throws DefaultException
    * @throws Exception
    */

   public String doTreeData(GoaActionConfig cfg) throws DefaultException,
       Exception {
      cfg.getRequest().setAttribute("treeData", dao.getTreeData());
      log.showLog(dao.getTreeData());
     return TREE_PAGE;
   }

   /**
    * ���Ǹ��෽�� ��request������update_result��־��
    * @param cfg GoaActionConfig
    * @param isBack boolean
    * @throws DefaultException
    * @throws Exception
    * @return String
    */
   public String doUpdate(GoaActionConfig cfg,boolean isBack) throws DefaultException,Exception{
     String forword=null;
     forword=super.doUpdate(cfg,isBack);
      cfg.getSession().setAttribute("operate","update");
     return forword;
   }

   /**
    * ���Ǹ��෽�� ��request������insert_result��־��
    * @param cfg GoaActionConfig
    * @param isBack boolean
    * @throws DefaultException
    * @throws Exception
    * @return String
    */
   public String doInsert(GoaActionConfig cfg,boolean isBack)throws DefaultException,Exception{
     String forword=null;
     forword=super.doInsert(cfg,isBack);
     cfg.getSession().setAttribute("operate","insert");
     return forword;
   }
   /**
    * ���Ǹ��෽������request������delete_resultֵ
    * @param cfg GoaActionConfig
    * @throws DefaultException
    * @throws Exception
    * @return String
    */
   public String doDelete(GoaActionConfig cfg)throws DefaultException,Exception{
     String forword=null;
     HttpServletRequest request=cfg.getRequest();
     cfg.getRequest().setAttribute("deleteIDs",cfg.getRequest().getParameterValues("fid"));
     try{
       forword = super.doDelete(cfg);
       cfg.getSession().setAttribute("operate", "delete");
       cfg.getSession().setAttribute("operateID",
                                     cfg.getRequest().getParameterValues("fid"));
     }
     catch(DefaultException e){
       if(e.getMessage().equals("PK")){
         request.setAttribute("ErrorMsg","��������¼��б�����ɾ���¼��б�");
         return this.FORWORD_LIST_PAGE;
       }
     }
     return forword;
   }

   protected String toList(GoaActionConfig cfg) throws DefaultException,
            Exception {
        WorldwindowTypeForm form = (WorldwindowTypeForm) cfg.getBaseForm();
        String sqlWhere = "";
        form.setSqlWhere(sqlWhere);
        return super.toList(cfg);
    }


}


