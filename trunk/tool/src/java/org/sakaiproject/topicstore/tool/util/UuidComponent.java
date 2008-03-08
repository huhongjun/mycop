package org.sakaiproject.topicstore.tool.util;

import java.util.UUID;

/**
 * @author：jingbac
 *
 */
public class UuidComponent {

	public UuidComponent() {
	}
	
	public String createUuid()
	{
		String id = UUID.randomUUID().toString();

		return id==null?null:id;
	}
}
