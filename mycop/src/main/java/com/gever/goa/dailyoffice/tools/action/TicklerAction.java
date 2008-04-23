package com.gever.goa.dailyoffice.tools.action;

import javax.servlet.http.HttpServletRequest;

import com.gever.exception.DefaultException;
import com.gever.goa.dailyoffice.message.dao.MessageDao;
import com.gever.goa.dailyoffice.message.dao.MessageFactory;
import com.gever.goa.dailyoffice.tools.dao.TicklerDao;
import com.gever.goa.dailyoffice.tools.dao.ToolsFactory;
import com.gever.goa.dailyoffice.tools.vo.TicklerVO;
import com.gever.jdbc.BaseDAO;
import com.gever.struts.action.BaseAction;
import com.gever.struts.action.GoaActionConfig;
import com.gever.util.DateTimeUtils;
import com.gever.util.StringUtils;

public class TicklerAction extends BaseAction {
    //private TicklerDao dao;
    public TicklerAction() {

    }

    /**
     * 初始化动作，包括生成DAO，VO对象；
     * @param cfg GoaActionConfig
     */
    public void initData(GoaActionConfig cfg) {
        TicklerForm myForm = (TicklerForm) cfg.getBaseForm(); //得到form变量
        TicklerDao dao = ToolsFactory.getInstance().createTicklerDao(super.
                dbData); //得到相对应dao的实例
        cfg.setBaseDao( (BaseDAO) dao); //设置父类dao

        //容错处理,防止vo对象为null
        if (myForm.getVo() == null)
            myForm.setVo(new TicklerVO());

        return;
    }

    /**
     * 修改动作，更新页面数据
     * @param cfg GoaActionConfig 包含各种配置信息
     * @param isBack boolean 是否返回到list页面
     * @throws DefaultException
     * @throws Exception
     * @return String 返回的forword名
     */
    protected String doUpdate(GoaActionConfig cfg, boolean isBack) throws
            DefaultException, Exception {

        String remind_flag = cfg.getRequest().getParameter("vo.remind_flag");
        String finish_flag = cfg.getRequest().getParameter("vo.finish_flag");
        TicklerVO vo = (TicklerVO) cfg.getBaseForm().getVo();
        if (remind_flag != null && remind_flag.equals("1")) {
            vo.setRemind_flag("1");
        } else {
            vo.setRemind_flag("0");
        }
        if (finish_flag != null && finish_flag.equals("1")) {
            vo.setFinish_flag("1");
        } else {
            vo.setFinish_flag("0");
        }
        String forword = super.doUpdate(cfg, isBack);
        //以下为判断短消息发送状态
        //先判断是否需要提醒
        log.showLog(""+vo.getRemind_flag().equals("1"));
        if (vo.getRemind_flag().equals("1")) {
            //需要提醒，判断原来是否有提醒；
            if ("1".equals(vo.getRemind_flag_bak())) {
                //原来有提醒，判断内容有没有改变。。；
                if (vo.getContent_bak().equals(vo.getContent()) &&
                    vo.getAwoke_time_bak().equals(vo.getAwoke_time())) {
                    //没有改变do nothing
                } else {
                    //update
                    this.updateMessage(vo);
                    //
                }

            } else {
                //原来没有提醒，新增一条提醒消息；
                this.sendMessage(vo);
            }

        } else {
            //不需要提醒，则判断原来是否有提醒
            if (vo.getRemind_flag_bak().equals("1")) {
                //原来有提醒则，删除该提醒（短消息）
                this.deleteMessage(vo);
            } else {
                //原来没有提醒，无动作
            }
        }
        vo.setOtherProperty(null);
        return forword;
    }

