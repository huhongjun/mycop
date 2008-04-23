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
 * <p>Title: ��ȡ��������޸�</p>
 * <p>Description: ��ȡ��������޸�</p>
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
     * �õ�ҳ���ʲ�������(�ʺ϶���޸�)
     * @param request HttpServletRequest
     * @param vo ��ǰ��vo����
     * @param columns �ֶ�����
     * @return �����������
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

        //�õ����е�����
        while (stk.hasMoreTokens()) {
            names[next++] = stk.nextToken();
        }

        //Ȼ������һ�������뵽vo,�ٷ�װlist������
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
     * ��ȡVOʵ��������
     * @param vo VO����
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
     * �õ�css����ʽ
     * @param pageContext jsp������
     * @return ·��
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
     * �õ�gdp��Ȩ��
     * @param session session����
     * @param optcode ������
     * @param rescode ��Դ����
     * @return �Ƿ���Ȩ��
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
     * jsp��Ҫ�ж�Ȩ��
     * @param pageContext jsp��������
     * @param operation Ȩ������
     * @return �Ƿ���Ȩ��
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
     * �ж��Ƿ��ڵ�½״̬
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
