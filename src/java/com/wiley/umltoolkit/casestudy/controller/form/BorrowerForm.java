package com.wiley.umltoolkit.casestudy.controller.form;
import com.wiley.umltoolkit.casestudy.controller.common.BaseForm;

/** Get form data when processing Borrower data
 * @author Paul M. Duvall, Stephen M. Matyas III
 */
public class BorrowerForm extends BaseForm  {

    private String username;
    private String password;

    public BorrowerForm()  {
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