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

    private String fileElements = "";//ҳ�������ַ���
    private int mailActionType;//�ʼ������趨
    private MailVO mailVo = new MailVO();//�ʼ����ݶ���
    private MailCapacityVO mailCapacityVo = new MailCapacityVO();//�����������ݶ���
    private MailDirectoryVO mailDirectoryVo = new MailDirectoryVO();//�ʼ������ݶ���
    private MailInfoVO mailInfo;//�ʼ���Ϣ�������ݶ���
    private List userMailDirList ;//�û��ʼ�����Ϣ�б�
    private String mailDirId;//�û���ǰ�ʼ���Id
    private String moveMailDirId = "-1";//�ƶ�������id
    private String gotoMailDirId = "-1";//��Ҫ�鿴����id
    private String oldMailDirId = "";//ǰ�ʼ���Id
    private String strWhereFromPage = "";//ҳ�洫����ѯ����
    private String findNameQuery;//��ѯ����������
    private String findMailTitleQuery;//��ѯ�ʼ�����
    private String findTimeQuery;//��ѯ��������
    private String mailTodayQuery;//��ѯ�����ʼ�
    private String unreadMailQuery;//��ѯδ���ʼ�
    private String unergenceMailQuery;//��ѯ�����ʼ�
    private String exterioMailQuery;//��ѯ�ⲿ�ʼ�
    private String curerentMailType;//��ǰ�����ʼ�����
    private String deleteFiles = "";//��ɾ������Id�ַ���
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
