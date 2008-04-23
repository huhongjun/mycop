

package com.gever.sysman.privilege.util;


/**
 * 
 * 
 */
public class UniQueryConfig
{

    public static final String DATA_SOURCE = "UniQuery";
    public static final String DEFAULT_COLUMN_TYPE = "VARCHAR";
    public static final String PACKAGE = "com.gever.uniquery.";
    public static final String DEFAULT_JS_PATH = "scripts/";
    public static final String DEFAULT_ACTION_PATH = "";
    public static final String DEFAULT_FORWARD = "/uniquery/xmlcontent.jsp";
    public static final String DEFAULT_DATA_FILTER = "com.gever.uniquery.filters.DefaultDataFilter";
    public static final String DEFAULT_CRITERIA_FILTER = "com.gever.uniquery.filters.DefaultCriteriaFilter";
    public static final String DEFAULT_RETURN_METHOD = "request";
    public static final String DEFAULT_MODEL_NAME = "com.gever.uniquery.DEFAULT_MODEL_NAME";
    public static final String DEFAULT_SQL_NAME = "com.gever.uniquery.DEFAULT_SQL_NAME";
    public static final boolean DEFAULT_CAN_INPUT_LOGIC_OPERATOR = true;
    public static final boolean DEFAULT_CAN_INPUT_BRACKET_OPERATOR = true;
    public static final boolean DEFAULT_CAN_INPUT_ORDER_OPERATOR = true;
    public static final boolean DEFAULT_CAN_SAVE_UNIQUERY_STATE = false;
    public static final boolean DEFAULT_CAN_DISPLAY_COLUMN_ALIAS = false;
    public static final boolean DEFAULT_CAN_DISPLAY_BACK_BUTTON = true;
    public static final String UNIQUERY_SESSION_ID = "com.gever.uniquery.UNIQUERY_SESSION_ID";
    public static final String UNIQUERYDATA_SESSION_ID = "com.gever.uniquery.UNIQUERYDATA_SESSION_ID";
    public static final String STATUS_TYPE_SUCCESS = "success";
    public static final String STATUS_TYPE_WARNING = "warning";
    public static final String STATUS_TYPE_FATAL = "fatal";
    public static final String ERRMSG_NOT_INITIALIZATION = "ͨ�ò�ѯδ��ʼ��.";
    public static final String ERRMSG_REQUIRE_TABLE_NAME = "δָ������.";
    public static final String ERRMSG_REQUIRE_COLUMN_NAME = "��ѡ���ֶ�����.";
    public static final String ERRMSG_UNKNOWN_COLUMN_NAME = "����ʶ����ֶ�����.";
    public static final String ERRMSG_REQUIRE_DIFFERENT_COLUMN = "��ѡ��ͬ���ֶ�.";
    public static final String ERRMSG_REQUIRE_OP_NAME = "��ѡ�������.";
    public static final String ERRMSG_UNKNOWN_OP_NAME = "����ʶ��������.";
    public static final String ERRMSG_REQUIRE_CRITERIA_ID = "��ѡ���б�ʽID";
    public static final String ERRMSG_REQUIRE_ORDER_ID = "��ѡ������ID";
    public static final String ERRMSG_AND_CRITERIA_ERROR = "AND ��������������ǰ������б�ʽ��.";
    public static final String ERRMSG_OR_CRITERIA_ERROR = "OR ��������������ǰ������б�ʽ��.";
    public static final String ERRMSG_REQUIRE_LOGIC_CRITERIA = "ȱ�� AND �� OR .";
    public static final String ERRMSG_NO_CRITERIA_BETWEEN_BRACKETS = "����֮��������.";
    public static final String ERRMSG_BRACKETS_NOT_MATCH = "���Ų�ƥ��.";
    public static final String ERRMSG_UNSUPPORTED_CRITERIA = "��֧�ֵ��б�ʽ��";

    public UniQueryConfig()
    {
    }
}
