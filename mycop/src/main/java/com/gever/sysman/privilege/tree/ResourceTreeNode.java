/*
 * 创建日期 2003-10-14
 */
package com.gever.sysman.privilege.tree;

import com.gever.sysman.privilege.dao.PrivilegeFactory;
import com.gever.sysman.privilege.dao.ResourceDAO;
import com.gever.sysman.privilege.model.Resource;

import com.gever.util.tree.GeverTreeNode;
import com.gever.util.tree.NodeObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;


/**
 * @author Hu.Walker
 */
public class ResourceTreeNode extends GeverTreeNode {
    private PrivilegeFactory factory = PrivilegeFactory.getInstance();
    private ResourceDAO resourceDAO = factory.getResourceDAO();

    public ResourceTreeNode(Resource resource) {
        setUserObject(new NodeObject(resource.getClass().getName(),
                resource.getId(), resource.getName()));                      
    }

    private Resource getResource() {
        long id = getNodeObject().getId();
        Resource resource = null;
        Resource parent=null;
        Resource t_parent=null;
        Resource add_p=PrivilegeFactory.getInstance().createResource();
        try {
            resource = resourceDAO.findResourceByID(id);
            parent=resource;
			t_parent=resource;
			
            while (parent.getParentID()>0) {
				t_parent=resourceDAO.findResourceByID(parent.getParentID());
				add_p.addParent(t_parent);
				parent=t_parent;	
            }
			
            resource.setParent(add_p);
            resource.setChildren(resourceDAO.getChildren(resource));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return resource;
    }

    public String toString() {
        return "[ResourceTreeNode " +
        ((NodeObject) getUserObject()).toString() + " ]";
    }

    /*
     * @see com.gever.organization.tree.GeverTreeNode#explore()
     */
    public void explore() {
        if (!isExplored()) {
            setExplored(true);

            Resource resource = getResource();

            Collection children = new ArrayList();

            try {            	
                children = resourceDAO.getChildren(resource);               
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (children.size() == 0) {
                return;
            }

            for (Iterator it = children.iterator(); it.hasNext();) {            	
                Resource d = (Resource) it.next();
                add(new ResourceTreeNode(d));
            }
        }
    }

    /*
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public boolean equals(Object obj) {
        if ((obj == null) || !(obj instanceof ResourceTreeNode)) {
            return false;
        }

        ResourceTreeNode temp = (ResourceTreeNode) obj;

        if (temp.getNodeObject().getId() == this.getNodeObject().getId()) {
            return true;
        }

        return false;
    }

    /*
     * @see com.gever.organization.tree.GeverTreeNode#getNodePath()
     */
    public List getNodePath() {
        List result = new ArrayList();
        result.add(this);

        Resource resource = getResource();

        Resource parent = null;

        while ((parent = resource.getParent()) != null) {			
            if (parent != null) {
                result.add(new ResourceTreeNode(parent));
            }

            resource = parent;
        }

        return result;
    }

    /*
     * @see com.gever.organization.tree.GeverTreeNode#getNodeObject()
     */
    public NodeObject getNodeObject() {
        return (NodeObject) getUserObject();
    }
}
