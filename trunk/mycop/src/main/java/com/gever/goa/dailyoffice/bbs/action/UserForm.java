package com.gever.goa.dailyoffice.bbs.action;

import com.gever.struts.form.BaseForm;

public class UserForm extends BaseForm {
    private String sboardID = "";
    private String searchValue="";
    public UserForm() {
    }

    public String getSboardID() {
        return sboardID;
    }

    public void setSboardID(String sboardID) {
        this.sboardID = sboardID;
    }
    public String getSearchValue() {
        return searchValue;
    }
    public void setSearchValue(String searchValue) {
        this.searchValue = searchValue;
    }
}
