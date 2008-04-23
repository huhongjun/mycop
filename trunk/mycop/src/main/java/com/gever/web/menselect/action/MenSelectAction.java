package com.gever.web.menselect.action;

import com.gever.sysman.util.BaseAction;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForm;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gever.config.Environment;
import com.gever.exception.db.DAOException;

import javax.servlet.ServletException;
import java.io.IOException;
import com.gever.sysman.privilege.dao.PrivilegeFactory;
import com.gever.sysman.privilege.dao.UserDAO;
import com.gever.util.log.Log;
import com.gever.web.menselect.MenSelectedCatalog;
import java.util.ArrayList;
import java.util.Collection;



public class MenSelectAction extends BaseAction{
	
	private Log mylog;
	
	public ActionForward selectMan(
        ActionMapping mapping,
        ActionForm actionform,
        HttpServletRequest request,
        HttpServletResponse response) throws DAOException, ServletException,
      IOException, ClassNotFoundException, IllegalAccessException,
      InstantiationException {

       String isMutilSelected=request.getParameter("isMutilSelected");
       if(isMutilSelected==null||("").equals(isMutilSelected)){
         isMutilSelected="false";
       }
       String isConfMutil=request.getParameter("isConfMutil");

      if(isConfMutil==null||("").equals(isConfMutil)){
        isConfMutil="false";
      }
       request.setAttribute("isConfMutil",isConfMutil);
       request.setAttribute("isMutilSelected",isMutilSelected);

          PrivilegeFactory factory = PrivilegeFactory.getInstance();
          UserDAO userdao = factory.getUserDAO();
          Collection userlist = (ArrayList) userdao.getUsers();
          //System.out.println(userlist.size());
          request.setAttribute("userlist", userlist);
          String seperator = ",";
          //读取隐藏哪些可选的条件
          String hiddenConfs=(String)request.getParameter("hiddenConf");
          String[] allhiddenConfs = null;
          if(hiddenConfs==null&&("").equals(hiddenConfs)) {
           }else{
        	   allhiddenConfs = hiddenConfs.split(seperator);
           }

          Collection MenSelectedCatalogs=new ArrayList();
          String ImplName = Environment.getProperty("MenSelectedCatalogs");
        //System.out.println("==========ImplName===" + ImplName);
          if(ImplName!=null&&!("").equals(ImplName)){
        	String[]  ImplNames=null;
            ImplNames=ImplName.split(seperator);

           // int m=0;
           //隐藏条件不增加到客户端
           if(allhiddenConfs!=null){
              ArrayList tempImplNames=new ArrayList();
              for(int j=0;j<ImplNames.length;j++){
               // System.out.println("======ImplNames[j]=========="+ImplNames[j]);

                boolean isEqual=false;
                for(int n=0;n<allhiddenConfs.length;n++){
                //  System.out.println("======allhiddenConfs[n]=========="+allhiddenConfs[n]);
                  if(!isEqual&&ImplNames[j].equals(allhiddenConfs[n])){
                  //  System.out.println("999999999999999999999999");
                   isEqual=true;
                   //  m++;
                  }


               }
               if(!isEqual){
                 tempImplNames.add(ImplNames[j]);
               }

              }
              ImplNames=new String[tempImplNames.size()];

             tempImplNames.toArray(ImplNames);
              }
            for(int i=0;i<ImplNames.length;i++){
              String ImplClassNames = Environment.getProperty("MenSelectedCatalog." +
                  ImplNames[i] + ".className");
              MenSelectedCatalog  menSelect = (MenSelectedCatalog ) Class.forName(ImplClassNames).
                  newInstance();
              MenSelectedCatalogs.add(menSelect);
            }
         }

          String selectedIds = (String)request.getParameter("selectedIds");
           String[] allSelectedIds = null;

          if(("").equals(selectedIds)) {
          }else{
          allSelectedIds = selectedIds.split(seperator);
          for (int j = 0; j < allSelectedIds.length; j++) {
           // System.out.println("-----------allselectedIds" + allSelectedIds[j]);
              mylog.console(this.getClass(),"allselectedIds",allSelectedIds[j]);
          }
        }
          request.setAttribute("selectedIds", allSelectedIds);
         // System.out.println("======3=================");
          request.setAttribute("manSelectedCatalogs", MenSelectedCatalogs);
              return mapping.findForward("selectman");
           }
         }

