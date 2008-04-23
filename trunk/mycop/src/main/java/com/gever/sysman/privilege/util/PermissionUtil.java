package com.gever.sysman.privilege.util;

import com.gever.sysman.privilege.model.I_User;
import com.gever.sysman.privilege.model.Operation;
import com.gever.sysman.privilege.model.Resource;
import com.gever.sysman.privilege.model.UserPermission;

import java.util.Collection;

import javax.servlet.http.HttpSession;


/**
 * Title：权限检测工具类
 * Desc: 用户-资源-操作
 * 
 * @author 
 * @version 1.0
 */
public class PermissionUtil {

	private PermissionUtil() {    }

    /**
     * 判断用户是否有相应权限
     * desc：optid和optcode、resid和rescode的不同在哪儿
     * @param I_User user 用户
     * @param Resource resource 资源
     * @param Operation operation 操作
     * @return boolean
     * 
     * @todo 没有实现代码
     */
    private static boolean hasPermission(I_User user, Resource resource,
        Operation operation) {
       
    	return true;
    }

    /**
     * 判断当前用户对指定资源、操作（序号）的权限
     * @param session	从中提取当前用户的权限集合
     * @param optid		操作编码
     * @param resid		资源编码
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
     * 判断当前用户对指定资源、操作(代码指定)的权限
     * @param session	从中提取当前用户的权限集合
     * @param optcode	操作代码
     * @param rescode	资源代码
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
