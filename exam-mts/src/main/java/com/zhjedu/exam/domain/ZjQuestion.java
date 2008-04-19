package com.zhjedu.exam.domain;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.struts.action.ActionForm;



/**
 * ZjQuestion generated by MyEclipse - Hibernate Tools
 */

public class ZjQuestion extends ActionForm implements java.io.Serializable {


    // Fields    

     private String id;
     private String parent="0";
     private String course="";
     private String chapter="";
     private String section="";
     private String category;
     private String subject;
     private String source;
     private String title;
     private String questioncontext;
     private int questionNum = 0;
     private String generalfeedback;
     private Long defaultgrade;
     private String qtype;
     private String shuffleoptions;
     private String difficulty;
     private String knowledge;
     private String distinguish;
     private String purpose;
     private String ability;
     private Long usetime;
     private String answers;
     private String correctfeedback;
     private String incorrectfeedback;
     private String accessoryUrl;
     private String key;
     private String memo;
     private Long lastusedate;
     private Long createdate;
     private Long lasteditdate;
     private String creator;
     private String lasteditor;
     private String delflag;
     private String status;
     private String alias;
     private Double rightRate;
     private String questionKey;
     
     private Set quizQuestion = new HashSet();
     
     private List optionList;
     private List matchingOptionList;
     private List matchingAnswerList;
     private List sonQuestionList;
     private ZjQuizAnswers uAnswer;
     private String shortName;
    // Constructors
	
	/** default constructor */
    public ZjQuestion() {
    }

	/** minimal constructor */
    public ZjQuestion(String parent, String questioncontext, Long defaultgrade, String qtype, String shuffleoptions, String difficulty, Long usetime, Long lastusedate, Long createdate, Long lasteditdate, String creator, String delflag) {
        this.parent = parent;
        this.questioncontext = questioncontext;
        this.defaultgrade = defaultgrade;
        this.qtype = qtype;
        this.shuffleoptions = shuffleoptions;
        this.difficulty = difficulty;
        this.usetime = usetime;
        this.lastusedate = lastusedate;
        this.createdate = createdate;
        this.lasteditdate = lasteditdate;
        this.creator = creator;
        this.delflag = delflag;
    }
    
    /** full constructor */
    public ZjQuestion(String parent, String course, String chapter, String section, String category, String subject, String source, String title, String questioncontext, int questionNum, String generalfeedback, Long defaultgrade, String qtype, String shuffleoptions, String difficulty, String knowledge, String distinguish, String purpose, String ability, Long usetime, String answers, String correctfeedback, String incorrectfeedback, String accessoryUrl, String memo, Long lastusedate, Long createdate, Long lasteditdate, String creator, String lasteditor, String delflag, String status, String alias, Double rightRate) {
        this.parent = parent;
        this.course = course;
        this.chapter = chapter;
        this.section = section;
        this.category = category;
        this.subject = subject;
        this.source = source;
        this.title = title;
        this.questioncontext = questioncontext;
        this.questionNum = questionNum;
        this.generalfeedback = generalfeedback;
        this.defaultgrade = defaultgrade;
        this.qtype = qtype;
        this.shuffleoptions = shuffleoptions;
        this.difficulty = difficulty;
        this.knowledge = knowledge;
        this.distinguish = distinguish;
        this.purpose = purpose;
        this.ability = ability;
        this.usetime = usetime;
        this.answers = answers;
        this.correctfeedback = correctfeedback;
        this.incorrectfeedback = incorrectfeedback;
        this.accessoryUrl = accessoryUrl;
        this.memo = memo;
        this.lastusedate = lastusedate;
        this.createdate = createdate;
        this.lasteditdate = lasteditdate;
        this.creator = creator;
        this.lasteditor = lasteditor;
        this.delflag = delflag;
        this.status = status;
        this.alias = alias;
        this.rightRate = rightRate;
    }

   
    // Property accessors

    public String getId() {
        return this.id;
    }
    
    public void setId(String id) {
        this.id = id;
    }

    public String getParent() {
        return this.parent;
    }
    
    public void setParent(String parent) {
        this.parent = parent;
    }

    public String getCourse() {
        return this.course;
    }
    
    public void setCourse(String course) {
        this.course = course;
    }

    public String getChapter() {
        return this.chapter;
    }
    
    public void setChapter(String chapter) {
        this.chapter = chapter;
    }

    public String getSection() {
        return this.section;
    }
    
    public void setSection(String section) {
        this.section = section;
    }

    public String getCategory() {
        return this.category;
    }
    
    public void setCategory(String category) {
        this.category = category;
    }

    public String getSubject() {
        return this.subject;
    }
    
    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getSource() {
        return this.source;
    }
    
    public void setSource(String source) {
        this.source = source;
    }

    public String getTitle() {
        return this.title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }

    public String getQuestioncontext() {
        return this.questioncontext;
    }
    
    public void setQuestioncontext(String questioncontext) {
        this.questioncontext = questioncontext;
    }

    public int getQuestionNum() {
        return this.questionNum;
    }
    
    public void setQuestionNum(int questionNum) {
        this.questionNum = questionNum;
    }

