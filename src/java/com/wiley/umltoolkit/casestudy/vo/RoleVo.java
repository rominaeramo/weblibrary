package com.wiley.umltoolkit.casestudy.vo;

/** RoleVo is a value object that contains data about a role in the system. A
 * role represents how a user interacts with the system regarding what the
 * user may be authorized to perform
 * NOTE: This is not currently implemented
 * @author Paul M. Duvall, Stephen M. Matyas III
 */
public class RoleVo extends LookupVo  {
    
    public RoleVo()  {
        super();
    }
    public String getRoleId() {
        return this.getId();
    }
    public void setRoleId(String id) {
        this.setId(id);
    }
    public String getName() {
        return this.getDescription();
    }
    public void setName(String name) {
        this.setDescription(name);
    }
}