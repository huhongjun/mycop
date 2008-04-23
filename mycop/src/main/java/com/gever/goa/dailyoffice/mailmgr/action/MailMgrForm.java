package com.gever.goa.dailyoffice.mailmgr.action;

import java.util.ArrayList;
import java.util.List;

import com.gever.goa.dailyoffice.mailmgr.vo.MailCapacityVO;
import com.gever.goa.dailyoffice.mailmgr.vo.MailDirectoryVO;
import com.gever.goa.dailyoffice.mailmgr.vo.MailErrorInfoVO;
import com.gever.goa.dailyoffice.mailmgr.vo.MailInfoVO;
import com.gever.goa.dailyoffice.mailmgr.vo.MailVO;
import com.gever.struts.form.BaseForm;

public class MailMgrForm extends BaseForm {

    private String fileElements = "";//页面属性字符串
    private int mailActionType;//邮件动作设定
    private MailVO mailVo = new MailVO();//邮件数据对象
    private MailCapacityVO mailCapacityVo = new MailCapacityVO();//邮箱容量数据对象
    private MailDirectoryVO mailDirectoryVo = new MailDirectoryVO();//邮件夹数据对象
    private MailInfoVO mailInfo;//邮件信息设置数据对象
    private List userMailDirList ;//用户邮件夹信息列表
    private String mailDirId;//用户当前邮件夹Id
    private String moveMailDirId = "-1";//移动到邮箱id
    private String gotoMailDirId = "-1";//将要查看邮箱id
    private String oldMailDirId = "";//前邮件夹Id
    private String strWhereFromPage = "";//页面传来查询条件
    private String findNameQuery;//查询发件人姓名
    private String findMailTitleQuery;//查询邮件主题
    private String findTimeQuery;//查询发件日期
    private String mailTodayQuery;//查询今日邮件
    private String unreadMailQuery;//查询未读邮件
    private String unergenceMailQuery;//查询紧急邮件
    private String exterioMailQuery;//查询外部邮件
    private String curerentMailType;//当前发送邮件类型
    private String deleteFiles = "";//被删除附件Id字符串
    private String smtpState;
    private String pageNumber;
    private String pop3MailResult = "";
    private boolean isPageSetuped;
    private boolean saveOnly = false;
    private boolean save = false;
    private MailErrorInfoVO errorInfo;
    private List errorList = new ArrayList();

    public MailMgrForm() {
    }
    public String getFileElements() {
        return fileElements;
    }
    public void setFileElements(String fileElements) {
        this.fileElements = fileElements;
    }
    public int getMailActionType() {
        return mailActionType;
    }
    public void setMailActionType(int mailActionType) {
        this.mailActionType = mailActionType;
    }
    public MailVO getMailVo() {
        return mailVo;
    }
    public void setMailVo(MailVO mailVo) {
        this.mailVo = mailVo;
    }
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
    public String getMailDirId() {
        return mailDirId;
    }
    public void setMailDirId(String mailDirId) {
        this.mailDirId = mailDirId;
    }
    public String getMoveMailDirId() {
        return moveMailDirId;
    }
    public void setMoveMailDirId(String moveMailDirId) {
        this.moveMailDirId = moveMailDirId;
    }
    public void setGotoMailDirId(String gotoMailDirId) {
        this.gotoMailDirId = gotoMailDirId;
    }
    public String getGotoMailDirId() {
        return gotoMailDirId;
    }
    public String getOldMailDirId() {
        return oldMailDirId;
    }
    public void setOldMailDirId(String oldMailDirId) {
        this.oldMailDirId = oldMailDirId;
    }
    public String getStrWhereFromPage() {
        return strWhereFromPage;
    }
    public void setStrWhereFromPage(String strWhereFromPage) {
        this.strWhereFromPage = strWhereFromPage;
    }
    public String getFindNameQuery() {
        return findNameQuery;
    }
    public void setFindNameQuery(String findNameQuery) {
        this.findNameQuery = findNameQuery;
    }
    public String getFindMailTitleQuery() {
        return findMailTitleQuery;
    }
    public void setFindMailTitleQuery(String findMailTitleQuery) {
        this.findMailTitleQuery = findMailTitleQuery;
    }
    public String getFindTimeQuery() {
        return findTimeQuery;
    }
    public void setFindTimeQuery(String findTimeQuery) {
        this.findTimeQuery = findTimeQuery;
    }
    public String getMailTodayQuery() {
        return mailTodayQuery;
    }
    public void setMailTodayQuery(String mailTodayQuery) {
        this.mailTodayQuery = mailTodayQuery;
    }
    public String getUnreadMailQuery() {
        return unreadMailQuery;
    }
    public void setUnreadMailQuery(String unreadMailQuery) {
        this.unreadMailQuery = unreadMailQuery;
    }
    public String getUnergenceMailQuery() {
        return unergenceMailQuery;
    }
    public void setUnergenceMailQuery(String unergenceMailQuery) {
        this.unergenceMailQuery = unergenceMailQuery;
    }
    public String getCurerentMailType() {
        return curerentMailType;
    }
    public void setCurerentMailType(String curerentMailType) {
        this.curerentMailType = curerentMailType;
    }
    public void setPageNumber(String pageNumber) {
        this.pageNumber = pageNumber;
    }
    public String getPageNumber() {
        return pageNumber;
    }
    public boolean isIsPageSetuped() {
        return isPageSetuped;
    }
    public void setIsPageSetuped(boolean isPageSetuped) {
        this.isPageSetuped = isPageSetuped;
    }
    public String getSmtpState() {
        return smtpState;
    }
    public void setSmtpState(String smtpState) {
        this.smtpState = smtpState;
    }
  public String getExterioMailQuery() {
    return exterioMailQuery;
  }
  public void setExterioMailQuery(String exterioMailQuery) {
    this.exterioMailQuery = exterioMailQuery;
  }
    public MailInfoVO getMailInfo() {
        return mailInfo;
    }
    public void setMailInfo(MailInfoVO mailInfo) {
        this.mailInfo = mailInfo;
    }
    public MailErrorInfoVO getErrorInfo() {
        return errorInfo;
    }
    public List getErrorList() {
        return errorList;
    }
    public void setErrorInfo(MailErrorInfoVO errorInfo) {
        this.errorInfo = errorInfo;
    }
    public void setErrorList(List errorList) {
        this.errorList = errorList;
    }
    public String getDeleteFiles() {
        return deleteFiles;
    }
    public void setDeleteFiles(String deleteFiles) {
        this.deleteFiles = deleteFiles;
    }
    public String getPop3MailResult() {
        return pop3MailResult;
    }
    public void setPop3MailResult(String pop3MailResult) {
        this.pop3MailResult = pop3MailResult;
    }
    public boolean isSaveOnly() {
        return saveOnly;
    }
    public void setSaveOnly(boolean saveOnly) {
        this.saveOnly = saveOnly;
    }
    public boolean isSave() {
        return save;
    }
    public void setSave(boolean save) {
        this.save = save;
    }
}