    public String getGeneralfeedback() {
        return this.generalfeedback;
    }
    
    public void setGeneralfeedback(String generalfeedback) {
        this.generalfeedback = generalfeedback;
    }

    public Long getDefaultgrade() {
        return this.defaultgrade;
    }
    
    public void setDefaultgrade(Long defaultgrade) {
        this.defaultgrade = defaultgrade;
    }

    public String getQtype() {
        return this.qtype;
    }
    
    public void setQtype(String qtype) {
        this.qtype = qtype;
    }

    public String getShuffleoptions() {
        return this.shuffleoptions;
    }
    
    public void setShuffleoptions(String shuffleoptions) {
        this.shuffleoptions = shuffleoptions;
    }

    public String getDifficulty() {
        return this.difficulty;
    }
    
    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getKnowledge() {
        return this.knowledge;
    }
    
    public void setKnowledge(String knowledge) {
        this.knowledge = knowledge;
    }

    public String getDistinguish() {
        return this.distinguish;
    }
    
    public void setDistinguish(String distinguish) {
        this.distinguish = distinguish;
    }

    public String getPurpose() {
        return this.purpose;
    }
    
    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getAbility() {
        return this.ability;
    }
    
    public void setAbility(String ability) {
        this.ability = ability;
    }

    public Long getUsetime() {
        return this.usetime;
    }
    
    public void setUsetime(Long usetime) {
        this.usetime = usetime;
    }

    public String getAnswers() {
        return this.answers;
    }
    
    public void setAnswers(String answers) {
        this.answers = answers;
    }

    public String getCorrectfeedback() {
        return this.correctfeedback;
    }
    
    public void setCorrectfeedback(String correctfeedback) {
        this.correctfeedback = correctfeedback;
    }

    public String getIncorrectfeedback() {
        return this.incorrectfeedback;
    }
    
    public void setIncorrectfeedback(String incorrectfeedback) {
        this.incorrectfeedback = incorrectfeedback;
    }

    public String getAccessoryUrl() {
        return this.accessoryUrl;
    }
    
    public void setAccessoryUrl(String accessoryUrl) {
        this.accessoryUrl = accessoryUrl;
    }

    public String getMemo() {
        return this.memo;
    }
    
    public void setMemo(String memo) {
        this.memo = memo;
    }

    public Long getLastusedate() {
        return this.lastusedate;
    }
    
    public void setLastusedate(Long lastusedate) {
        this.lastusedate = lastusedate;
    }

    public Long getCreatedate() {
        return this.createdate;
    }
    
    public void setCreatedate(Long createdate) {
        this.createdate = createdate;
    }

    public Long getLasteditdate() {
        return this.lasteditdate;
    }
    
    public void setLasteditdate(Long lasteditdate) {
        this.lasteditdate = lasteditdate;
    }

    public String getCreator() {
        return this.creator;
    }
    
    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getLasteditor() {
        return this.lasteditor;
    }
    
    public void setLasteditor(String lasteditor) {
        this.lasteditor = lasteditor;
    }

    public String getDelflag() {
        return this.delflag;
    }
    
    public void setDelflag(String delflag) {
        this.delflag = delflag;
    }

    public String getStatus() {
        return this.status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }

    public String getAlias() {
        return this.alias;
    }
    
    public void setAlias(String alias) {
        this.alias = alias;
    }

    public Double getRightRate() {
        return this.rightRate;
    }
    
    public void setRightRate(Double rightRate) {
        this.rightRate = rightRate;
    }

	public List getOptionList() {
		return optionList;
	}

	public void setOptionList(List optionList) {
		this.optionList = optionList;
	}
   
	public void addQuestionOption(ZjQuestionOption option){
		optionList.add(option);
	}

	public List getMatchingAnswerList() {
		return matchingAnswerList;
	}

	public void setMatchingAnswerList(List matchingAnswerList) {
		this.matchingAnswerList = matchingAnswerList;
	}

	public void addMatchingAnswer(ZjQuestionMatchingAnswer matchingAnswer){
		matchingAnswerList.add(matchingAnswer);
	}
	public List getMatchingOptionList() {
		return matchingOptionList;
	}

	public void setMatchingOptionList(List matchingOptionList) {
		this.matchingOptionList = matchingOptionList;
	}
	public void addMatchingOption(ZjQuestionMatchingOption matchingOption){
		matchingOptionList.add(matchingOption);
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getQuestionKey() {
		return questionKey;
	}

	public void setQuestionKey(String questionKey) {
		this.questionKey = questionKey;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
	
	public List getSonQuestionList() {
		return sonQuestionList;
	}

	public void setSonQuestionList(List sonQuestionList) {
		this.sonQuestionList = sonQuestionList;
	}
	public void addSonQuestion(ZjQuestion question){
		sonQuestionList.add(question);
	}

	public Set getQuizQuestion() {
		return quizQuestion;
	}

	public void setQuizQuestion(Set quizQuestion) {
		this.quizQuestion = quizQuestion;
	}

	public ZjQuizAnswers getUAnswer() {
		return uAnswer;
	}

	public void setUAnswer(ZjQuizAnswers answer) {
		uAnswer = answer;
	}
}