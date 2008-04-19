
package com.zhjedu.exam.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import com.zhjedu.exam.domain.ZjCourse;
import com.zhjedu.exam.domain.ZjQuestionCategory;
import com.zhjedu.exam.manager.IQuestionManager;

public class MenuBuilder {
	private IQuestionManager questionManager;
	
	 public String generateCourseTree(String target, String url,String courseid,String server)
	    {
	        List menuList = questionManager.getMenuList(courseid);
	        List menuItems = chDirectory2TreeNode(menuList, target, url);
	        Iterator it = menuItems.iterator();
	        StringBuffer strBuf = new StringBuffer(64);
	        strBuf.append("<div class=\"dtree\">").append("\n");
	        strBuf.append("\t<script type=\"text/javascript\">").append("\n");
	        strBuf.append("\t\td = new dTree('d','"+server+"');").append("\n");
	        MenuItem menuItem;
	       
	        for(; it.hasNext(); strBuf.append(",").append(menuItem.isOpen()).append(");").append("\n"))
	        {
	            menuItem = (MenuItem)it.next();
	           
	            strBuf.append("\t\td.add(").append("'").append(menuItem.getMenuNo()).append("'").append(",").append("'").append(menuItem.getParentNo()).append("'");
	            strBuf.append(",'").append(menuItem.getName() != null ? menuItem.getName().trim() : "").append("'");
	            strBuf.append(",'").append(menuItem.getUrl() != null ? menuItem.getUrl() : "").append("'");
	            strBuf.append(",'").append(menuItem.getTitle() != null ? menuItem.getTitle().trim() : "").append("'");
	            strBuf.append(",'").append(menuItem.getTarget() != null ? menuItem.getTarget() : "").append("'");
	            strBuf.append(",'").append(menuItem.getIcon() != null ? menuItem.getIcon() : "").append("'");
	            strBuf.append(",'").append(menuItem.getIconOpen() != null ? menuItem.getIconOpen() : "").append("'");
	        }
	        
	        strBuf.append("\t\tdocument.write(d);").append("\n");
	        strBuf.append("\t</script>").append("\n");
	        strBuf.append("</div>").append("\n");
	        return strBuf.toString();
	    }
	    
	 
	 public String generateCourseTreeForChoose(String target, String url,String courseid,String server)
	    {
	        List menuList = questionManager.getMenuList(courseid);
	        List menuItems = chDirectory2TreeNode(menuList, target, url);
	        Iterator it = menuItems.iterator();
	        StringBuffer strBuf = new StringBuffer(64);
	        strBuf.append("<div class=\"dtree\">").append("\n");
	        strBuf.append("\t<script type=\"text/javascript\">").append("\n");
	        strBuf.append("\t\td = new dTree('d','"+server+"');").append("\n");
	        MenuItem menuItem;
	       
	        for(; it.hasNext(); strBuf.append(",").append(menuItem.isOpen()).append(");").append("\n"))
	        {
	            menuItem = (MenuItem)it.next();
	           
	            strBuf.append("\t\td.add(").append("'").append(menuItem.getMenuNo()).append("'").append(",").append("'").append(menuItem.getParentNo()).append("'");
	            strBuf.append(",'").append("<input type=checkbox onclick=\"test(this)\" name=\"tree\" title="+menuItem.getName()+" title="+menuItem.getName()+" value="+menuItem.getMenuNo()+">").append(menuItem.getName() != null ? menuItem.getName().trim() : "").append("'");
	            strBuf.append(",'").append("'");
	            strBuf.append(",'").append(menuItem.getName() != null ? menuItem.getName().trim() : "").append("'");
	            strBuf.append(",'").append(menuItem.getUrl() != null ? menuItem.getUrl() : "").append("'");
	            strBuf.append(",'").append(menuItem.getTitle() != null ? menuItem.getTitle().trim() : "").append("'");
	            strBuf.append(",'").append(menuItem.getTarget() != null ? menuItem.getTarget() : "").append("'");
	            strBuf.append(",'").append(menuItem.getIcon() != null ? menuItem.getIcon() : "").append("'");
	            strBuf.append(",'").append(menuItem.getIconOpen() != null ? menuItem.getIconOpen() : "").append("'");
	        }
	        
	        strBuf.append("\t\tdocument.write(d);").append("\n");
	        strBuf.append("\t</script>").append("\n");
	        strBuf.append("</div>").append("\n");
	        return strBuf.toString();
	    }
	 
	 
		private List chDirectory2TreeNode(List menuList, String target, String url)
	    {
	        List menuItems = new ArrayList();
	        for(Iterator it = menuList.iterator(); it.hasNext(); Collections.sort(menuItems))
	        {
	        	ZjCourse node = (ZjCourse)it.next();
	            MenuItem menuItem = new MenuItem();
	            menuItem.setMenuNo(node.getId());         
	            menuItem.setParentNo(node.getParent() != null ? node.getParent() : "");
	            String name = node.getName();

	            menuItem.setName(name); 
	            String course = "";
	            String chapter = "";
	            String section = "";
	            if(node.getItemType().equals("0")){
	            	course = node.getId();
	            	menuItem.setUrl("");
	            }
	            if(node.getItemType().equals("1")){
	            	ZjCourse coursetmp = this.getQuestionManager().getZjCourse(node.getParent());
	            	course = coursetmp.getId();
	            	chapter = node.getId();
	            	menuItem.setUrl(url + "&course="+course+"&chapter="+chapter+"&section="+section+"&nodeid="+node.getId());
	            }
	            if(node.getItemType().equals("2")){
	            	ZjCourse chaptertmp = this.getQuestionManager().getZjCourse(node.getParent());
	            	ZjCourse coursetmp = this.getQuestionManager().getZjCourse(chaptertmp.getParent());
	            	course = coursetmp.getId();
	            	chapter = chaptertmp.getId();
	            	section = node.getId();
	            	menuItem.setUrl(url + "&course="+course+"&chapter="+chapter+"&section="+section+"&nodeid="+node.getId());
	            }
	            
	            	           	       
	            menuItem.setIcon("");
	            menuItem.setIconOpen("");
	            menuItem.setOpen(false);
	            menuItem.setTitle(node.getName());
	            menuItem.setTarget(target);
	            menuItems.add(menuItem);
	        }

	        return menuItems;
	    }

