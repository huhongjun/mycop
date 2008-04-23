package com.gever.goa.dailyoffice.bbs.action;

import com.gever.struts.form.BaseForm;

public class SBoardForm extends BaseForm {
    String topid = "";
    private String searchValue = "";
    public SBoardForm() {
    }


    public String getSearchValue() {
        return searchValue;
    }

    public void setSearchValue(String searchValue) {
        this.searchValue = searchValue;
    }

    public String getTopid() {
        return topid;
    }

    public void setTopid(String topid) {
        this.topid = topid;
    }

}
