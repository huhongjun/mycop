package org.sakaiproject.topicstore.tool.moduel;

import java.util.Set;

public class Module {
	
	/** moduleId */
	private int moduleId;
	
	/** moduleTitle */
	private String moduleTitle;
	
	/** Section Set */
	private Set section;

	public int getModuleId() {
		return moduleId;
	}

	public void setModuleId(int moduleId) {
		this.moduleId = moduleId;
	}

	public String getModuleTitle() {
		return moduleTitle;
	}

	public void setModuleTitle(String moduleTitle) {
		this.moduleTitle = moduleTitle;
	}

	public Set getSection() {
		return section;
	}

	public void setSection(Set section) {
		this.section = section;
	}

}
