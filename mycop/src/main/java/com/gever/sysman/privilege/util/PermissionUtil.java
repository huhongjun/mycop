package com.gever.sysman.privilege.util;

import com.gever.sysman.privilege.model.I_User;
import com.gever.sysman.privilege.model.Operation;
import com.gever.sysman.privilege.model.Resource;
import com.gever.sysman.privilege.model.UserPermission;

import java.util.Collection;

import javax.servlet.http.HttpSession;


/**
 * Title��Ȩ�޼�⹤����
 * Desc: �û�-��Դ-����
 * 
 * @author 
 * @version 1.0
 */
public class PermissionUtil {

	private PermissionUtil() {    }

    /**
     * �ж��û��Ƿ�����ӦȨ��
     * desc��optid��optcode��resid��rescode�Ĳ�ͬ���Ķ�
     * @param I_User user �û�
     * @param Resource resource ��Դ
     * @param Operation operation ����
     * @return boolean
     * 
     * @todo û��ʵ�ִ���
     */
    private static boolean hasPermission(I_User user, Resource resource,
        Operation operation) {
       
    	return true;
    }

    /**
     * �жϵ�ǰ�û���ָ����Դ����������ţ���Ȩ��
     * @param session	������ȡ��ǰ�û���Ȩ�޼���
     * @param optid		��������
     * @param resid		��Դ����
     * @return
     */
    public static boolean checkPermissionById(HttpSession session,String optid, String resid) {
        Collection permissions = (Collection) session.getAttribute(Constants.USER_PERMISSION);
        resid = resid.trim();
        optid = optid.trim();
        
        if ((permissions==null)||(permissions.size()==0))
            return false;
        if ((optid==null)||(resid==null))
            return false;
        if ((optid.equals(""))||(resid.equals("")))
            return false;
        for (java.util.Iterator it = permissions.iterator(); it.hasNext();) {
            UserPermission userperm = (UserPermission) it.next();
            if ((userperm.getResop_id()==Long.parseLong(optid)) && (userperm.getResource_id()==Long.parseLong(resid)))
             {

               return true;
            }
        }
        return false;

    }

    /**
     * �жϵ�ǰ�û���ָ����Դ������(����ָ��)��Ȩ��
     * @param session	������ȡ��ǰ�û���Ȩ�޼���
     * @param optcode	��������
     * @param rescode	��Դ����
     * @return
     */

    public static boolean checkPermissionByCode(HttpSession session,String optcode, String rescode) {
        Collection permissions = (Collection) session.getAttribute(Constants.USER_PERMISSION);
        rescode = rescode.trim();
        optcode = optcode.trim();
        if ((permissions==null)||(permissions.size()==0))
            return false;
          if ((optcode==null)||(rescode==null))
              return false;
        for (java.util.Iterator it = permissions.iterator(); it.hasNext();) {
            UserPermission userperm = (UserPermission) it.next();
             if ((userperm.getOpt_code().equals(optcode)) && (userperm.getRes_code().equals(rescode)))
             {
                return true;
            }
        }

        return false;
    }

    public static boolean checkPermissionByCode(HttpSession session, String rescode) {
        Collection permissions = (Collection) session.getAttribute(Constants.USER_PERMISSION);
        rescode = rescode.trim();
        if ((permissions==null)||(permissions.size()==0))
            return false;
        for (java.util.Iterator it = permissions.iterator(); it.hasNext();) {
            UserPermission userperm = (UserPermission) it.next();

            if ((userperm.getRes_code().equals(rescode)))
             {
                return true;
            }
        }

        return false;
    }

    public static boolean checkPermissionById(HttpSession session, String resid) {
        Collection permissions = (Collection) session.getAttribute(Constants.USER_PERMISSION);
        resid = resid.trim();
        if ((permissions==null)||(permissions.size()==0))
            return false;
        for (java.util.Iterator it = permissions.iterator(); it.hasNext();) {
            UserPermission userperm = (UserPermission) it.next();

            if ((userperm.getResource_id()==Long.parseLong(resid)))
             {
                return true;
            }
        }

        return false;
    }



}
