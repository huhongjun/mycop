package com.zhjedu.exam.domain;



/**
 * ZjQuizFeedback generated by MyEclipse - Hibernate Tools
 */

public class ZjQuizFeedback  implements java.io.Serializable {


    // Fields    

     private String id;
     private String quiz;
     private String feedbackcontext;
     private Long mingrade;
     private Long maxgrade;


    // Constructors

    /** default constructor */
    public ZjQuizFeedback() {
    }

    
    /** full constructor */
    public ZjQuizFeedback(String quiz, String feedbackcontext, Long mingrade, Long maxgrade) {
        this.quiz = quiz;
        this.feedbackcontext = feedbackcontext;
        this.mingrade = mingrade;
        this.maxgrade = maxgrade;
    }

   
    // Property accessors

    public String getId() {
        return this.id;
    }
    
    public void setId(String id) {
        this.id = id;
    }

    public String getQuiz() {
        return this.quiz;
    }
    
    public void setQuiz(String quiz) {
        this.quiz = quiz;
    }

    public String getFeedbackcontext() {
        return this.feedbackcontext;
    }
    
    public void setFeedbackcontext(String feedbackcontext) {
        this.feedbackcontext = feedbackcontext;
    }

    public Long getMingrade() {
        return this.mingrade;
    }
    
    public void setMingrade(Long mingrade) {
        this.mingrade = mingrade;
    }

    public Long getMaxgrade() {
        return this.maxgrade;
    }
    
    public void setMaxgrade(Long maxgrade) {
        this.maxgrade = maxgrade;
    }
   








}