		public String generateCategoryTree(String target, String url,String courseid,String server)
	    {
	        List menuList = questionManager.getAllCategory("");
	        List menuItems = chDirectory2CatTreeNode(menuList, target, url);
	        Iterator it = menuItems.iterator();
	        StringBuffer strBuf = new StringBuffer(64);
	        strBuf.append("<div class=\"dtree\">").append("\n");
	        strBuf.append("\t<script type=\"text/javascript\">").append("\n");
	        strBuf.append("\t\td = new dTree('d','"+server+"');").append("\n");
	        MenuItem menuItem;
	       
	        for(; it.hasNext(); strBuf.append(",").append(menuItem.isOpen()).append(");").append("\n"))
	        {
	            menuItem = (MenuItem)it.next();
	           
	            strBuf.append("\t\td.add(").append("'").append(menuItem.getMenuNo()).append("'").append(",").append("'").append(menuItem.getParentNo()).append("'");
	            strBuf.append(",'").append(menuItem.getName() != null ? menuItem.getName().trim() : "").append("'");
	            strBuf.append(",'").append(menuItem.getUrl() != null ? menuItem.getUrl() : "").append("'");
	            strBuf.append(",'").append(menuItem.getTitle() != null ? menuItem.getTitle().trim() : "").append("'");
	            strBuf.append(",'").append(menuItem.getTarget() != null ? menuItem.getTarget() : "").append("'");
	            strBuf.append(",'").append(menuItem.getIcon() != null ? menuItem.getIcon() : "").append("'");
	            strBuf.append(",'").append(menuItem.getIconOpen() != null ? menuItem.getIconOpen() : "").append("'");
	        }
	        
	        strBuf.append("\t\tdocument.write(d);").append("\n");
	        strBuf.append("\t</script>").append("\n");
	        strBuf.append("</div>").append("\n");
	        return strBuf.toString();
	    }
		
