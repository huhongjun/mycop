package com.gever.struts.pager;

import javax.servlet.http.HttpServletRequest;

import com.gever.config.Environment;
import com.gever.exception.DefaultException;
import com.gever.struts.Constant;
import com.gever.struts.action.GoaActionConfig;
import com.gever.struts.form.BaseForm;
import com.gever.util.SumUtils;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: 天行软件</p>
 * @author Hu.Walker
 * @version 1.0
 */

public class PageHelper {
    private PageControl pageControl = new PageControl();
    private long page = 1;
    private long start = 0l;
    private long count = 0l;
    private int pageCount = -1;

    public PageHelper() {
        pageControl = new PageControl();
    }

    public PageControl pagination(long curPage, long count,String pageType) {

        this.page = 1;
        page = curPage;
        if (this.pageCount == -1){
            pageCount =  Environment.getIntProperty("pager.rowsPerPage", 10);
        } else if (this.pageCount == 0){
            pageCount =(int) count;
        }
        pageControl.setRowsPerPage( pageCount);
        pageControl.setMaxRowCount(count);   //最大记录数

        //得到最终当前页
        page = page < 1 ? 1 : page;
        page = page > pageControl.getMaxPage() ? pageControl.getMaxPage() : page;

        //设置开始,统计数
        this.setStart( pageControl.getRowsPerPage() * (page - 1));
        this.setCount(pageControl.getRowsPerPage());
        pageControl.setCurrentPage(page); //设置当前页
        return pageControl;
    }


    /**
     * 得到当前页(继续返回到本页面新增)
     * @param cfg 当前Action相关配置类
     * @param lngCnt 统计记录数
     * @return forward地址
     * @throws DefaultException
     * @throws Exception
     */
    public String getPageStr(GoaActionConfig cfg, long lngCnt,String pageType) throws
        DefaultException,
        Exception {
        BaseForm bForm = cfg.getBaseForm();
        HttpServletRequest request = cfg.getRequest();

        String pageStr = "";
        //新增时跑到最后一页,
        if (Constant.FIRST_PAGE.equals(pageType)) {
            return "1";
        } else if (Constant.LAST_PAGE.equals(pageType)){
            return String.valueOf(lngCnt);
        }

        pageStr = request.getParameter("page");
        if (pageStr == null || pageStr.length() == 0) {
            pageStr = SumUtils.nullToString(request.getAttribute("page"));
            //如果是修改的时候,将已保存的当前页恢复过来
            if ("".equals(pageStr))
                pageStr = String.valueOf(bForm.getCurPage());
        }
        return pageStr;
    }

    public String  procOrderBy(String sql ,String orderby, String type){
        return "";
    }
    public long getPage() {
        return page;
    }

    public PageControl getPageControl() {
        return pageControl;
    }

    public void setPageControl(PageControl pageControl) {
        this.pageControl = pageControl;
    }

    public void setPage(long page) {
        this.page = page;
    }
    public long getCount() {
        return count;
    }
    public void setCount(long count) {
        this.count = count;
    }
    public long getStart() {
//        if ( start %pageControl.getRowsPerPage()  ==0){
//            ++start;
//        }
        return start;
    }
    public void setStart(long start) {
        this.start = start;
    }
    public int getPageCount() {
        return pageCount;
    }
    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }
}