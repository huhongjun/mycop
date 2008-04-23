package com.gever.sysman.log.action;


import com.gever.exception.DefaultException;


import com.gever.struts.pager.AbstractPageControlHelper;
import com.gever.struts.pager.PageControl;

import com.gever.sysman.log.dao.LogDAOFactory;
import com.gever.sysman.log.dao.LogDao;
import com.gever.sysman.log.form.LogForm;
import com.gever.sysman.log.vo.LogVO;
import com.gever.sysman.util.BaseAction;
import com.gever.sysman.util.OrderList;
import com.gever.util.log.Log;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * <p>Title: 操作日志Action类</p>
 * <p>Description: 操作日志包括,查询,删除动作</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: 天行软件</p>
 * @author Hu.Walker
 * @version 0.5
 */
public class LogAction extends BaseAction {
    private final static String TYPE_SELECT = "1"; //选择记录删除
    private final static String TYPE_WHERE = "0"; //按条件删除
    private LogDao dao = null;
    private Log log = Log.getInstance(LogAction.class);

    /**
     * null对象转换为空对象
     * @param value 对象
     * @return String对象
     */
    private String nullToString(Object value) {
        String strRet = (String) value;

        if (null == strRet) {
            return strRet = "";
        }

        return strRet;
    }

    public ActionForward list(ActionMapping mapping, ActionForm form,
        HttpServletRequest request, HttpServletResponse response)
        throws Exception {
        dao = LogDAOFactory.getInstance().getLogDao();

        LogForm mForm = (LogForm) form;
        LogVO logVO = mForm.getSearchVo();
        String endDate = logVO.getEndDate();

        if ((endDate != null) && !"".equals(endDate)) {
            Calendar endDate2 = parseDate(endDate);
            endDate2.add(Calendar.DATE, 1);
            logVO.setEndDate(endDate2.get(Calendar.YEAR) + "-" +
                format(endDate2.get(Calendar.MONTH) + 1) + "-" +
                format(endDate2.get(Calendar.DAY_OF_MONTH)));
        }

        String action = nullToString(request.getParameter("actionFlag"));

        //如果有删除
        if ("del".equals(action)) {
            String type = nullToString(request.getParameter("typeSelect"));
            log.showLog(" out type==" + type);

            if (TYPE_SELECT.equals(type)) { //如果是选择几条记录删除

                String[] aryIDs = request.getParameterValues("sid");

                if ((aryIDs != null) && (aryIDs.length > 0)) {
                    dao.delBySelect(aryIDs);
                    mForm.setSearchVo(new LogVO());
                }
            } else if (TYPE_WHERE.equals(type)) { //如果是条件删除
                dao.delByCond(mForm.getSearchVo());
                mForm.setSearchVo(new LogVO());
            }
        }

        try {
            PageControl pageControl = new AbstractPageControlHelper() {
                        public Collection getPagerData(ActionMapping mapping,
                            ActionForm form, HttpServletRequest request,
                            HttpServletResponse response, long start, long count)
                            throws DefaultException {
                            LogForm myForm = (LogForm) form;
                            Collection collection = new ArrayList();

                            //                    try {
                            //start= 0;
                            // count = 9;
                            //==========================================================================
                            //胡勇添加，增加JSP视图列表排序功能
                            OrderList _order = OrderList.getInstance();
                            String orderby = request.getParameter(OrderList.log_key);
                            String desc = request.getParameter(OrderList.desc);
                            
                            if (!_order.isNull(orderby) && !_order.isNull(desc)) {
                                String[] ss = {orderby, desc};
                                request.getSession(true).setAttribute(OrderList.log_key, ss);
                                dao.setOrderby(ss);
                            } else {
                                String[] ss = (String[]) request.getSession(true).getAttribute(OrderList.log_key);
                                if (ss != null) {
                                    dao.setOrderby(ss);
                                }
                            }
                            //==========================================================================
                            collection = dao.queryByPage(myForm.getSearchVo(),
                                    start, count);

                            //                    } catch (DefaultException e) {
                            //                        e.printStackTrace();
                            //                    }
                            return collection;
                        }

                        public long getRowCount(ActionMapping mapping,
                            ActionForm form, HttpServletRequest request,
                            HttpServletResponse response) {
                            LogForm myForm = (LogForm) form;
                            Collection collection = new ArrayList();
                            long lngCnt = dao.queryByCount(myForm.getSearchVo());

                            return lngCnt;
                        }
                    }.newPageControl(mapping, form, request, response);
            request.setAttribute("LogForm", form);
            request.setAttribute("pageControl", pageControl);
        } catch (Exception es) {
            es.printStackTrace(System.out);
            throw new DefaultException(es);
        }

        return mapping.findForward("list");
    }

    private java.util.Calendar parseDate(String date) throws DefaultException {
        java.util.StringTokenizer st = new java.util.StringTokenizer(date, "-");
        java.util.Calendar calendar = null;

        try {
            if (st.countTokens() == 3) {
                calendar = java.util.Calendar.getInstance();

                int year = Integer.parseInt(st.nextToken());
                int month = Integer.parseInt(st.nextToken()) - 1;
                int day = Integer.parseInt(st.nextToken());
                calendar.clear();
                calendar.set(year, month, day);
            } else {
                throw new DefaultException("日期格式不对");
            }
        } catch (NumberFormatException ne) {
            throw new DefaultException("日期格式不对");
        }

        return calendar;
    }

    private String format(int input) {
        String output = Integer.toString(input);

        if (output.length() == 1) {
            output = "0" + output;
        }

        return output;
    }
}
