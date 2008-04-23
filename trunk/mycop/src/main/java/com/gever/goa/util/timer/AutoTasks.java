package com.gever.goa.util.timer;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import com.gever.exception.DefaultException;
import com.gever.struts.Constant;
import com.gever.util.NumberUtil;
/**
 * <p>Title: 定时器</p>
 * <p>Description: 定时器</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: 天行软件</p>
 * @author Hu.Walker
 * @version 1.0
 */


public class AutoTasks {
    private Timer timer;
    protected final static String DATA_SOURCE =  Constant.DATA_SOURCE;
    public AutoTasks() {

    }

    /**
     * 开始执行任务
     * @param fileName 文件名称
     * @throws DefaultException
     */
    public void start(String fileName) throws DefaultException{
        timer = new Timer();
        TimerConfig cfg = TimerConfig.getInstance(fileName);
        List aryData = cfg.getTimerList();
        Object[] oClass = new Object[aryData.size()];

        //初始化所有
        for (int idx = 0; idx < aryData.size(); idx++) {
            TimerVO cfgVO = (TimerVO) aryData.get(idx);

            try {
                oClass[idx] = Class.forName(cfgVO.getClassName()).newInstance();
            } catch (java.lang.ClassNotFoundException e) {
                e.printStackTrace();
                throw new DefaultException("goa系统中没有找到该类[" + cfgVO.getClassName() +"]");
            } catch (java.lang.InstantiationException e) {
                e.printStackTrace();
                throw new DefaultException("实例化出错[" + cfgVO.getClassName() +"]");
            } catch (java.lang.IllegalAccessException e) {
                e.printStackTrace();
                throw new DefaultException("实例化出错[" + cfgVO.getClassName() +"]");
            }
        }

        //执行任务
        for (int idx = 0; idx < oClass.length; idx++) {
            TimerVO cfgVO  = (TimerVO) aryData.get(idx);
            RemindTask task = new RemindTask(oClass[idx], cfgVO);
            long lngSeconds = NumberUtil.stringToLong(cfgVO.getSeconds(), 0);
            timer.schedule(task,10000, lngSeconds * 1000); //参数要求转化成毫秒

        }

    }

    public static void main(String args[]) {
        try {
            AutoTasks at = new AutoTasks();
            at.start("c:/timer.xml"); //５秒后运行
            Thread.sleep(100000l);
            at.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 关闭
     */
    public void close() {
        timer.cancel();
    }

    /**一个内部类，封装了所要运行的任务*/
    class RemindTask extends TimerTask {
        private boolean cancel = false;
        private Object mClass = null;
        private TimerVO timerVO = new TimerVO();


        public RemindTask(Object oClass, TimerVO vo) {
            this.mClass = oClass;
            this.timerVO = vo;
        }

    //    private final static SET_DBDATA = "setDbData();
        public void run() {
            TimerConfig cfg = null;
            try {
                cfg = TimerConfig.getInstance("c:/timer.xml");
               // System.out.println("---------------"+timerVO.getIsDao());
                if ("true".equals(timerVO.getIsDao())) {
                    cfg.execSetDBMethod(mClass, DATA_SOURCE);
                }

                boolean bRet = cfg.performMethod(mClass,
                                                 timerVO.getCheckMethod());
                if (bRet == true) {
                    bRet = cfg.performMethod(mClass, timerVO.getDoMethod());
                //    System.out.println("--------do.........---" + bRet);
                }

            } catch (DefaultException e) {
                e.printStackTrace();
            }

           // System.out.println("任务运行。。。。");
        }
    }
}
