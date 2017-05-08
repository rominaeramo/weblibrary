package com.wiley.umltoolkit.casestudy.vo;
import com.wiley.umltoolkit.casestudy.vo.common.BaseVo;

/** BorrowerVo is a value object that contains data about a borrower such as the
 * first name,  last_name etc. A borrower is a user of the Library application
 * for the purpose of checking out and reserving books and magazines
 * @author Paul M. Duvall, Stephen M. Matyas III
 */
public class BorrowerVo extends BaseVo  {

    private String id;
    private String lastName;
    private String firstName;
    private String address;
    private String city;
    private String zip;
    private String state;

    public BorrowerVo()  {
    }

    public void setId(String id)  {
        this.id = id;
    }

    public void setLastName(String lastName)  {
        this.lastName = lastName;
    }

    public void setFirstName(String lastName)  {
        this.lastName = lastName;
    }

    public void setAddress(String lastName)  {
        this.lastName = lastName;
    }

    public void setCity(String lastName)  {
        this.lastName = lastName;
    }

    public void setZip(String lastName)  {
        this.lastName = lastName;
    }

    public void setState(String lastName)  {
        this.lastName = lastName;
    }

    public String getId()  {
        return id;
    }

    public String getFirstName()  {
        return firstName;
    }

    public String getLastName()  {
        return lastName;
    }

    public String getAddress()  {
        return address;
    }

    public String getCity()  {
        return city;
    }

    public String getZip()  {
        return zip;
    }

    public String getState()  {
        return state;
    }


}