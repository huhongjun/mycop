package com.gever.goa.dailyoffice.smsmgr.action;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import com.gever.exception.DefaultException;
import com.gever.goa.dailyoffice.smsmgr.dao.SMSCapacityDAO;
import com.gever.goa.dailyoffice.smsmgr.dao.SmsFactory;
import com.gever.goa.dailyoffice.smsmgr.dao.SmsMgrDAO;
import com.gever.goa.dailyoffice.smsmgr.vo.OutBoxVO;
import com.gever.jdbc.BaseDAO;
import com.gever.struts.action.BaseAction;
import com.gever.struts.action.GoaActionConfig;
import com.gever.sysman.api.OrganizationUtil;
import com.gever.util.DateTimeUtils;
import com.gever.util.StringUtils;
import com.gever.util.SumUtils;
import com.gever.vo.VOInterface;

/**
 * <p>Title: �ֻ�����</p>
 * <p>Description: �ֻ�����</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: �������</p>
 * @author Hu.Walker
 * @version 1.0
 */

public class SmsMgrAction extends BaseAction {
    SmsMgrDAO smsDao = null;
    private static final String WRITE_PAGE = "write";
    private static final String SEND_PAGE ="sendover";
    private final static int CHN = 1; //��Ϣ����ʾΪ����
    private final static int ENG = 0; //��Ϣ����ʾΪӢ��

    public SmsMgrAction() {
    }

    /**
     * ��ʼ��ҳ������
     * @param cfg ��ǰ��vo����
     * @throws DefaultException
     * @throws Exception
     */
    protected void initData(GoaActionConfig cfg) throws DefaultException,
        Exception {

        SmsMgrForm myForm = (SmsMgrForm) cfg.getBaseForm(); //�õ�form����
        smsDao = SmsFactory.getInstance().createSmsMgrDAO(super.dbData); //�õ����Ӧdao��ʵ��
        cfg.setBaseDao( (BaseDAO) smsDao);

        if (myForm.getVo() == null) {
            myForm.setVo(new OutBoxVO());
        }
    }

    /**
     * ���嵥ҳ��
     * @param cfg ��ǰAction���������
     * @return forward��ַ
     * @throws DefaultException
     * @throws Exception
     */

    protected String toList(GoaActionConfig cfg) throws DefaultException,
        Exception {
        String subWhere =cfg.getBaseForm().getSqlWhere();
        String defaultWhere = " and sn ='" + cfg.getBaseForm().getUserId() + "' ";
        if (StringUtils.isNull(cfg.getBaseForm().getSqlWhere())){
            cfg.getBaseForm().setSqlWhere(defaultWhere);
        } else {

            int pos = (subWhere.indexOf("sn ="));
            if (pos < 0)
                cfg.getBaseForm().setSqlWhere(defaultWhere);


        }
        super.toList(cfg);
        return this.FORWORD_LIST_PAGE;
    }

    /**
     * ���嵥ҳ��
     * @param cfg ��ǰAction���������
     * @return forward��ַ
     * @throws DefaultException
     * @throws Exception
     */

    public String toWrite(GoaActionConfig cfg) throws DefaultException,
        Exception {
        SmsMgrForm form = (SmsMgrForm) cfg.getBaseForm(); //�õ�form����
        //�õ����ż���,
        OrganizationUtil util = new OrganizationUtil();
        Collection cDept = util.getDepartmentStructure();

        List aryAddress = this.smsDao.queryCardByUserID(form.getUserId());
        List aryDir  = this.smsDao.queryCardTypeByUserID(form.getUserId());
        List aryUser = smsDao.querySmsUsers();

        cfg.getRequest().setAttribute("aryDept", cDept);
        cfg.getRequest().setAttribute("aryUser", aryUser);
        cfg.getRequest().setAttribute("aryAddress", aryAddress);
        cfg.getRequest().setAttribute("aryDir", aryDir);
        form.setKeyMobile("");
        
		SMSCapacityDAO scDao = SmsFactory.getInstance().createSmsCapacityDAO(super.dbData); //�õ����Ӧdao��ʵ��
		String corpname = scDao.queryCorpName();
        form.setMsg(corpname+",");
        
        String strCurDate = DateTimeUtils.getCurrentDate(); //��ǰ����
        //��ʼ����ʱ��������
        form.setSendYear(strCurDate.substring(0, 4));
        form.setSendMonth(strCurDate.substring(5, 7));
        form.setSendDay(strCurDate.substring(8, 10));
        String strTime = this.getCurTime();
        form.setSendHour(strTime.substring(0, 2));
        form.setSendMinute(strTime.substring(3, 5));

        String strWhere = " AND SENDDATE>='" + strCurDate + " 00:00:00'";
        strWhere +=" AND SENDDATE<='" + strCurDate + " 23:59:59'";
        Map hMap = new HashMap();
        hMap = smsDao.getCurUserSendNumber(form.getUserId(), strWhere);
        log.showLog("----WHERECNT ---" + (String) hMap.get("WHERECNT"));
        form.setSumCount( (String) hMap.get("SUMCNT"));
        form.setWhereCount( (String) hMap.get("WHERECNT"));

        SMSCapacityDAO smsCapDao = SmsFactory.getInstance().
            createSmsCapacityDAO(super.dbData); //�õ����Ӧdao��ʵ��
        form.setCapVO(smsCapDao.queryCapacityByUserId(form.getUserId()));

        form.setOtherCount(SumUtils.sum(form.getCapVO().getNumbers() + "-" +
                                        form.getWhereCount()));
        return WRITE_PAGE;

    }

