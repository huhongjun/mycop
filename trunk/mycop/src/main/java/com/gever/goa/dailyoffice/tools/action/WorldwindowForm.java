package com.gever.goa.dailyoffice.tools.action;

import java.util.Collection;

import com.gever.struts.form.BaseForm;
/**世界之窗模块
 * <p>Title: </p>
 * <p>Description: GOA</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: 天行软件</p>
 * @author Hu.Walker
 * @version 1.0
 */
public class WorldwindowForm extends BaseForm {
    private Collection Worldwindow_types; //保存所有类别；
    private String windowType = ""; //保存特定的类别
    private String searchValue = ""; //搜索值

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
