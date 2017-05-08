package com.wiley.umltoolkit.casestudy.vo;
import com.wiley.umltoolkit.casestudy.vo.common.BaseVo;

/** LookupVo is a value object that contains generic data used to support
 * different types of Lookup tables in the database
 * @author Paul M. Duvall, Stephen M. Matyas III
 */
public class LookupVo extends BaseVo  {
    
    private String id;
    private String description;
    
    public LookupVo()  {
    }
    public String getDescription()  {
        return description;
    }
    public void setDescription(String description)  {
        this.description = description;
    }
    public void setId(String id)  {
        this.id = id;
    }
    public String getId()  {
        return id;
    }
}