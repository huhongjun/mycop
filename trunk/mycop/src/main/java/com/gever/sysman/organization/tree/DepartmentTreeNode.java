package com.gever.sysman.organization.tree;

import com.gever.util.tree.GeverTreeNode;
import com.gever.sysman.organization.dao.OrganizationFactory;
import com.gever.sysman.organization.dao.DepartmentDAO;
import com.gever.sysman.organization.model.Department;
import com.gever.util.tree.NodeObject;
import java.util.*;

public class DepartmentTreeNode
    extends GeverTreeNode {

    private OrganizationFactory factory = OrganizationFactory.getInstance();
    private DepartmentDAO departmentDAO = factory.getDepartmentDAO();

    public DepartmentTreeNode(Department department) {
        setUserObject(
            new NodeObject(
            department.getClass().getName(),
            department.getId(),
            department.getName()));
    }

    private Department getDepartment() {
        long id = this.getNodeObject().getId();
        Department department = null;
        try {
            department = departmentDAO.findDepartmentByID(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return department;
    }

    public String toString() {
        return "[DepartmentTreeNode "
            + ( (NodeObject) getUserObject()).toString()
            + " ]";
    }

    public void explore() {
        if (!isExplored()) {
            setExplored(true);
            Department department = getDepartment();

            Collection children = new ArrayList();
            try {
                children = departmentDAO.getSubDepartments(department);
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (children.size() == 0) {
                return;
            }

            for (Iterator it = children.iterator(); it.hasNext(); ) {
                Department d = (Department) it.next();
                add(new DepartmentTreeNode(d));
            }
        }
    }

    public boolean equals(Object obj) {
        if (obj == null || ! (obj instanceof DepartmentTreeNode)) {
            return false;
        }

        DepartmentTreeNode temp = (DepartmentTreeNode) obj;

        if (temp.getNodeObject().getId() == this.getNodeObject().getId()) {
            return true;
        }
        return false;
    }

    public NodeObject getNodeObject() {
        return (NodeObject) getUserObject();
    }

    public List getNodePath() {
        List result = new ArrayList();
        result.add(this);
        Department department = getDepartment();

        Department parent = null;
        try {
            while ( (parent = departmentDAO.getParentDepartment(parent)) != null) {
                if (parent != null) {
                    result.add(new DepartmentTreeNode(parent));
                }
                department = parent;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}