    /**
     * ���嵥ҳ��
     * @param cfg ��ǰAction���������
     * @return forward��ַ
     * @throws DefaultException
     * @throws Exception
     */
    public String doSendSms(GoaActionConfig cfg) throws DefaultException,
        Exception {
        SmsMgrForm form = (SmsMgrForm) cfg.getBaseForm(); //�õ�form����

        String[] selectedUser = cfg.getRequest().getParameterValues("selecteduser");
        List arySms = new ArrayList();
        boolean bTimeSend = false; //�Ƿ�Ϊ��ʱ����
        String strTimeing = new String(); //��ʱ���͵�ʱ��
        boolean bCHN = true;
        int langType = CHN; //1Ϊ����,0ΪӢ��

        if ("0".equals(cfg.getRequest().getParameter("lang"))) {
            bCHN = false;
            langType = ENG;
        }

        if (cfg.getRequest().getParameter("bTimeSend") != null) {
            bTimeSend = true;
            strTimeing = form.getSendYear() + "-" +
                addZero(form.getSendMonth()) + "-" +
                addZero(form.getSendDay()) + " " +
                addZero(form.getSendHour()) + ":" +
                addZero(form.getSendMinute());
        }

        java.util.StringTokenizer stkMbNo;
        String curMobiles = new String();
        String strCurDate = DateTimeUtils.getCurrentDateTime(); //��ǰ����
        String strCurTime = getCurTime(); //��ǰʱ��
        boolean bIsNotKey = false;

        //�ֶ�key���ֻ�����.......
        do {
            //���ֶ�key��ѡ��ĺ���,�ۼ�����,Ȼ����������
            curMobiles = (bIsNotKey == true) ? form.getSelectMobile() :
                form.getKeyMobile(); ;
            if (curMobiles == null)
                curMobiles = "";
            stkMbNo = new StringTokenizer(curMobiles, ",");
            String strMbno = "";
            String strName = "";
            int pos = 0;
            
            while (stkMbNo.hasMoreTokens()) {
                OutBoxVO sendVO = new OutBoxVO();
                strMbno = stkMbNo.nextToken();
                pos = strMbno.indexOf(":");
                if (pos > 0) {
                    strName = strMbno.substring(pos + 1);
                    strMbno = strMbno.substring(0, pos);

                } else {
                    strName = "";
                }

                sendVO.setMbno(strMbno);
                sendVO.setMsg(form.getMsg());
                sendVO.setSn(form.getUserId());
                sendVO.setUsername(strName);
                sendVO.setSmstype(String.valueOf(langType));
                sendVO.setSenddate(strCurDate);
                sendVO.setSendtime(strCurTime);

                if (bTimeSend == true) {
                    sendVO.setSendingdate(strTimeing);
                }
                arySms.add(sendVO);
            }
            bIsNotKey = !bIsNotKey; //��ʼ�ֶ�key,���ž͸��û�ѡ���
        } while (bIsNotKey == true);

        if (bTimeSend == true) // ���ڶ�ʱ����
            smsDao.insertTiming(arySms);
        else //���Ϸ���
            smsDao.insert(arySms);

        return SEND_PAGE;
    }



    /**
     * ɾ������(�������ص���ҳ������)
     * @param cfg ��ǰAction���������
     * @return forward��ַ
     * @throws DefaultException
     * @throws Exception
     */
    protected String doDelete(GoaActionConfig cfg) throws DefaultException,
        Exception {

        int iRet = 0;
        String[] values = cfg.getRequest().getParameterValues("fid");

        if (values == null) //�ݴ���
            values = new String[0];

        //�ݴ���
        if (values.length <= 0) {
            return FORWORD_LIST_PAGE;
        }

        String[] keyId = new String[values.length];
        String[] types = new String[values.length];
        for (int idx = 0 ; idx < values.length; idx++){
            StringTokenizer stkTypes = new StringTokenizer(values[idx], ";;");
            while (stkTypes.hasMoreTokens()) {
                keyId[idx] = stkTypes.nextToken();
                types[idx] = stkTypes.nextToken();
            }
        }

        smsDao.delBySelect(keyId,types);
        doPage(cfg, "");
        //toList(vo);
        return this.FORWORD_LIST_PAGE;
    }

    /**
        * ������ҳ��
        * @param cfg ��ǰAction���������
        * @return forward��ַ
        * @throws DefaultException
        * @throws Exception
        */
       protected String toView(GoaActionConfig cfg) throws DefaultException,
           Exception {

           String values = cfg.getRequest().getParameter("fid");
           String keyId = "";
           String type = "";

           StringTokenizer stkTypes = new StringTokenizer(values, ";;");
           while (stkTypes.hasMoreTokens()) {
               keyId = stkTypes.nextToken();
               type = stkTypes.nextToken();

           }
          VOInterface vo = new OutBoxVO();
           //�õ���ǰ����������
           vo = (OutBoxVO) smsDao.queryByPk(keyId,type);

           cfg.getBaseForm().setVo(vo);
           return this.FORWORD_VIEW_PAGE;
       }

    /**
     * ��Ҫ��0��,��9-->09
     * @param value ֵ
     * @return ���
     */
    private String addZero(String value) {
        return (value.length() == 1) ? "0" + value : value;
    }

    /**
     * ��Ҫ��0��,��9-->09
     * @param value ֵ
     * @return ���
     */
    private String intToString(int value) {
        return ( (value < 10) ? "0" + String.valueOf(value) :
                String.valueOf(value));
    }

    /**
     * �õ���ǰ��ʱ��
     * @return ��ǰʱ��
     */
    private String getCurTime() {
        Calendar now = Calendar.getInstance();
        String strRet = new String();
        Date curDate = new Date();
        String hours = intToString(curDate.getHours());
        String minutes = intToString(curDate.getMinutes());
        String seconds = intToString(curDate.getSeconds());
        return hours + ":" + minutes + ":" + seconds;
    }

}