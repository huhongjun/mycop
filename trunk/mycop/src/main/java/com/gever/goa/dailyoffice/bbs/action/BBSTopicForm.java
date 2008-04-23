package com.gever.goa.dailyoffice.bbs.action;

import com.gever.goa.dailyoffice.bbs.vo.TopicListVO;
import com.gever.goa.dailyoffice.bbs.vo.TopicVO;
import com.gever.goa.dailyoffice.bbs.vo.UserVO;
import com.gever.goa.dailyoffice.bbs.vo.ViewTopicVO;
import com.gever.struts.form.BaseForm;

/**
 * <p>Title: </p>
 * <p>Description: GOA</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: ÌìÐÐÈí¼þ</p>
 * @author Hu.Walker
 * @version 1.0
 */

public class BBSTopicForm extends BaseForm {
    private TopicVO topicVO;
    private TopicListVO topiclistVO;
    private ViewTopicVO viewtopicVO;
    private UserVO userVO;
    private String searchValue = "";
    private String sboardID = "";
    private String bbsPageType = "";
    public BBSTopicForm() {
    }

    public TopicListVO getTopiclistVO() {
        return topiclistVO;
    }

    public void setTopiclistVO(TopicListVO topicListVO) {
        this.topiclistVO = topicListVO;
    }

    public TopicVO getTopicVO() {
        return topicVO;
    }

    public void setTopicVO(TopicVO topicVO) {
        this.topicVO = topicVO;
    }

    public String getSearchValue() {
        return searchValue;
    }

    public void setSearchValue(String searchValue) {
        this.searchValue = searchValue;
    }

    public String getSboardID() {
        return sboardID;
    }

    public void setSboardID(String sboardID) {
        this.sboardID = sboardID;
    }

    public UserVO getUserVO() {
        return userVO;
    }

    public void setUserVO(UserVO userVO) {
        this.userVO = userVO;
    }

    public String getBbsPageType() {
        return bbsPageType;
    }

    public void setBbsPageType(String bbsPageType) {
        this.bbsPageType = bbsPageType;
    }
    public ViewTopicVO getViewtopicVO() {
        return viewtopicVO;
    }
    public void setViewtopicVO(ViewTopicVO viewtopicVO) {
        this.viewtopicVO = viewtopicVO;
    }

}
