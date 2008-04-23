package com.gever.goa.dailyoffice.tools.action;

import com.gever.struts.form.BaseForm;

public class TicklerForm extends BaseForm {
    private String date="";
    private String searchValue = "";
    public TicklerForm() {

    }
    public String getSearchValue() {
        return searchValue;
    }
    public void setSearchValue(String searchValue) {
        this.searchValue = searchValue;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
}