		public String generateCategoryTreeForChoose(String target, String url,String courseid,String server)
	    {
	        List menuList = questionManager.getAllCategory("");
	        List menuItems = chDirectory2CatTreeNodeForChoose(menuList, target, url);
	        Iterator it = menuItems.iterator();
	        StringBuffer strBuf = new StringBuffer(64);
	        strBuf.append("<div class=\"dtree\">").append("\n");
	        strBuf.append("\t<script type=\"text/javascript\">").append("\n");
	        strBuf.append("\t\td = new dTree('d','"+server+"');").append("\n");
	        MenuItem menuItem;
	       
	        for(; it.hasNext(); strBuf.append(",").append(menuItem.isOpen()).append(");").append("\n"))
	        {
	            menuItem = (MenuItem)it.next();
	           
	            strBuf.append("\t\td.add(").append("'").append(menuItem.getMenuNo()).append("'").append(",").append("'").append(menuItem.getParentNo()).append("'");
	            strBuf.append(",'").append(menuItem.getName() != null ? menuItem.getName().trim() : "").append("'");
	            strBuf.append(",'").append(menuItem.getUrl() != null ? menuItem.getUrl() : "").append("'");
	            strBuf.append(",'").append(menuItem.getTitle() != null ? menuItem.getTitle().trim() : "").append("'");
	            strBuf.append(",'").append(menuItem.getTarget() != null ? menuItem.getTarget() : "").append("'");
	            strBuf.append(",'").append(menuItem.getIcon() != null ? menuItem.getIcon() : "").append("'");
	            strBuf.append(",'").append(menuItem.getIconOpen() != null ? menuItem.getIconOpen() : "").append("'");
	        }
	        
	        strBuf.append("\t\tdocument.write(d);").append("\n");
	        strBuf.append("\t</script>").append("\n");
	        strBuf.append("</div>").append("\n");
	        return strBuf.toString();
	        
	    }
		
		private List chDirectory2CatTreeNode(List menuList, String target, String url)
	    {
	        List menuItems = new ArrayList();
	        for(Iterator it = menuList.iterator(); it.hasNext(); Collections.sort(menuItems))
	        {
	        	ZjQuestionCategory node = (ZjQuestionCategory)it.next();
	            MenuItem menuItem = new MenuItem();
	            menuItem.setMenuNo(node.getId());         
	            menuItem.setParentNo(node.getParent() != null ? node.getParent() : "");
	            String name = node.getName();
	            menuItem.setName(name);  
	           
	            menuItem.setUrl(url + "&category="+node.getId());	 
	           
	            menuItem.setIcon("");
	            menuItem.setIconOpen("");
	            menuItem.setOpen(false);
	            menuItem.setTitle(node.getName());
	            menuItem.setTarget(target);
	            menuItems.add(menuItem);
	        }

	        return menuItems;
	    }
		
		private List chDirectory2CatTreeNodeForChoose(List menuList, String target, String url)
	    {
	        List menuItems = new ArrayList();
	        for(Iterator it = menuList.iterator(); it.hasNext(); Collections.sort(menuItems))
	        {
	        	ZjQuestionCategory node = (ZjQuestionCategory)it.next();
	            MenuItem menuItem = new MenuItem();
	            menuItem.setMenuNo(node.getId());         
	            menuItem.setParentNo(node.getParent() != null ? node.getParent() : "");
	            String name = node.getName();
	            menuItem.setName(name);  
	            
	            menuItem.setUrl(url + "&category="+node.getId());	 
	            
	            menuItem.setIcon("");
	            menuItem.setIconOpen("");
	            menuItem.setOpen(false);
	            menuItem.setTitle(node.getName());
	            menuItem.setTarget(target);
	            menuItems.add(menuItem);
	        }

	        return menuItems;
	    }
		
		public IQuestionManager getQuestionManager() {
			return questionManager;
		}

		public void setQuestionManager(IQuestionManager questionManager) {
			this.questionManager = questionManager;
		}
	
}
