package com.gever.goa.admin.action;

import com.gever.struts.form.BaseForm;

/**
 * <p>Title: 单位程序</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: 天行软件</p>
 * @author Hu.Walker
 * @version 1.0
 */

public class UnitForm
    extends BaseForm {

  private int datasize;
  public UnitForm () {
  }
  public int getDatasize() {
    return datasize;
  }
  public void setDatasize(int datasize) {
    this.datasize = datasize;
  }


}
