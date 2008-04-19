package com.zhjedu.exam.domain;



/**
 * ZjQuizQuestion generated by MyEclipse - Hibernate Tools
 */

public class ZjQuizQuestion  implements java.io.Serializable {


    // Fields    

     private String id;
     private String paper = "";
     private String quiz = "";
     private ZjQuestion question;
//     private String question;
     private Long grade = new Long(0);
     private Long belongto = new Long(0);


    // Constructors

    /** default constructor */
    public ZjQuizQuestion() {
    }

   
    // Property accessors

    public String getId() {
        return this.id;
    }
    
    public void setId(String id) {
        this.id = id;
    }

    public String getPaper() {
        return this.paper;
    }
    
    public void setPaper(String paper) {
        this.paper = paper;
    }

    public String getQuiz() {
        return this.quiz;
    }
    
    public void setQuiz(String quiz) {
        this.quiz = quiz;
    }

    public Long getGrade() {
        return this.grade;
    }
    
    public void setGrade(Long grade) {
        this.grade = grade;
    }

    public Long getBelongto() {
        return this.belongto;
    }
    
    public void setBelongto(Long belongto) {
        this.belongto = belongto;
    }


	public ZjQuestion getQuestion() {
		return question;
	}


	public void setQuestion(ZjQuestion question) {
		this.question = question;
	}
   








}