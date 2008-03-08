package org.sakaiproject.topicstore.tool.util;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class XmlDealUtil
{
	@SuppressWarnings(value = { "unchecked" })
	public static List getIds(String str)
	{
		List list = new ArrayList();

		try
		{
			StringReader strInStream = new StringReader(str);
			SAXReader reader = new SAXReader();
			Document doc = reader.read(strInStream);
			Element root = doc.getRootElement();
			//ö�ٸ�Ԫ������Ϊsection��Ԫ������Ϊid��ֵ
			for(Iterator iter = root.elementIterator("section"); iter.hasNext();)
			{
				Element element = (Element) iter.next();
				String ss = element.attributeValue("id");
				list.add(ss);		
			}
		} catch (DocumentException e)
		{
			e.printStackTrace();
		}
		
		return list;
	}
}
