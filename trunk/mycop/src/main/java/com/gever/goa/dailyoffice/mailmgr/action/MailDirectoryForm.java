package com.gever.goa.dailyoffice.mailmgr.action;

import java.util.List;

import com.gever.goa.dailyoffice.mailmgr.vo.MailCapacityVO;
import com.gever.goa.dailyoffice.mailmgr.vo.MailDirectoryVO;
import com.gever.struts.form.BaseForm;

public class MailDirectoryForm extends BaseForm {
    public MailDirectoryForm() {
    }
    private MailDirectoryVO mailDirVo = new MailDirectoryVO();
    private MailCapacityVO mailCapacityVo = new MailCapacityVO();//�����������ݶ���
    private MailDirectoryVO mailDirectoryVo = new MailDirectoryVO();//�ʼ������ݶ���
    private List userMailDirList ;//�û��ʼ�����Ϣ�б�
    private String oldMailDirName;//�޸�ǰ�ʼ�������
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
