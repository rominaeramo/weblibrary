package com.wiley.umltoolkit.casestudy.controller.form;
import java.io.Serializable;
import org.apache.struts.action.ActionForm;

/** Get form data when reserving an Item
 * @author Paul M. Duvall, Stephen M. Matyas III
 */
public class ReservationForm extends ActionForm implements Serializable  {
    
    private String username;
    private String password;
    
    public ReservationForm()  {
    }
    
    public String getUsername()  {
        return username;
    }
    
    public void setUsername(String username)  {
        this.username = username;
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
    
}