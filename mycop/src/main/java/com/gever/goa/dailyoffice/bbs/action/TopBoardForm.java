package com.gever.goa.dailyoffice.bbs.action;

import com.gever.struts.form.BaseForm;

public class TopBoardForm extends BaseForm {
    private String searchValue = "";
    public TopBoardForm() {
    }

    public String getSearchValue() {
        return searchValue;
    }

    public void setSearchValue(String searchValue) {
        this.searchValue = searchValue;
    }

}
