package com.gever.goa.dailyoffice.tools.action;

import java.util.Collection;

import com.gever.struts.form.BaseForm;
/**����֮��ģ��
 * <p>Title: </p>
 * <p>Description: GOA</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: �������</p>
 * @author Hu.Walker
 * @version 1.0
 */
public class WorldwindowForm extends BaseForm {
    private Collection Worldwindow_types; //�����������
    private String windowType = ""; //�����ض������
    private String searchValue = ""; //����ֵ

    public WorldwindowForm() {
    }

    public void setWorldwindow_types(Collection types) {
        this.Worldwindow_types = types;
    }

    public Collection getWorldwindow_types() {
        return this.Worldwindow_types;
    }

    public String getSearchValue() {
        return searchValue;
    }

    public void setSearchValue(String searchValue) {
        this.searchValue = searchValue;
    }

    public String getWindowType() {
        System.out.println("**********Get WindowType*******"+windowType);
        return windowType;
    }

    public void setWindowType(String windowType) {
        System.out.println("**********Set WindowType*******"+windowType);
        this.windowType = windowType;
    }
}
