package com.gever.web.menselect;

import java.util.Map;

import com.gever.exception.db.DAOException;
import com.gever.web.menselect.impl.MenSelect;

public interface MenSelectedCatalog {
  //���Id
   //���ڿͻ��˱�д��������
   //ע�⣬�÷���ֵ���ܳ��ֿո��磬����Ϊ"administrator Level"
   //�����������"administratorLevel"
    String getId();
     MenSelectedCatalog menSelectedCatalog=new MenSelect();


    //�������
    //��:"����������"
    String getName();



    //�ڸ÷����ķ���ֵMap��
    //key��Ӧ�����б��Valueֵ
    //value��Ӧ�����б����ʾ��name
    //��:
    //<select>
    //<option value="(***)">(---)</option>
    //</select>
    //�ڿͻ��˵����ɹ����У�
    //(***)Ϊvalue
    //(---)Ϊname
    Map    getCatalogs()  throws DAOException;


    //�õ���Ա��������ӳ���ϵ
    //ManӦ��ΪMap�е�key
    //catalogӦ��ΪMap�е�value
    //catalog��ֵ���뱣֤ΪgetCatalogs�з��ص�Mapһ��
    Map    getManToCatalogMap() throws DAOException;



    //�õ�getManToCatalogMap�ķ���ֵMap��δ�����
    //ӳ���ϵ��manĬ�����������ֵ
    //���뱣֤ΪgetCatalogs�з��ص�Mapһ��
    Object getDefaultCatalog();

}