    /**
     * 按输入内容进行查找
     * @param cfg GoaActionConfig
     * @throws DefaultException
     * @throws Exception
     * @return String 返回forword的名字
     */
    public String doSearchByContent(GoaActionConfig cfg) throws
            DefaultException, Exception {
        //在BForm里设置查询条件。。。。。。
        /*        TicklerForm form = (TicklerForm) cfg.getBaseForm();
                String sqlWhere = "";
                String content = form.getSearchValue();
                if ( (!"".equals(content)) && (!"'".equals(content))) {
                    sqlWhere += " and content like '%" + content + "%'";
                }
                String user_code = form.getUserId();
                if (!"".equals(user_code)) {
                    sqlWhere += " and user_code =" + user_code;
                }
                form.setSqlWhere(sqlWhere);*/
        return this.toList(cfg);
    }
    protected String toView(GoaActionConfig cfg)throws DefaultException,Exception{
        // TicklerForm form = (TicklerForm) cfg.getBaseForm();
         HttpServletRequest request=cfg.getRequest();
         String fromModule=request.getParameter("fromModule");
         if(fromModule!=null){
             request.setAttribute("fromModule",fromModule);
         }
         return super.toView(cfg);
    }
    protected String toList(GoaActionConfig cfg) throws DefaultException,
            Exception {
        //BaseDAO basedao=cfg.getBaseDao();
        TicklerForm form = (TicklerForm) cfg.getBaseForm();
        log.showLog("----TicklerAction.date---------" + form.getDate());
        String sqlWhere = "";

        HttpServletRequest request = cfg.getRequest();

        String date = StringUtils.nullToString(
                request.getParameter("date"));
        log.showLog("----TicklerAction.date---------" + date);
        if (!"".equals(date)) { //判断是否传人父节点
            form.setDate(date);
            form.setSearchValue("");
            sqlWhere = " and create_date =" +super.toDate(date) + " and user_code =" +
                    form.getUserId();
            form.setSqlWhere(sqlWhere);
        } else {
            String content = form.getSearchValue();
            log.showLog("----TicklerAction.content---------" + content);
            if (content != null && !"".equals(content)) {
                content=StringUtils.replaceText(content);
                sqlWhere = " and content like '%" + content + "%'";
            }
            date = form.getDate();
            log.showLog("----TicklerAction.date---------" + date);
            if (!"".equals(date)) {
                sqlWhere += " and create_date =" +toDate(date) + " and user_code =" +
                        form.getUserId();
                form.setSqlWhere(sqlWhere);
            } else {
                String oldWhere = form.getSqlWhere();
                log.showLog("**********TicklerAction.oldWhere******" + oldWhere +
                            "******");
                if (!"".equals(oldWhere)) {

                } else {
                    String tmp=DateTimeUtils.getCurrentDate();
                    form.setDate(tmp);
                    sqlWhere = " and create_date =" +toDate(tmp) + " and user_code =" + form.getUserId();
                    form.setSqlWhere(sqlWhere);
                }
            }
        }
        log.showLog("*********TicklerAction.sqlWhere======" + form.getSqlWhere() +
                    "======");
        String forword = super.toList(cfg);

        //form.setSqlWhere(""); //条件设初值；

        return forword;
    }

    protected String doInsert(GoaActionConfig cfg, boolean isBack) throws
            DefaultException,
            Exception {
        TicklerForm form = (TicklerForm) cfg.getBaseForm();
        TicklerVO vo = (TicklerVO) form.getVo();
        vo.setUser_code(form.getUserId());
        vo.setFinish_flag("0");
        String forword = super.doInsert(cfg, isBack);
        //判断是否发短消息提醒
        if (vo.getRemind_flag().equals("1")) {
            //发送短消息
            this.sendMessage(vo);
        }
        form.setSearchValue(""); //清空查询条件；
        return forword;
    }

    /**
     * 删除动作(继续返回到本页面新增)
     * @param cfg 当前Action相关配置类
     * @return forward地址
     * @throws DefaultException
     * @throws Exception
     */
    protected String doDelete(GoaActionConfig cfg) throws DefaultException,
            Exception {
        TicklerForm form = (TicklerForm)cfg.getBaseForm();
        String[] pks= cfg.getRequest().getParameterValues("fid");
        this.deleteMessage(pks,form.getUserId());
        String forword = super.doDelete(cfg);
        return forword;
    }

    private void sendMessage(TicklerVO vo) throws Exception {
        log.showLog("*******发送短消息************");
        MessageDao msgdao = this.getMessageDAO(vo.getUser_code());
        String user_code = vo.getUser_code();
        String send_time = vo.getAwoke_time();
        String content = vo.getContent();
        log.showLog("短消息内容长度String.length()===="+content.length());
        log.showLog("短消息内容长度String.length()===="+content.getBytes().length);
        //短消息内容长度限制为255；
        if(content.getBytes().length>255){
            content=content.substring(0,127);
        }
        String receiver = vo.getUser_code();
        String web_url = "/dailyoffice/tools/viewTickler.do?fid="+vo.getSerial_num()+"&fromModule=message";
        String model_id = "2";
        String mserial_num = vo.getSerial_num();
        String memo="备忘录提醒";
        msgdao.sendMessageInfo(user_code, send_time, content, receiver, web_url,
                               model_id, mserial_num,memo);
    }

    private void updateMessage(TicklerVO vo) throws Exception {
        log.showLog("*******更新短消息内容************");
        MessageDao msgdao = this.getMessageDAO(vo.getUser_code());
        String send_time = vo.getAwoke_time();
        String content = vo.getContent();
        String model_id = "2";
        String mserial_num = vo.getSerial_num();
        msgdao.updateMessageInfo(send_time, content, model_id, mserial_num);
    }

    private void deleteMessage(String[] pks,String userid) throws Exception{
        if(pks==null){
            return;
        }
        TicklerVO vo=new TicklerVO();
        vo.setUser_code(userid);
        for(int i=0;i<pks.length;i++){
            vo.setSerial_num(pks[i]);
            this.deleteMessage(vo);
        }
    }
    private void deleteMessage(TicklerVO vo) throws Exception {
        log.showLog("*******删除短消息************");
        MessageDao msgdao = this.getMessageDAO(vo.getUser_code());
        String model_id = "2";
        String mserial_num = vo.getSerial_num();
        msgdao.deleteMessageInfo(model_id, mserial_num);

    }

    private MessageDao getMessageDAO(String userID) {
        MessageDao dao = MessageFactory.getInstance().createMessageDao(super.
                dbData);
        ( (BaseDAO) dao).setUserID(userID);
        return dao;
    }
}
