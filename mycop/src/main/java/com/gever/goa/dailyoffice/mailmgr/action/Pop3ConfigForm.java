package com.gever.goa.dailyoffice.mailmgr.action;

import com.gever.goa.dailyoffice.mailmgr.vo.Pop3ConfigVO;
import com.gever.struts.form.BaseForm;
import com.gever.vo.VOInterface;
/**
 *
 * <p>Title: </p>
 * <p>Description: Pop3�ʼ�</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: �������</p>
 * @author Hu.Walker
 * @version 1.0
 */
public class Pop3ConfigForm  extends BaseForm{
    public Pop3ConfigForm() {
    }
    //added by dongzg
    //
    private Pop3ConfigVO vo =new Pop3ConfigVO();
    private long numberOfPop3Acount = 0;//�ʼ����ø���ͳ��
    private String inceptResult;//�����ʼ����
    private String inceptType = "";

    public VOInterface getVo() {
        return vo;
    }
    public void setVo(VOInterface vo) {
        this.vo = (Pop3ConfigVO) vo;
    }
    public long getNumberOfPop3Acount() {
        return numberOfPop3Acount;
    }
    public void setNumberOfPop3Acount(long numberOfPop3Acount) {
        this.numberOfPop3Acount = numberOfPop3Acount;
    }
    public String getInceptResult() {
        return inceptResult;
    }
    public void setInceptResult(String inceptResult) {
        this.inceptResult = inceptResult;
    }
    public String getInceptType() {
        return inceptType;
    }
    public void setInceptType(String inceptType) {
        this.inceptType = inceptType;
    }




}
