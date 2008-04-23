package com.gever.goa.menselect.vo;

import com.gever.vo.BaseVO;

public class MenSelectVO extends BaseVO {
    private String name;
    private String id;
    private String deptid;
    private String jobid;
    public MenSelectVO() {
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getDeptid() {
        return deptid;
    }
    public void setDeptid(String deptid) {
        this.deptid = deptid;
    }
    public String getJobid() {
        return jobid;
    }
    public void setJobid(String jobid) {
        this.jobid = jobid;
    }

}
