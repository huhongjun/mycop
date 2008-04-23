package com.gever.goa.web.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.PageContext;

import com.gever.struts.Constant;
import com.gever.sysman.privilege.model.UserPermission;
import com.gever.util.StringUtils;
import com.gever.util.log.Log;
import com.gever.vo.VOInterface;

/**
 * <p>Title: 获取多笔数据修改</p>
 * <p>Description: 获取多笔数据修改</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: </p>
 * @author 
 * @version 1.0
 */

public class RequestUtils {
    Log log = Log.getInstance(RequestUtils.class);
    public RequestUtils() {
    }

    /**
     * 得到页面多笔操作数据(适合多笔修改)
     * @param request HttpServletRequest
     * @param vo 当前的vo对象
     * @param columns 字段名称
     * @return 多笔数据资料
     */
    public List requestValues(HttpServletRequest request,
                              VOInterface vo, String columns) {
        StringTokenizer stk = new StringTokenizer(columns, ",");
        int count = stk.countTokens();
        String[] names = new String[count];
        if (count == 0)
            return new ArrayList();
        String fldName = "";
        String[] value = new String[count];
        int next = 0;
        List aryData = new ArrayList();

        //得到所有的列名
        while (stk.hasMoreTokens()) {
            names[next++] = stk.nextToken();
        }

        //然后将数据一个个读入到vo,再封装list对象当中
        int rowCount = request.getParameterValues(names[0]).length;
        for (int row = 0; row < rowCount; row++) {
            VOInterface curVo = this.getInstanceVO(vo);
            for (int col = 0; col < names.length; col++) {
                curVo.setValue(names[col],
                               request.getParameterValues(names[col])[row]);
            }
            aryData.add(curVo);
        }
        return aryData;
    }

    /**
     * 获取VO实例化对象
     * @param vo VO对象
     * @return ObjectView
     */
    public VOInterface getInstanceVO(VOInterface vo) {
        VOInterface valueObject = null;
        String logMethod = "getInstanceVO(vo)";
        try {
            valueObject = (VOInterface) Class.forName(vo.getClass().getName()).
                newInstance();
        } catch (InstantiationException e) {
            log.showLog(logMethod + e.getMessage());
        } catch (IllegalAccessException e) {
            log.showLog(logMethod + e.getMessage());
        } catch (ClassNotFoundException e) {
            log.showLog(logMethod + e.getMessage());
        }

        return valueObject;
    }

    /**
     * 得到css的样式
     * @param pageContext jsp的容器
     * @return 路径
     */
    public static String getCSSPath(PageContext pageContext) {
        HttpServletRequest request = (HttpServletRequest) pageContext.
            getRequest();
        String path = (String) request.getSession().getAttribute(Constant.
            CSS_PATH);
        if (StringUtils.isNull(path)) {
        	Log.getInstance().console(RequestUtils.class,"->getCSSPath()","Default:/css/goa.css");
            String defaultCssPath = request.getContextPath() + "/css/goa.css";
            return defaultCssPath;
        }
        return path;
    }

    /**
     * 得到gdp的权限
     * @param session session对象
     * @param optcode 操作级
     * @param rescode 资源数据
     * @return 是否有权限
     */
    public static boolean checkPermissionByCode(HttpSession session,
                                                String optcode, String rescode) {
        Collection permissions = (Collection) session.getAttribute(Constant.
            USER_PERMISSION);
        rescode = rescode.trim();
        optcode = optcode.trim();
        if ( (permissions == null) || (permissions.size() == 0))
            return false;
        if ( (optcode == null) || (rescode == null))
            return false;
        for (java.util.Iterator it = permissions.iterator(); it.hasNext(); ) {
            UserPermission userperm = (UserPermission) it.next();
            if ( (userperm.getOpt_code().equals(optcode)) &&
                (userperm.getRes_code().equals(rescode))) {
                return true;
            }
        }
        return false;
    }

    /**
     * jsp需要判断权限
     * @param pageContext jsp的上下文
     * @param operation 权限数据
     * @return 是否有权限
     */
    public static boolean checkAcl(PageContext pageContext,String operation){
        HttpServletRequest request = (HttpServletRequest) pageContext.
            getRequest();
        HttpSession session = request.getSession();
        StringTokenizer stk = new StringTokenizer(operation, ".");
        int next = 0;
        String optcode = "";
        String rescode = "";
        while (stk.hasMoreTokens()) {
            if (next == 0)
                rescode = stk.nextToken();
            else
                optcode = stk.nextToken();
            ++next;
        }

        return checkPermissionByCode(session,optcode,rescode);

    }

    /**
     * 判断是否处于登陆状态
     * @return boolean
     */
    public static boolean isLogon(PageContext pageContext){
      HttpServletRequest request = (HttpServletRequest) pageContext.
            getRequest();
        HttpSession session = request.getSession();
      String isLogon = (String)session.getAttribute("isLogon");
      if("true".equalsIgnoreCase(isLogon)){
        return true;
      }else{
        return false;
      }

    }
}
