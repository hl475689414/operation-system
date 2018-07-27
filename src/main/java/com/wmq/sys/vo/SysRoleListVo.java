package com.wmq.sys.vo;

/**
 * Created by 李怀鹏 on 2018/7/13.
 */
public class SysRoleListVo {
    private int id; //角色ID
    private String roleName; //角色名称
    private int deptId; //所属部门ID
    private String deptName; //所属部门名称
    private String remark; //备注

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public int getDeptId() {
        return deptId;
    }

    public void setDeptId(int deptId) {
        this.deptId = deptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
