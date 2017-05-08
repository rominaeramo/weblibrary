package com.wiley.umltoolkit.casestudy.vo;
import com.wiley.umltoolkit.casestudy.vo.common.BaseVo;

/** UserVo is a value object that contains data about a user
 * @author Paul M. Duvall, Stephen M. Matyas III
 */
public class UserVo extends BaseVo  {

    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String id;
    private String currentRole;
    private String address;
    private String city;
    private String state;
    private String phone;
    private String zip;

    public UserVo()  {
    }

    public void setUsername(String username)  {
        this.username = username;
    }

    public void setFirstName(String firstName)  {
        this.firstName = firstName;
    }

    public void setLastName(String lastName)  {
        this.lastName = lastName;
    }

    public void setId(String id)  {
        this.id = id;
    }

    public String getUsername()  {
        return username;
    }

    public String getFirstName()  {
        return firstName;
    }

    public String getLastName()  {
        return lastName;
    }

    public String getId()  {
        return id;
    }

    /** Getter for property password.
     * @return Value of property password.
     *
     */
    public java.lang.String getPassword()  {
        return password;
    }

    /** Setter for property password.
     * @param password New value of property password.
     *
     */
    public void setPassword(java.lang.String password)  {
        this.password = password;
    }

    /** Getter for property currentRole.
     * @return Value of property currentRole.
     *
     */
    public java.lang.String getCurrentRole()  {
        return currentRole;
    }

    /** Setter for property currentRole.
     * @param currentRole New value of property currentRole.
     *
     */
    public void setCurrentRole(java.lang.String currentRole)  {
        this.currentRole = currentRole;
    }
    public String getAddress()  {
        return address;
    }
    public void setAddress(String address)  {
        this.address = address;
    }
    public String getCity()  {
        return city;
    }
    public void setCity(String city)  {
        this.city = city;
    }
    public String getPhone()  {
        return phone;
    }
    public void setPhone(String phone)  {
        this.phone = phone;
    }
    public String getState()  {
        return state;
    }
    public void setState(String state)  {
        this.state = state;
    }
    public String getZip()  {
        return zip;
    }
    public void setZip(String zip)  {
        this.zip = zip;
    }

}