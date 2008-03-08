package org.sakaiproject.topicstore.tool.moduel;

import java.util.Set;

public class Course {
	/** courseId */
	private String courseId;
	/** courseName */
	private String courseName;
	
	/** Module Set */
	private Set module;

	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public Set getModule() {
		return module;
	}

	public void setModule(Set module) {
		this.module = module;
	}


}
