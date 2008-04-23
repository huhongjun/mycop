package com.gever.web.menselect;

import java.util.Map;

import com.gever.exception.db.DAOException;
import com.gever.web.menselect.impl.MenSelect;

public interface MenSelectedCatalog {
  //类表Id
   //对于客户端编写代码有用
   //注意，该返回值不能出现空格，如，不能为"administrator Level"
   //在这里可以如"administratorLevel"
    String getId();
     MenSelectedCatalog menSelectedCatalog=new MenSelect();


    //类别名称
    //如:"按行政级别"
    String getName();



    //在该方法的返回值Map中
    //key对应下拉列表的Value值
    //value对应下拉列表的显示名name
    //如:
    //<select>
    //<option value="(***)">(---)</option>
    //</select>
    //在客户端的生成过程中，
    //(***)为value
    //(---)为name
    Map    getCatalogs()  throws DAOException;


    //得到人员到该类别的映射关系
    //Man应该为Map中的key
    //catalog应该为Map中的value
    //catalog的值必须保证为getCatalogs中返回的Map一致
    Map    getManToCatalogMap() throws DAOException;



    //得到getManToCatalogMap的返回值Map中未定义的
    //映射关系的man默认所属的类别值
    //必须保证为getCatalogs中返回的Map一致
    Object getDefaultCatalog();

}
