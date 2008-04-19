package com.zhjedu.util;

public interface Constants {
	  
	  /**
	   * 分页时每页显示记录个数
	   */
	  public static final int pageSize = 10;
	  public static String LINE_SEPARATOR = System.getProperty("line.separator");
	  /**
	   * 单选题型
	   */
	  public static final String QUESTION_SINGLECHOICE = "1";
	  /**
	   * 多选题型
	   */
	  public static final String QUESTION_MULTICHOICE = "2";
	  /**
	   * 判断题型
	   */
	  public static final String QUESTION_JUDGE = "3";
	  /**
	   * 匹配题
	   */
	  public static final String QUESTION_MATCHING = "4";
	  /**
	   * 选择型填空题型
	   */
	  public static final String QUESTION_CHOICEFILL = "5";
	  /**
	   * 输入型填空题型
	   */
	  public static final String QUESTION_INPUTFILL = "6";
	  /**
	   * 简答题型
	   */
	  public static final String QUESTION_ANSWER = "7";
	  /**
	   * 综合题
	   */
	  public static final String QUESTION_INTEGRATE = "9";


	  /**
	   * 综合题别名
	   */
	  /**完型填空*/
	  public static final String INTEGRATE_ALIAS_FILL="1";
	  /**阅读理解*/
	  public static final String INTEGRATE_ALIAS_READING="2";
}