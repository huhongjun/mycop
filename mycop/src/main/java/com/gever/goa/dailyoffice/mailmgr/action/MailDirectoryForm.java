package com.gever.goa.dailyoffice.mailmgr.action;

import java.util.List;

import com.gever.goa.dailyoffice.mailmgr.vo.MailCapacityVO;
import com.gever.goa.dailyoffice.mailmgr.vo.MailDirectoryVO;
import com.gever.struts.form.BaseForm;

public class MailDirectoryForm extends BaseForm {
    public MailDirectoryForm() {
    }
    private MailDirectoryVO mailDirVo = new MailDirectoryVO();
    private MailCapacityVO mailCapacityVo = new MailCapacityVO();//邮箱容量数据对象
    private MailDirectoryVO mailDirectoryVo = new MailDirectoryVO();//邮件夹数据对象
    private List userMailDirList ;//用户邮件夹信息列表
    private String oldMailDirName;//修改前邮件夹姓名
    private String operate = "false";

    public MailCapacityVO getMailCapacityVo() {
        return mailCapacityVo;
    }
    public MailDirectoryVO getMailDirectoryVo() {
        return mailDirectoryVo;
    }
    public void setMailCapacityVo(MailCapacityVO mailCapacityVo) {
        this.mailCapacityVo = mailCapacityVo;
    }
    public void setMailDirectoryVo(MailDirectoryVO mailDirectoryVo) {
        this.mailDirectoryVo = mailDirectoryVo;
    }
    public List getUserMailDirList() {
        return userMailDirList;
    }
    public void setUserMailDirList(List userMailDirList) {
        this.userMailDirList = userMailDirList;
    }

    public MailDirectoryVO getMailDirVo() {
        return mailDirVo;
    }
    public void setMailDirVo(MailDirectoryVO mailDirVo) {
        this.mailDirVo = mailDirVo;
    }
  public String getOperate() {
    return operate;
  }
  public void setOperate(String operate) {
    this.operate = operate;
  }


}
