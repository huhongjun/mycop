package com.gever.goa.dailyoffice.tools.action;

/**
 * <p>Title: </p>
 * <p>Description: GOA</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: 天行软件</p>
 * @author Hu.Walker
 * @version 1.0
 */
import java.util.Collection;

import com.gever.struts.form.BaseForm;

public class CardcaseForm extends BaseForm {
    private Collection cardcase_types; //保存所有名片夹类别；
    private String cardType = ""; //保存特定的名片类别
    private String nodename="";
    private String searchValue = ""; //搜索值

    public CardcaseForm() {
    }

    public void setCardcase_types(Collection types) {
        this.cardcase_types = types;
    }

    public Collection getCardcase_types() {
        return this.cardcase_types;
    }

    public String getSearchValue() {
        return searchValue;
    }

    public void setSearchValue(String searchValue) {
        this.searchValue = searchValue;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }
	/**
	 * @return
	 */
	public String getNodename() {
		return nodename;
	}

	/**
	 * @param string
	 */
	public void setNodename(String string) {
		nodename = string;
	}

}
