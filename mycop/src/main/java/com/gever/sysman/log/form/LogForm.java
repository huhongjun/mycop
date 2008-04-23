package com.gever.sysman.log.form;

import org.apache.struts.action.ActionForm;
import com.gever.sysman.log.vo.LogVO;

/**
 * <p>Title: 操作日志LogForm</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: 天行软件</p>
 * @author Hu.Walker
 * @version 0.5
 */

public class LogForm extends ActionForm{
    private LogVO searchVo = new LogVO();
    private LogVO vo = new LogVO();
    public LogForm() {
    }
    public LogVO getSearchVo() {
        return searchVo;
    }
    public LogVO getVo() {
        return vo;
    }
    public void setVo(LogVO vo) {
        this.vo = vo;
    }
    public void setSearchVo(LogVO searchVo) {
        this.searchVo = searchVo;
    }

}